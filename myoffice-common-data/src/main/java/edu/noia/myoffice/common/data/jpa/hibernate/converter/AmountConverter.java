package edu.noia.myoffice.common.data.jpa.hibernate.converter;

import edu.noia.myoffice.common.domain.vo.Amount;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AmountConverter implements AttributeConverter<Amount, Long> {
    @Override
    public Long convertToDatabaseColumn(Amount attribute) {
        return attribute != null ? attribute.toCentimes() : null;
    }

    @Override
    public Amount convertToEntityAttribute(Long dbData) {
        return dbData != null ? Amount.ofCentimes(dbData) : null;
    }
}
