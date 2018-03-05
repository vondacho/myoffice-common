package edu.noia.myoffice.common.domain.event;

import edu.noia.myoffice.common.util.exception.Problem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProblemEventPayload {
    @NonNull
    List<Problem> problems;
}
