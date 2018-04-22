package edu.noia.myoffice.common.data.jpa.hibernate.converter;

import edu.noia.myoffice.common.domain.vo.Percentage;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;

@Converter(autoApply = true)
public class PercentageConverter implements AttributeConverter<Percentage, BigDecimal> {
    @Override
    public BigDecimal convertToDatabaseColumn(Percentage attribute) {
        return attribute != null ? attribute.getValue().itimes(100L).getValue() : null;
    }

    @Override
    public Percentage convertToEntityAttribute(BigDecimal dbData) {
        return dbData != null ? Percentage.of(dbData.stripTrailingZeros().toPlainString()) : null;
    }
}
