package edu.noia.myoffice.common.domain.event;

import java.time.Instant;

public interface Event<T> {

    T getPayload();

    Instant getTimestamp();
}
