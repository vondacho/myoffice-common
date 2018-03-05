package edu.noia.myoffice.common.domain.entity;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.common.domain.repository.EntityRepository;
import edu.noia.myoffice.common.domain.vo.Identity;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.List;

@ToString(of = {"id", "state"}, doNotUseGetters = true)
@EqualsAndHashCode(of = "id", callSuper = false)
@Accessors(chain = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class BaseEntity<E extends BaseEntity<E, I, S>, I extends Identity, S extends EntityState>
        implements Entity<E, I, S> {

    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    I id;
    @Setter(value = AccessLevel.PROTECTED)
    @NonNull
    S state;

    @Override
    public S getState() {
        return cloneState();
    }

    @Override
    public E modify(S modifier) {
        validate(modifier);
        state.modify(modifier);
        return (E) this;
    }

    @Override
    public E patch(S modifier) {
        state.patch(modifier);
        validate(state);
        return (E) this;
    }

    @Override
    public E save(EntityRepository<E, I, S> repository) {
        state = repository.save(id, state).state;
        return (E) this;
    }

    protected E andEvent(Event event) {
        state.andEvent(event);
        return (E) this;
    }

    public List<Event> domainEvents() {
        return Collections.unmodifiableList(state.domainEvents());
    }

    protected abstract S cloneState();
}
