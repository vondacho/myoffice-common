package edu.noia.myoffice.common.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface ValueRepository<V, I> {

    @Transactional(readOnly = true)
    Optional<V> findOne(I id);

    @Transactional(readOnly = true)
    List<V> findAll(Specification specification);

    @Transactional(readOnly = true)
    Page<V> findAll(Specification specification, Pageable pageable);

    @Transactional(readOnly = true)
    Page<V> findAll(Pageable pageable);

    @Transactional(readOnly = true)
    default List<V> findAll() {
        return this.findAll((Specification) Specifications.where((Specification)null));
    }

    V save(V value);

    void delete(I id);

}
