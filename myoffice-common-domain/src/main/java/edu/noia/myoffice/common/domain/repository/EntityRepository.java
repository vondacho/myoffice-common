package edu.noia.myoffice.common.domain.repository;

import edu.noia.myoffice.common.domain.entity.Entity;
import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.common.domain.vo.Identity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface EntityRepository<E extends Entity, I extends Identity, S extends EntityState> {

    @Transactional(readOnly = true)
    Optional<E> findOne(I id);

    @Transactional(readOnly = true)
    List<E> findAll(Specification specification);

    @Transactional(readOnly = true)
    Page<E> findAll(Specification specification, Pageable pageable);

    @Transactional(readOnly = true)
    Page<E> findAll(Pageable pageable);

    @Transactional(readOnly = true)
    default List<E> findAll() {
        return findAll(Specifications.where(null));
    }

    E save(E entity);

    E save(I id, S state);

    void delete(I id);
}

