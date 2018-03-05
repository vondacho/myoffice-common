package edu.noia.myoffice.common.data.adapter;

import edu.noia.myoffice.common.data.jpa.JpaEventPublication;
import edu.noia.myoffice.common.data.jpa.JpaEventPublicationRepository;
import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.common.event.store.EventPublication;
import edu.noia.myoffice.common.event.store.InternalEventStore;
import edu.noia.myoffice.common.util.processor.Processor;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RdbmsEventStoreAdapter implements InternalEventStore {

    private static final Processor<Event, JpaEventPublication> toPersistentEvent =
            event -> Optional.of(JpaEventPublication.of(event));
    @NonNull
    JpaEventPublicationRepository repository;

    @Override
    public void accept(Event event) {
        toPersistentEvent.apply(event).ifPresent(repository::save);
    }

    @Override
    public List<JpaEventPublication> listPending100() {
        return repository.findTop100ByStatus(EventPublication.Status.PENDING);
    }
}
