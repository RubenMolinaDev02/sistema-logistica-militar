package com.example.weapon_microservice.service.helmet;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import com.example.weapon_microservice.model.helmet.HelmetModel;
import com.example.weapon_microservice.model.helmet.enums.HelmetLevel;
import com.example.weapon_microservice.model.helmet.enums.HelmetMaterial;
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
public class HelmetQueryBuilder extends BaseQueryBuilder<HelmetModel> {

    private final MongoTemplate mongoTemplate;
    private final QuerySecurityValidator validator;

    public HelmetQueryBuilder(MongoTemplate mongoTemplate) {

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
            "level",
            "material",
            "weight",
            "status"
    );

    public static final Map<String, Set<String>> FIELD_OPERATORS = Map.of(
            "name", Set.of("EQ", "REGEX"),
            "reference", Set.of("EQ"),
            "level", Set.of("EQ"),
            "material", Set.of("EQ"),
            "weight", Set.of("EQ", "GTE", "LTE"),
            "status", Set.of("EQ")
    );

    public static final Map<String, Class<?>> FIELD_TYPES = Map.of(
            "name", String.class,
            "reference", String.class,
            "level", HelmetLevel.class,
            "material", HelmetMaterial.class,
            "weight", Number.class,
            "status", ServiceStatus.class
    );

    @Override
    protected Set<String> getAllowedFields() {
        return ALLOWED_FIELDS;
    }

    public PageResponse<HelmetModel> search(SearchRequest request, int page, int size) {

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

        List<HelmetModel> content =
                mongoTemplate.find(query, HelmetModel.class);

        long total =
                mongoTemplate.count(new Query(), HelmetModel.class);

        return PageResponse.<HelmetModel>builder()
                .content(content)
                .totalElements(total)
                .totalPages((int) Math.ceil((double) total / size))
                .page(page)
                .size(size)
                .build();
    }
}