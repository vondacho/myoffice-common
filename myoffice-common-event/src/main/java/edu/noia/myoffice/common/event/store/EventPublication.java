package edu.noia.myoffice.common.event.store;

import edu.noia.myoffice.common.domain.event.Event;

import java.time.Instant;

public interface EventPublication extends Event<Event> {

    Status getStatus();

    void publish(Instant at);

    enum Status {
        PENDING, SENT
    }
}
