package com.example.weapon_microservice.service.grenade;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.grenade.GrenadeModel;
import com.example.weapon_microservice.model.grenade.enums.ArmingMethod;
import com.example.weapon_microservice.model.grenade.enums.GrenadeType;
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
public class GrenadeQueryBuilder extends BaseQueryBuilder<GrenadeModel> {

    private final MongoTemplate mongoTemplate;
    private final QuerySecurityValidator validator;

    public GrenadeQueryBuilder(MongoTemplate mongoTemplate) {

        this.mongoTemplate = mongoTemplate;

        this.validator = new QuerySecurityValidator(
                ALLOWED_FIELDS,
                FIELD_OPERATORS,
                FIELD_TYPES
        );
    }

    private static final Set<String> ALLOWED_FIELDS = Set.of(
            "name",
            "reference",
            "type",
            "fuzeDelay",
            "armingDistance",
            "armingMethod",
            "lethal"
    );

    public static final Map<String, Set<String>> FIELD_OPERATORS = Map.of(
            "name", Set.of("EQ", "REGEX"),
            "reference", Set.of("EQ"),
            "type", Set.of("EQ"),
            "fuzeDelay", Set.of("GTE", "LTE", "EQ"),
            "armingDistance", Set.of("GTE", "LTE", "EQ"),
            "armingMethod", Set.of("EQ"),
            "lethal", Set.of("EQ")
    );

    public static final Map<String, Class<?>> FIELD_TYPES = Map.of(
            "name", String.class,
            "reference", String.class,
            "type", GrenadeType.class,
            "fuzeDelay", Number.class,
            "armingDistance", Number.class,
            "armingMethod", ArmingMethod.class,
            "lethal", Boolean.class
    );

    @Override
    protected Set<String> getAllowedFields() {
        return ALLOWED_FIELDS;
    }

    public PageResponse<GrenadeModel> search(SearchRequest request, int page, int size) {

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

        List<GrenadeModel> content =
                mongoTemplate.find(query, GrenadeModel.class);

        long total =
                mongoTemplate.count(new Query(), GrenadeModel.class);

        return PageResponse.<GrenadeModel>builder()
                .content(content)
                .totalElements(total)
                .totalPages((int) Math.ceil((double) total / size))
                .page(page)
                .size(size)
                .build();
    }
}