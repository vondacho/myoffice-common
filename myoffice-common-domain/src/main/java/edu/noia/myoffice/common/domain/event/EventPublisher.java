package edu.noia.myoffice.common.domain.event;

public interface EventPublisher {
    void publish(Event event);
}
