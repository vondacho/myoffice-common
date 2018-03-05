package edu.noia.myoffice.common.data.jpa;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.common.event.store.EventPublication;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Instant;

import static edu.noia.myoffice.common.event.store.EventPublication.Status.PENDING;
import static edu.noia.myoffice.common.event.store.EventPublication.Status.SENT;

@Entity(name = "event_publication")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JpaEventPublication extends JpaBaseEntity implements EventPublication {

    @NonNull
    @Type(type = "json")
    @Column(columnDefinition = "json")
    Event payload;

    @NonNull
    @Enumerated(EnumType.STRING)
    EventPublication.Status status;

    Instant timestamp;

    public static JpaEventPublication of(Event payload) {
        return new JpaEventPublication(payload, PENDING);
    }

    public void publish(Instant at) {
        this.timestamp = at;
        this.status = SENT;
    }
}
