package edu.noia.myoffice.common.event.store;

import edu.noia.myoffice.common.domain.repository.EventStore;

import java.util.List;

public interface InternalEventStore extends EventStore {

    <T extends EventPublication> List<T> listPending100();
}
