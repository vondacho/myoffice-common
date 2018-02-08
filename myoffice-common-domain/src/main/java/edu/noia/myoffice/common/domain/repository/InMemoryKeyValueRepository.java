package edu.noia.myoffice.common.domain.repository;

import edu.noia.myoffice.common.domain.entity.Entity;
import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.common.domain.vo.Identity;
import edu.noia.myoffice.common.util.search.FindCriteria;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InMemoryKeyValueRepository<E extends Entity<I,S>, I extends Identity, S extends EntityState>
        implements EntityRepository<E,I,S> {

    HashMap<I,E> store = new HashMap<>();
    @NonNull
    BiFunction<I,S,E> toEntityFunction;

    public Optional<E> findOne(I id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<E> findByCriteria(List<FindCriteria> criteria) {
        return new ArrayList(store.values());
    }

    public E save(E entity) {
        store.put(entity.getId(), entity);
        return entity;
    }

    public E save(I id, S state) {
        return save(toEntityFunction.apply(id, state));
    }

    public void delete(I id) {
        store.remove(id);
    }
}