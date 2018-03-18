package edu.noia.myoffice.common.data.jpa;

import edu.noia.myoffice.common.event.store.EventPublication;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Instant;

import static edu.noia.myoffice.common.event.store.EventPublication.Status.PENDING;
import static edu.noia.myoffice.common.event.store.EventPublication.Status.SENT;

@Entity(name = "event_publication")
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JpaEventPublication extends JpaBaseEntity implements EventPublication {

    @NonNull
    @Column(name = "eventName")
    String name;

    @NonNull
    @Column(name = "event", length = 2048)
    String payload;

    @NonNull
    Instant eventTimestamp;

    Instant timestamp;

    @Enumerated(EnumType.STRING)
    EventPublication.Status status = PENDING;

    public void publish(Instant at) {
        this.timestamp = at;
        this.status = SENT;
    }
}
