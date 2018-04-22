package edu.noia.myoffice.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import edu.noia.myoffice.common.domain.vo.*;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import static edu.noia.myoffice.common.util.converter.Converters.toLocalDateTimeUTC;

public class CommonSerializers {

    private CommonSerializers() {
    }

    public static Module getModule() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(UUID.class, new UUIDDeserializer());
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        module.addDeserializer(Instant.class, new InstantDeserializer());
        module.addDeserializer(Amount.class, new AmountDeserializer());
        module.addDeserializer(Percentage.class, new PercentageDeserializer());
        module.addDeserializer(Quantity.class, new QuantityDeserializer());
        module.addDeserializer(Tariff.class, new TariffDeserializer());
        module.addSerializer(UUID.class, new UUIDSerializer());
        module.addSerializer(LocalDate.class, new LocalDateSerializer());
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        module.addSerializer(Instant.class, new InstantSerializer());
        module.addSerializer(Amount.class, new AmountSerializer());
        module.addSerializer(Percentage.class, new PercentageSerializer());
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
        public void serialize(LocalDateTime localDateTime, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (localDateTime != null) {
                gen.writeString(localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
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

    public static class InstantSerializer extends JsonSerializer<Instant> {
        final JsonSerializer<LocalDateTime> localDateTimeSerializer = new LocalDateTimeSerializer();

        @Override
        public void serialize(Instant instant, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            localDateTimeSerializer.serialize(toLocalDateTimeUTC(instant), gen, serializers);
        }
    }

    public static class InstantDeserializer extends JsonDeserializer<Instant> {
        @Override
        public Instant deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
            return Optional.ofNullable(p.readValueAs(String.class))
                    .filter(StringUtils::hasText)
                    .map(Instant::parse)
                    .orElse(null);
        }
    }

    public static class AmountSerializer extends JsonSerializer<Amount> {
        @Override
        public void serialize(Amount amount, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (amount != null) {
                serializers.findValueSerializer(Quantity.class)
                        .serialize(Quantity.of(amount.asFrancs(), Unit.CHF), gen, serializers);
            }
        }
    }

    public static class AmountDeserializer extends JsonDeserializer<Amount> {
        @Override
        public Amount deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
            return Optional.ofNullable(ctx.readValue(p, Quantity.class))
                    .map(Amount::from)
                    .orElse(null);
        }
    }

    public static class PercentageSerializer extends JsonSerializer<Percentage> {
        @Override
        public void serialize(Percentage percentage, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (percentage != null) {
                gen.writeString(percentage.getValue().itimes(100L).asString());
            }
        }
    }

    public static class PercentageDeserializer extends JsonDeserializer<Percentage> {
        @Override
        public Percentage deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
            return Optional.ofNullable(p.readValueAs(String.class))
                    .filter(StringUtils::hasText)
                    .map(Percentage::of)
                    .orElse(null);
        }
    }

    public static class QuantityDeserializer extends JsonDeserializer<Quantity> {
        @Override
        public Quantity deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
            JsonNode node = p.getCodec().readTree(p);
            return Optional.ofNullable(node.get("value"))
                    .map(JsonNode::textValue)
                    .flatMap(value -> Optional.ofNullable(node.get("unit"))
                            .map(JsonNode::textValue)
                            .map(Unit::valueOf)
                            .map(unit -> Quantity.of(value, unit)))
                    .orElse(null);
        }
    }

    public static class TariffDeserializer extends JsonDeserializer<Tariff> {
        @Override
        public Tariff deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
            return Optional.ofNullable(ctx.readValue(p, Rate.class))
                    .map(rate -> Tariff.of(Amount.from(rate.getQuantity()), rate.getBy()))
                    .orElse(null);
        }
    }
}
