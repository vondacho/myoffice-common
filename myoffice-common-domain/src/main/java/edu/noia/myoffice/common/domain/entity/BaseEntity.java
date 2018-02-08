package edu.noia.myoffice.common.domain.entity;

import edu.noia.myoffice.common.domain.repository.EntityRepository;
import edu.noia.myoffice.common.domain.vo.Identity;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(of = "id", callSuper = false)
@Accessors(chain = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class BaseEntity<I extends Identity, S extends EntityState, M extends EntityMutableState>
        implements Entity<I, S> {

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    I id;
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    M state;

    @Override
    public S getState() {
        return toImmutableState();
    }

    @Override
    public Entity<I, S> modify(S modifier) {
        validate(modifier);
        state.modify(modifier);
        return this;
    }

    @Override
    public Entity<I, S> patch(S modifier) {
        state.patch(modifier);
        validate((S)state);
        return this;
    }

    @Override
    public <R extends EntityRepository<Entity<I, S>, I, S>> Entity<I, S> save(R repository) {
        return repository.save(id, (S)state);
    }

    protected abstract S toImmutableState();
}
