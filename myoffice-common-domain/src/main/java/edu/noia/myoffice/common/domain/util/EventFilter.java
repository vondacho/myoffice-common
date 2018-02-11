package edu.noia.myoffice.common.domain.util;

import edu.noia.myoffice.common.domain.event.Event;

import java.util.Optional;
import java.util.function.Function;

public interface EventFilter<T extends Event> extends Function<T, Optional<T>> {
}
