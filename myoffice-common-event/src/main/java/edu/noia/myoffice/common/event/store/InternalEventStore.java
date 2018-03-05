package edu.noia.myoffice.common.event.store;

import edu.noia.myoffice.common.domain.event.Event;

import java.util.List;
import java.util.function.Consumer;

public interface InternalEventStore extends Consumer<Event> {

    <T extends EventPublication> List<T> listPending100();
}
