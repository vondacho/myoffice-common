package edu.noia.myoffice.common.rest.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import edu.noia.myoffice.common.domain.vo.Amount;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
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

    public static class AmountSerializer extends JsonSerializer<Amount> {
        @Override
        public void serialize(Amount amount, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (amount != null) {
                gen.writeString(amount.asFrancs());
            }
        }
    }

    public static class AmountDeserializer extends JsonDeserializer<Amount> {
        @Override
        public Amount deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
            return Optional.ofNullable(p.readValueAs(String.class))
                    .filter(StringUtils::hasText)
                    .map(Amount::ofFrancs)
                    .orElse(null);
        }
    }
}
