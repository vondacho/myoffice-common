package edu.noia.myoffice.common.rest.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@JsonComponent
public class CommonSerializers {

    public static class UUIDSerializer extends JsonSerializer<UUID> {
        @Override
        public void serialize(UUID uuid, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (uuid != null) {
                gen.writeString(uuid.toString());
            }
        }
    }

    public static class UUIDDeserializer extends JsonDeserializer<UUID> {
        @Override
        public UUID deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
            return Optional.ofNullable(p.readValueAs(String.class))
                    .filter(StringUtils::hasText)
                    .map(UUID::fromString)
                    .orElse(null);
        }
    }

    public static class LocalDateSerializer extends JsonSerializer<LocalDate> {
        @Override
        public void serialize(LocalDate localDate, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (localDate != null) {
                gen.writeString(localDate.format(DateTimeFormatter.ISO_DATE));
            }
        }
    }

    public static class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
            return Optional.ofNullable(p.readValueAs(String.class))
                    .filter(StringUtils::hasText)
                    .map(s -> LocalDate.parse(s, DateTimeFormatter.ISO_DATE))
                    .orElse(null);
        }
    }

    public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime localDate, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (localDate != null) {
                gen.writeString(localDate.format(DateTimeFormatter.ISO_DATE_TIME));
            }
        }
    }

    public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
            return Optional.ofNullable(p.readValueAs(String.class))
                    .filter(StringUtils::hasText)
                    .map(s -> LocalDateTime.parse(s, DateTimeFormatter.ISO_DATE_TIME))
                    .orElse(null);
        }
    }
}
