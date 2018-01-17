package edu.noia.myoffice.common.domain.command;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class BaseCommand implements Command {
    @NonNull
    String name;
    @NonNull
    LocalDateTime timestamp;
}
