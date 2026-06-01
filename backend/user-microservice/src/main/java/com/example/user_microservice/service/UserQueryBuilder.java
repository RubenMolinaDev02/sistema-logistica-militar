package com.example.user_microservice.service;

import com.example.user_microservice.model.PageResponse;
import com.example.user_microservice.model.user.UserModel;
import com.example.user_microservice.model.user.enums.Rank;
import com.example.user_microservice.model.user.enums.Role;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class UserQueryBuilder extends BaseQueryBuilder<UserModel> {

    private final MongoTemplate mongoTemplate;
    private final QuerySecurityValidator validator;

    public UserQueryBuilder(MongoTemplate mongoTemplate) {

        this.mongoTemplate = mongoTemplate;

        this.validator = new QuerySecurityValidator(
                ALLOWED_FIELDS,
                FIELD_OPERATORS,
                FIELD_TYPES
        );
    }

    private static final Set<String> ALLOWED_FIELDS = Set.of(
            "soldierNumber",
            "firstName",
            "lastName",
            "dni",
            "username",
            "createdAt",
            "locationId",
            "rank",
            "role",
            "active"
    );

    public static final Map<String, Set<String>> FIELD_OPERATORS = Map.of(
            "soldierNumber", Set.of("EQ"),
            "dni", Set.of("EQ"),
            "firstName", Set.of("EQ", "REGEX"),
            "lastName", Set.of("EQ", "REGEX"),
            "active", Set.of("EQ"),
            "username", Set.of("EQ", "REGEX"),
            "createdAt", Set.of("EQ", "GTE", "LTE"),
            "locationId", Set.of("EQ"),
            "rank", Set.of("EQ"),
            "role", Set.of("EQ")
    );

    public static final Map<String, Class<?>> FIELD_TYPES = Map.of(
            "soldierNumber", String.class,
            "dni", String.class,
            "firstName", String.class,
            "lastName", String.class,
            "active", Boolean.class,
            "username", String.class,
            "createdAt", LocalDate.class,
            "locationId", String.class,
            "rank", Rank.class,
            "role", Role.class
    );

    @Override
    protected Set<String> getAllowedFields() {
        return ALLOWED_FIELDS;
    }

    public PageResponse<UserModel> search(SearchRequest request, int page, int size) {

        Query query = new Query();

        query.skip((long) page * size);
        query.limit(size);

        if (request.getFilters() != null) {

            List<Criteria> criteria = request.getFilters()
                    .stream()
                    .peek(validator::validate)
                    .map(this::buildCriteria)
                    .toList();

            if (!criteria.isEmpty()) {
                query.addCriteria(new Criteria().andOperator(criteria));
            }
        }

        applySorting(query, request.getSortBy(), request.getDirection());

        List<UserModel> content =
                mongoTemplate.find(query, UserModel.class);

        long total =
                mongoTemplate.count(new Query(), UserModel.class);

        return PageResponse.<UserModel>builder()
                .content(content)
                .totalElements(total)
                .totalPages((int) Math.ceil((double) total / size))
                .page(page)
                .size(size)
                .build();
    }
}