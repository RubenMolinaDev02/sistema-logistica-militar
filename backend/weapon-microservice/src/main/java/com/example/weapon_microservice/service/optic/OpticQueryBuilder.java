package com.example.weapon_microservice.service.optic;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.common.enums.SightMountType;
import com.example.weapon_microservice.model.optic.OpticModel;
import com.example.weapon_microservice.model.optic.enums.OpticType;
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
public class OpticQueryBuilder extends BaseQueryBuilder<OpticModel> {

    private final MongoTemplate mongoTemplate;
    private final QuerySecurityValidator validator;

    public OpticQueryBuilder(MongoTemplate mongoTemplate) {

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
            "mountType",
            "minZoom",
            "maxZoom"
    );

    public static final Map<String, Set<String>> FIELD_OPERATORS = Map.of(
            "name", Set.of("EQ", "REGEX"),
            "reference", Set.of("EQ"),
            "mountType", Set.of("EQ", "IN"),
            "minZoom", Set.of("GTE", "LTE", "EQ"),
            "maxZoom", Set.of("GTE", "LTE", "EQ"),
            "type", Set.of("EQ", "IN")
    );

    public static final Map<String, Class<?>> FIELD_TYPES = Map.of(
            "name", String.class,
            "reference", String.class,
            "type", OpticType.class,
            "mountType", SightMountType.class,
            "minZoom", Number.class,
            "maxZoom", Number.class
    );

    @Override
    protected Set<String> getAllowedFields() {
        return ALLOWED_FIELDS;
    }

    public PageResponse<OpticModel> search(SearchRequest request, int page, int size) {

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

        List<OpticModel> content =
                mongoTemplate.find(query, OpticModel.class);

        long total =
                mongoTemplate.count(new Query(), OpticModel.class);

        return PageResponse.<OpticModel>builder()
                .content(content)
                .totalElements(total)
                .totalPages((int) Math.ceil((double) total / size))
                .page(page)
                .size(size)
                .build();
    }
}