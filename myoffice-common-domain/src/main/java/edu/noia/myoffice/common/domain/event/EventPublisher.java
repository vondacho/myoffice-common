package edu.noia.myoffice.common.domain.event;

import java.util.function.Consumer;

public interface EventPublisher extends Consumer<Event> {
}
