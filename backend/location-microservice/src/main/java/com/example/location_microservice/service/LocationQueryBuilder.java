package com.example.location_microservice.service;

import com.example.location_microservice.model.PageResponse;
import com.example.location_microservice.model.location.LocationModel;
import com.example.location_microservice.model.location.enums.LocationStatus;
import com.example.location_microservice.model.location.enums.LocationType;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class LocationQueryBuilder extends BaseQueryBuilder<LocationModel> {

    private final MongoTemplate mongoTemplate;
    private final QuerySecurityValidator validator;

    public LocationQueryBuilder(MongoTemplate mongoTemplate) {

        this.mongoTemplate = mongoTemplate;

        this.validator = new QuerySecurityValidator(
                ALLOWED_FIELDS,
                FIELD_OPERATORS,
                FIELD_TYPES
        );
    }

    private String reference;
    private String name;
    private String country;
    private LocationType type;
    private int maxTroops;
    private LocationStatus status;

    private static final Set<String> ALLOWED_FIELDS = Set.of(
            "reference",
            "name",
            "country",
            "type",
            "maxTroops",
            "status"
    );

    public static final Map<String, Set<String>> FIELD_OPERATORS = Map.of(
            "reference", Set.of("EQ"),
            "name", Set.of("EQ", "REGEX"),
            "country", Set.of("EQ", "REGEX"),
            "type", Set.of("EQ"),
            "maxTroops", Set.of("EQ", "GTE", "LTE"),
            "status", Set.of("EQ")
    );

    public static final Map<String, Class<?>> FIELD_TYPES = Map.of(
            "reference", String.class,
            "name", String.class,
            "country", String.class,
            "type", LocationType.class,
            "maxTroops", Number.class,
            "status", LocationStatus.class
    );

    @Override
    protected Set<String> getAllowedFields() {
        return ALLOWED_FIELDS;
    }

    public PageResponse<LocationModel> search(SearchRequest request, int page, int size) {

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

        List<LocationModel> content =
                mongoTemplate.find(query, LocationModel.class);

        long total =
                mongoTemplate.count(new Query(), LocationModel.class);

        return PageResponse.<LocationModel>builder()
                .content(content)
                .totalElements(total)
                .totalPages((int) Math.ceil((double) total / size))
                .page(page)
                .size(size)
                .build();
    }
}