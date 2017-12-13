package edu.noia.myoffice.common.domain.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@FieldDefaults(level = AccessLevel.PROTECTED)
public class BaseEvent implements Event {
    @NonNull
    LocalDateTime timestamp;
}
