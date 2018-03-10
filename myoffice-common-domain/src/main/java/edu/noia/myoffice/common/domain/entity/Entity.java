package edu.noia.myoffice.common.domain.entity;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.common.domain.repository.EntityRepository;
import edu.noia.myoffice.common.domain.vo.Identity;

import java.util.List;

public interface Entity<E extends Entity<E, I, S>, I extends Identity, S extends EntityState> {

    I getId();

    S getState();

    E modify(S modifier);

    E patch(S modifier);

    E save(EntityRepository<E, I, S> repository);

    default E save(EntityRepository<E, I, S> repository, EventPublisher publisher) {
        return save(repository).publishEvents(publisher);
    }

    List<Event> domainEvents();

    E publishEvents(EventPublisher publisher);

    void validate(S state);
}
