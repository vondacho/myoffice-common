package edu.noia.myoffice.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.common.domain.vo.Quantity;
import edu.noia.myoffice.common.domain.vo.Unit;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

public class CommonSerializers {

    private CommonSerializers() {}

    public static Module getModule() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(UUID.class, new UUIDDeserializer());
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        module.addDeserializer(Amount.class, new AmountDeserializer());
        module.addDeserializer(Quantity.class, new QuantityDeserializer());
        module.addSerializer(UUID.class, new UUIDSerializer());
        module.addSerializer(LocalDate.class, new LocalDateSerializer());
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        module.addSerializer(Amount.class, new AmountSerializer());
        module.addSerializer(Quantity.class, new QuantitySerializer());
        return module;
    }

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

    public static class QuantitySerializer extends JsonSerializer<Quantity> {
        @Override
        public void serialize(Quantity quantity, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (quantity != null) {
                gen.writeString(quantity.asString());
            }
        }
    }

    public static class QuantityDeserializer extends JsonDeserializer<Quantity> {
        @Override
        public Quantity deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
            return Optional.ofNullable(p.readValueAs(String.class))
                    .filter(StringUtils::hasText)
                    .map(s -> new Quantity(s, Unit.SAMPLE))
                    .orElse(null);
        }
    }
}
