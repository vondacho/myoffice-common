package edu.noia.myoffice.common.data.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.noia.myoffice.common.data.jpa.JpaEventPublication;
import edu.noia.myoffice.common.data.jpa.JpaEventPublicationRepository;
import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.common.event.store.EventPublication;
import edu.noia.myoffice.common.event.store.InternalEventStore;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RdbmsEventStoreAdapter implements InternalEventStore {

    @NonNull
    JpaEventPublicationRepository repository;
    @NonNull
    ObjectMapper objectMapper;

    @Override
    public void accept(Event event) {
        repository.save(JpaEventPublication.of(
                event.getName(),
                serializeEvent(event),
                event.getTimestamp()));
    }

    private String serializeEvent(Event event) {
        try {
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<JpaEventPublication> listPending100() {
        return repository.findTop100ByStatus(EventPublication.Status.PENDING);
    }
}
