package edu.noia.myoffice.common.data.jpa.hibernate.converter;

import edu.noia.myoffice.common.domain.vo.Amount;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AmountConverter implements AttributeConverter<Amount, String> {
    @Override
    public String convertToDatabaseColumn(Amount attribute) {
        return attribute != null ? attribute.toCentimes().toString() : null;
    }

    @Override
    public Amount convertToEntityAttribute(String dbData) {
        return StringUtils.hasText(dbData) ? Amount.ofCentimes(Long.valueOf(dbData)) : null;
    }
}
