package edu.noia.myoffice.common.domain.repository;

import edu.noia.myoffice.common.domain.entity.Entity;
import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.common.domain.vo.Identity;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InMemoryKeyValueRepository<E extends Entity, I extends Identity, S extends EntityState> {

    HashMap<I,E> store = new HashMap<>();
    @NonNull
    BiFunction<I,S,E> toEntityFunction;

    public Optional<E> findOne(I id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<E> findAll(Specification specification) {
        return new ArrayList(store.values());
    }

    public Page<E> findAll(Specification specification, Pageable pageable) {
        return findAll(pageable);
    }

    public Page<E> findAll(Pageable pageable) {
        List<E> values = new ArrayList<>(store.values());
        return new PageImpl(values, pageable, values.size());
    }

    public E save(E entity) {
        store.put((I) entity.getId(), entity);
        return entity;
    }

    public E save(I id, S state) {
        return save(toEntityFunction.apply(id, state));
    }

    public void delete(I id) {
        store.remove(id);
    }
}