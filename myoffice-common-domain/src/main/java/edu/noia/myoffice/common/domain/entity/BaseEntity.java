package edu.noia.myoffice.common.domain.entity;

import edu.noia.myoffice.common.domain.vo.Identity;
import edu.noia.myoffice.common.domain.repository.EntityRepository;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class BaseEntity<
        E extends BaseEntity,
        I extends Identity,
        S extends EntityState,
        M extends EntityMutableState,
        R extends EntityRepository> implements Entity<E, I, S, R> {

    @Getter
    I id;
    @NonNull
    S state;

    protected E setId(@NonNull I id) {
        this.id = id;
        return (E)this;
    }

    @Override
    public S getState() {
        return toImmutableState(state);
    }

    protected E setState(@NonNull S state) {
        this.state = state;
        return (E)this;
    }

    @Override
    public E modify(S modifier) {
        modifier = validate(modifier);
        return setState((S)toMutable().modify(modifier));
    }

    @Override
    public E patch(S modifier) {
        return setState(validate((S)toMutable(state).patch(modifier)));
    }

    @Override
    public E save(R repository) {
        return (E)repository.save(id, state);
    }

    protected M toMutable() {
        return toMutable(state);
    }

    protected M toMutable(S state) {
        return state instanceof EntityMutableState ? (M)state : toMutableState(state);
    }

    protected abstract M toMutableState(S state);

    protected abstract S toImmutableState(S state);

    protected abstract I identify();

    protected abstract S validate(S state);
}
