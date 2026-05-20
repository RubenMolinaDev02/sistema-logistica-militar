package com.example.weapon_microservice.service.weapon;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import com.example.weapon_microservice.service.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class WeaponQueryBuilder extends BaseQueryBuilder<WeaponModel> {

    private final MongoTemplate mongoTemplate;
    private final QuerySecurityValidator validator;

    public WeaponQueryBuilder(MongoTemplate mongoTemplate) {

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
            "platformId",
            "caliberId",
            "manufacturerId",
            "barrelLength",
            "effectiveDistance"
    );

    public static final Map<String, Set<String>> FIELD_OPERATORS = Map.of(
            "name", Set.of("EQ", "REGEX"),
            "reference", Set.of("EQ"),
            "platformId", Set.of("EQ"),
            "manufacturerId", Set.of("EQ"),
            "caliberId", Set.of("EQ"),
            "barrelLength", Set.of("GTE", "LTE", "EQ"),
            "effectiveDistance", Set.of("GTE", "LTE", "EQ"),
            "type", Set.of("EQ", "IN")
    );

    public static final Map<String, Class<?>> FIELD_TYPES = Map.of(
            "name", String.class,
            "reference", String.class,
            "platformId", String.class,
            "manufacturerId", String.class,
            "caliberId", String.class,
            "barrelLength", Number.class,
            "effectiveDistance", Number.class,
            "type", Object.class
    );

    @Override
    protected Set<String> getAllowedFields() {
        return ALLOWED_FIELDS;
    }

    public PageResponse<WeaponModel> search(SearchRequest request, int page, int size) {

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

        List<WeaponModel> content =
                mongoTemplate.find(query, WeaponModel.class);

        long total =
                mongoTemplate.count(new Query(), WeaponModel.class);

        return PageResponse.<WeaponModel>builder()
                .content(content)
                .totalElements(total)
                .totalPages((int) Math.ceil((double) total / size))
                .page(page)
                .size(size)
                .build();
    }
}