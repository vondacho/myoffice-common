package edu.noia.myoffice.common.data.jpa.hibernate.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.noia.myoffice.common.domain.event.BaseEvent;
import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.common.serializer.CommonSerializers;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

@Converter(autoApply = true)
public class EventConverter implements AttributeConverter<Event, String> {

    private ObjectMapper objectMapper;

    public EventConverter() {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(CommonSerializers.getModule());
    }

    @Override
    public String convertToDatabaseColumn(Event attribute) {
        try {
            return attribute != null ? objectMapper.writeValueAsString(attribute) : null;
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public Event convertToEntityAttribute(String dbData) {
        try {
            return StringUtils.hasText(dbData) ? objectMapper.readValue(dbData, BaseEvent.class) : null;
        } catch (IOException e) {
            return null;
        }
    }
}
