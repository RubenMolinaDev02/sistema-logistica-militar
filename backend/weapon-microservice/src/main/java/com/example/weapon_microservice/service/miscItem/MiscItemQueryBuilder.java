package com.example.weapon_microservice.service.miscItem;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.miscItems.MiscItemModel;
import com.example.weapon_microservice.service.BaseQueryBuilder;
import com.example.weapon_microservice.service.QuerySecurityValidator;
import com.example.weapon_microservice.service.SearchRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class MiscItemQueryBuilder extends BaseQueryBuilder<MiscItemModel> {

    private final MongoTemplate mongoTemplate;
    private final QuerySecurityValidator validator;

    public MiscItemQueryBuilder(MongoTemplate mongoTemplate) {

        this.mongoTemplate = mongoTemplate;

        this.validator = new QuerySecurityValidator(
                ALLOWED_FIELDS,
                FIELD_OPERATORS,
                FIELD_TYPES
        );
    }

    private static final Set<String> ALLOWED_FIELDS = Set.of(
            "name",
            "type",
            "reference"
    );

    public static final Map<String, Set<String>> FIELD_OPERATORS = Map.of(
            "name", Set.of("EQ", "REGEX"),
            "reference", Set.of("EQ", "IN"),
            "type", Set.of("EQ", "IN")
    );

    public static final Map<String, Class<?>> FIELD_TYPES = Map.of(
            "name", String.class,
            "reference", String.class,
            "type", Object.class
    );

    @Override
    protected Set<String> getAllowedFields() {
        return ALLOWED_FIELDS;
    }

    public PageResponse<MiscItemModel> search(SearchRequest request, int page, int size) {

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

        List<MiscItemModel> content =
                mongoTemplate.find(query, MiscItemModel.class);

        long total =
                mongoTemplate.count(new Query(), MiscItemModel.class);

        return PageResponse.<MiscItemModel>builder()
                .content(content)
                .totalElements(total)
                .totalPages((int) Math.ceil((double) total / size))
                .page(page)
                .size(size)
                .build();
    }
}