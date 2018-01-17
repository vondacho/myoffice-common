package edu.noia.myoffice.common.domain.entity;

import edu.noia.myoffice.common.domain.event.Event;

import java.util.List;

public interface EventSourcedEntityState extends EntityState {
    List<Event> getEvents();
}
