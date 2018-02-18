package edu.noia.myoffice.common.util.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Collections.singletonList;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Problem {

    String field;
    @NonNull
    String message;

    public static List<Problem> from(Exception exception) {
        return singletonList(new Problem(exception.getMessage()));
    }

    public static List<Problem> from(ConstraintViolationException exception) {
        return exception.getConstraintViolations()
                .stream()
                .map(violation ->
                        {
                            String field = StreamSupport.stream(violation.getPropertyPath().spliterator(), false)
                                    .map(Path.Node::getName)
                                    .filter(StringUtils::hasText)
                                    .findFirst().orElse(null);

                            return new Problem(field,
                                    violation.getMessage()
                            );
                        }
                ).collect(Collectors.toList());
    }
}
