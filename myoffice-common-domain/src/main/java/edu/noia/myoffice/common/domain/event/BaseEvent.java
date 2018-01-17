package edu.noia.myoffice.common.domain.event;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class BaseEvent implements Event {
    @NonNull
    LocalDateTime timestamp;
}
