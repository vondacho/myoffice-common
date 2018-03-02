package edu.noia.myoffice.common.domain.entity;

import edu.noia.myoffice.common.domain.event.Event;

import java.util.Collections;
import java.util.List;

public interface EntityState {

    EntityState modify(EntityState modifier);

    EntityState patch(EntityState modifier);

    default EntityState andEvent(Event event) {
        return this;
    }

    default List<Event> domainEvents() {
        return Collections.emptyList();
    }
}
