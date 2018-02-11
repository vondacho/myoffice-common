package edu.noia.myoffice.common.domain.util;

import edu.noia.myoffice.common.domain.event.Event;

import java.util.function.Function;

public interface EventProcessor<T extends Event, U extends Event> extends Function<T, U> {
}
