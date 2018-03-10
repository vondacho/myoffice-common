package edu.noia.myoffice.common.domain.repository;

import edu.noia.myoffice.common.domain.event.Event;

import java.util.function.Consumer;

public interface EventStore extends Consumer<Event> {
}
