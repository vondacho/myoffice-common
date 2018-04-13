package edu.noia.myoffice.common.domain.entity;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.common.domain.repository.EntityRepository;
import edu.noia.myoffice.common.domain.vo.Identity;

import java.util.List;

/**
 * This interface specifies the minimal api of a domain entity, basically composed of an identity and a state.
 *
 * @param <E> Any class that implements {@link Entity}
 * @param <I> Any class that implements {@link Identity}
 * @param <S> Any class that implements {@link EntityState}
 */
public interface Entity<E extends Entity<E, I, S>, I extends Identity, S extends EntityState> {

    /**
     * @return the identity of the entity
     */
    I getId();

    /**
     * @return a snapshot of the current state hosted by the entity
     */
    S getState();

    /**
     * Replaces the attributes values of the entity state by the ones contained inside the given modifier state
     *
     * @param modifier instance of {@link EntityState}
     * @return the entity instance
     */
    E modify(S modifier);

    /**
     * Replaces the attributes values of the entity state by the non-null ones contained inside the given modifier state
     *
     * @param modifier instance of {@link EntityState}
     * @return the entity instance
     */
    E patch(S modifier);

    /**
     * Validates the attributes values of the given entity state
     *
     * @param state instance of {@link EntityState}
     */
    void validate(S state);

    /**
     * Saves the entity state using the given entity repository
     *
     * @param repository instance of {@link EntityRepository} dedicated to the implementing entity class
     * @return the entity instance
     */
    E save(EntityRepository<E, I, S> repository);

    /**
     * Saves the entity state using the given entity repository and publish the domain events emitted
     * when invoking the entity methods
     *
     * @param repository instance of {@link EntityRepository} dedicated to the implementing entity class
     * @param publisher  instance of {@link EventPublisher} responsible for publishing the domain events
     * @return the entity instance
     */
    default E save(EntityRepository<E, I, S> repository, EventPublisher publisher) {
        return save(repository).publishEvents(publisher);
    }

    /**
     * @return the list of domain {@link Event} instances emitted and accumulated when invoking the entity methods
     */
    List<Event> getRegisteredEvents();

    /**
     * Publish the domain {@link Event} instances emitted and accumulated when invoking the entity methods
     *
     * @param publisher instance of {@link EventPublisher}
     * @return the entity instance
     */
    E publishEvents(EventPublisher publisher);
}
