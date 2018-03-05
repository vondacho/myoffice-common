package edu.noia.myoffice.common.event.scheduler;

import edu.noia.myoffice.common.event.store.EventPublication;
import edu.noia.myoffice.common.event.store.ExternalEventStore;
import edu.noia.myoffice.common.event.store.InternalEventStore;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublishPendingEventScheduler {

    @NonNull
    InternalEventStore eventStore;
    @NonNull
    ExternalEventStore publisher;

    @Scheduled(initialDelay = 3000, fixedDelayString = "${events.publisher.freq:3000}")
    public void sendEvents() {
        eventStore.listPending100().forEach(this::publish);
    }

    private void publish(EventPublication event) {
        publisher.accept(event);
        event.publish(Instant.now());
    }
}
