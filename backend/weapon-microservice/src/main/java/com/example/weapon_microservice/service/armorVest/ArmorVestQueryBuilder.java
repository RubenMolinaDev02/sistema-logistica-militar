package com.example.weapon_microservice.service.armorVest;

import com.example.weapon_microservice.model.PageResponse;
import com.example.weapon_microservice.model.armorVest.ArmorVestModel;
import com.example.weapon_microservice.model.armorVest.enums.SoftArmorLevel;
import com.example.weapon_microservice.model.common.enums.ServiceStatus;
import com.example.weapon_microservice.model.weapon.WeaponModel;
import com.example.weapon_microservice.model.weapon.enums.WeaponType;
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
public class ArmorVestQueryBuilder extends BaseQueryBuilder<ArmorVestModel> {

    private final MongoTemplate mongoTemplate;
    private final QuerySecurityValidator validator;

    public ArmorVestQueryBuilder(MongoTemplate mongoTemplate) {

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
            "softArmor",
            "compatibleWithPlates",
            "status"
    );

    public static final Map<String, Set<String>> FIELD_OPERATORS = Map.of(
            "name", Set.of("EQ", "REGEX"),
            "reference", Set.of("EQ"),
            "softArmor", Set.of("EQ"),
            "compatibleWithPlates", Set.of("EQ"),
            "status", Set.of("EQ")
    );

    public static final Map<String, Class<?>> FIELD_TYPES = Map.of(
            "name", String.class,
            "reference", String.class,
            "softArmor", SoftArmorLevel.class,
            "compatibleWithPlates", Boolean.class,
            "status", ServiceStatus.class
    );

    @Override
    protected Set<String> getAllowedFields() {
        return ALLOWED_FIELDS;
    }

    public PageResponse<ArmorVestModel> search(SearchRequest request, int page, int size) {

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

        List<ArmorVestModel> content =
                mongoTemplate.find(query, ArmorVestModel.class);

        long total =
                mongoTemplate.count(new Query(), ArmorVestModel.class);

        return PageResponse.<ArmorVestModel>builder()
                .content(content)
                .totalElements(total)
                .totalPages((int) Math.ceil((double) total / size))
                .page(page)
                .size(size)
                .build();
    }
}