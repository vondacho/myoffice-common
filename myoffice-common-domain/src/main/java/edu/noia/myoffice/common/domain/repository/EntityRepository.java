package edu.noia.myoffice.common.domain.repository;

import edu.noia.myoffice.common.domain.entity.Entity;
import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.common.domain.vo.Identity;
import edu.noia.myoffice.common.util.search.FindCriteria;

import java.util.List;
import java.util.Optional;

public interface EntityRepository<E extends Entity<E, I, S>, I extends Identity, S extends EntityState> {

    Optional<E> findOne(I id);

    List<E> findByCriteria(List<FindCriteria> criteria);

    default List<E> findAll() {
        return findByCriteria(FindCriteria.empty());
    }

    E save(I id, S state);

    default E save(Entity<E, I, S> entity) {
        return save(entity.getId(), entity.getState());
    }

    void delete(I id);
}

