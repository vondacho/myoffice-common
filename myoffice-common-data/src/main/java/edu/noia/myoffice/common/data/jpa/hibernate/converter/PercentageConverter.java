package edu.noia.myoffice.common.data.jpa.hibernate.converter;

import edu.noia.myoffice.common.domain.vo.Percentage;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PercentageConverter implements AttributeConverter<Percentage, String> {
    @Override
    public String convertToDatabaseColumn(Percentage attribute) {
        return attribute != null ? attribute.getValue().asString() : null;
    }

    @Override
    public Percentage convertToEntityAttribute(String dbData) {
        return StringUtils.hasText(dbData) ? Percentage.of(dbData) : null;
    }
}
