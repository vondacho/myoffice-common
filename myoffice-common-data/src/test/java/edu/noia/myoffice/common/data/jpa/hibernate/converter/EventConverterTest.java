package edu.noia.myoffice.common.data.jpa.hibernate.converter;

import edu.noia.myoffice.common.domain.event.BaseEvent;
import edu.noia.myoffice.common.domain.event.Event;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EventConverterTest {

    @Test
    public void test() {
        Event event = BaseEvent.of(new Payload());
        EventConverter eventConverter = new EventConverter();
        String json = "payload\":{\"a\":1,\"b\":\"text\",\"c\":100}";
        assertThat(eventConverter.convertToDatabaseColumn(event)).contains(json);
    }

    @Getter
    @FieldDefaults(level = AccessLevel.PUBLIC)
    class Payload {
        int a = 1;
        String b = "text";
        Long c = 100L;
    }
}
