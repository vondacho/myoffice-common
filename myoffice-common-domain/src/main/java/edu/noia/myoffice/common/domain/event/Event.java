package edu.noia.myoffice.common.domain.event;

import java.time.Instant;

public interface Event<T> {

    T getPayload();

    default String getName() {
        return getPayload().getClass().getCanonicalName();
    }

    Instant getTimestamp();
}
