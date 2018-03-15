package edu.noia.myoffice.common.domain.event;

public interface EventPublisher {

    void publish(Event event);

    default void publish(EventPayload payload) {
        publish(BaseEvent.of(payload));
    }
}
