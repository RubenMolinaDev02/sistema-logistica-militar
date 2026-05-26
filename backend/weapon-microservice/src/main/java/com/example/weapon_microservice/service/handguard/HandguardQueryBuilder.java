package com.example.weapon_microservice.service.handguard;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.common.enums.MountType;
import com.example.weapon_microservice.model.handguard.HandguardModel;
import com.example.weapon_microservice.model.handguard.enums.HandguardMaterial;
import com.example.weapon_microservice.model.platform.PlatformModel;
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
public class HandguardQueryBuilder extends BaseQueryBuilder<HandguardModel> {

    private final MongoTemplate mongoTemplate;
    private final QuerySecurityValidator validator;

    public HandguardQueryBuilder(MongoTemplate mongoTemplate) {

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
            "mountType",
            "material",
            "compatiblePlatformsIds"
    );

    public static final Map<String, Set<String>> FIELD_OPERATORS = Map.of(
            "name", Set.of("EQ", "REGEX"),
            "reference", Set.of("EQ"),
            "mountType", Set.of("EQ"),
            "material", Set.of("EQ"),
            "compatiblePlatformsIds", Set.of("EQ", "IN")
    );

    public static final Map<String, Class<?>> FIELD_TYPES = Map.of(
            "name", String.class,
            "reference", String.class,
            "mountType", MountType.class,
            "material", HandguardMaterial.class,
            "compatiblePlatformsIds", String.class
    );

    @Override
    protected Set<String> getAllowedFields() {
        return ALLOWED_FIELDS;
    }

    public PageResponse<HandguardModel> search(SearchRequest request, int page, int size) {

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

        List<HandguardModel> content =
                mongoTemplate.find(query, HandguardModel.class);

        long total =
                mongoTemplate.count(new Query(), HandguardModel.class);

        return PageResponse.<HandguardModel>builder()
                .content(content)
                .totalElements(total)
                .totalPages((int) Math.ceil((double) total / size))
                .page(page)
                .size(size)
                .build();
    }
}