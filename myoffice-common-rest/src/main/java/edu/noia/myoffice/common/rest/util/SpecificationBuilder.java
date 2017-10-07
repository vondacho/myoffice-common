package edu.noia.myoffice.common.rest.util;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpecificationBuilder<E> {

    private final List<Specification<E>> params = new ArrayList();

    public SpecificationBuilder with(SearchCriteria criteria) {
        Optional<Specification<E>> specification = criteria.toSpecification();
        specification.ifPresent(s -> params.add(s));
        return this;
    }

    public Specification<E> build() {
        return params.stream()
            .reduce((s1, s2) -> Specifications.where(s1).and(s2))
            .orElse(null);
    }
}
