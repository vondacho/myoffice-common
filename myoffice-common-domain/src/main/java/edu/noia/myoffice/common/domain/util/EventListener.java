package edu.noia.myoffice.common.domain.util;

import edu.noia.myoffice.common.domain.event.Event;

import java.util.function.Consumer;

public interface EventListener<T extends Event> extends Consumer<T> {
}
