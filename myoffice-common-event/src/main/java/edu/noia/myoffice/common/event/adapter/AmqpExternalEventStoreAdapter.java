package edu.noia.myoffice.common.event.adapter;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.common.event.store.ExternalEventStore;

public class AmqpExternalEventStoreAdapter implements ExternalEventStore {
    @Override
    public void accept(Event event) {

    }
}
