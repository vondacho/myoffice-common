package edu.noia.myoffice.common.domain.event;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEvent<T> implements Event<T> {

    @NonNull
    T payload;

    Instant timestamp;

    public static <T> BaseEvent of(T payload) {
        return of(payload, Instant.now());
    }
}
