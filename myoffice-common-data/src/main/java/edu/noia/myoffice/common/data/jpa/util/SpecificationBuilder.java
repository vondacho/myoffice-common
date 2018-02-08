package edu.noia.myoffice.common.data.jpa.util;

import edu.noia.myoffice.common.util.search.FindCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpecificationBuilder<E> {

    private final List<Specification<E>> params = new ArrayList();

    public SpecificationBuilder with(FindCriteria criteria) {
        Optional<Specification<E>> specification = FindCriteriaSpecification.from(criteria);
        specification.ifPresent(params::add);
        return this;
    }

    public Specification<E> build() {
        return params.stream()
            .reduce((s1, s2) -> s1.and(s2))
            .orElse(null);
    }
}
