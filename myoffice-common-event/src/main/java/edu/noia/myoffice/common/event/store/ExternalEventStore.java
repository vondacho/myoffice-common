package edu.noia.myoffice.common.event.store;

import edu.noia.myoffice.common.domain.event.Event;

import java.util.function.Consumer;

public interface ExternalEventStore extends Consumer<Event> {
}
