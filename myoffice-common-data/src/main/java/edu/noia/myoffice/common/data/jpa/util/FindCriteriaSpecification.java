package edu.noia.myoffice.common.data.jpa.util;

import edu.noia.myoffice.common.util.search.FindCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class FindCriteriaSpecification {

    private FindCriteriaSpecification() {
    }

    public static <E> Optional<Specification<E>> from(FindCriteria criteria) {
        return Optional.ofNullable(toSpec(criteria));
    }

    private static <E> Specification<E> toSpec(FindCriteria criteria) {
        return (root, query, builder) -> {
            if (criteria.getOperation().equalsIgnoreCase(">")) {
                return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
            }
            else if (criteria.getOperation().equalsIgnoreCase("<")) {
                return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
            }
            else if (criteria.getOperation().equalsIgnoreCase(":")) {
                if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    return builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
                } else {
                    return builder.equal(root.get(criteria.getKey()), criteria.getValue());
                }
            }
            return null;
        };
    }
}