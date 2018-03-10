package edu.noia.myoffice.common.domain.event;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Audit {

    List<Event> events = new ArrayList<>();

    public List<Event> all() {
        return Collections.unmodifiableList(events);
    }

    public void clear() {
        events.clear();
    }

    public Audit and(Event event) {
        events.add(event);
        return this;
    }

    public Event first() {
        return events.get(0);
    }

    public Event last() {
        return events.get(events.size() - 1);
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

