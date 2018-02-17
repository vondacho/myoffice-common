package edu.noia.myoffice.common.domain.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEvent<T extends EventPayload> implements Event<T> {

    @NonNull
    T payload;
    @NonNull
    Instant timestamp;
}
