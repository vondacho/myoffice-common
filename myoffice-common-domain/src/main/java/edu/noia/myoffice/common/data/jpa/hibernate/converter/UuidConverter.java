package edu.noia.myoffice.common.data.jpa.hibernate.converter;

import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.UUID;

@Converter(autoApply = true)
public class UuidConverter implements AttributeConverter<UUID, String> {
    @Override
    public String convertToDatabaseColumn(UUID attribute) {
        return attribute != null ? attribute.toString() : null;
    }

    @Override
    public UUID convertToEntityAttribute(String dbData) {
        return StringUtils.hasText(dbData) ? UUID.fromString(dbData) : null;
    }
}
