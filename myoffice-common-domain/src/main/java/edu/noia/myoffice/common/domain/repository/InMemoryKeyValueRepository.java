package edu.noia.myoffice.common.domain.repository;

import edu.noia.myoffice.common.domain.entity.BaseEntity;
import edu.noia.myoffice.common.domain.entity.Entity;
import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.common.domain.vo.Identity;
import edu.noia.myoffice.common.util.search.FindCriteria;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InMemoryKeyValueRepository<E extends Entity<E, I, S>, I extends Identity, S extends EntityState> implements EntityRepository<E, I, S> {

    HashMap<I, S> store = new HashMap<>();

    BiFunction<I, S, E> entityCreator = (id, state) -> (E) new DefaultEntity(id, state);

    public InMemoryKeyValueRepository(@NonNull BiFunction<I, S, E> entityCreator) {
        this.entityCreator = entityCreator;
    }

    @Override
    public Optional<E> findOne(I id) {
        return Optional.ofNullable(store.get(id)).map(s -> entityCreator.apply(id, s));
    }

    @Override
    public List<E> findByCriteria(List<FindCriteria> criteria) {
        return store.entrySet().stream().map(e -> entityCreator.apply(e.getKey(), e.getValue())).collect(toList());
    }

    @Override
    public E save(I id, S state) {
        store.put(id, state);
        return entityCreator.apply(id, state);
    }

    @Override
    public void delete(I id) {
        store.remove(id);
    }

    private class DefaultEntity extends BaseEntity<DefaultEntity, I, S> {

        DefaultEntity(I identity, S state) {
            super(identity, state);
        }

        @Override
        protected S cloneState() {
            return (S) this;
        }

        @Override
        public void validate(S state) {
            throw new UnsupportedOperationException();
        }
    }
}