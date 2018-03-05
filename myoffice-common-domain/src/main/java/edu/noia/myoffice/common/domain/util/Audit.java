package edu.noia.myoffice.common.domain.util;

import edu.noia.myoffice.common.domain.event.Event;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Audit {

    List<Event> events;

    public Audit(Event event) {
        events = new ArrayList<>();
        events.add(event);
    }

    public void add(Event event) {
        events.add(event);
    }

    public Event first() {
        return events.get(0);
    }

    public Event last() {
        return events.get(events.size()-1);
    }

    public List<Event> between(Instant from, Instant to) {
        return events.stream()
                .filter(event -> event.getTimestamp().isAfter(from) && event.getTimestamp().isBefore(to))
                .collect(toList());
    }

    public List<Event> from(Instant from) {
        return events.stream()
                .filter(event -> event.getTimestamp().isAfter(from))
                .collect(toList());
    }
    public List<Event> until(Instant to) {
        return events.stream()
                .filter(event -> event.getTimestamp().isBefore(to))
                .collect(toList());
    }
}
