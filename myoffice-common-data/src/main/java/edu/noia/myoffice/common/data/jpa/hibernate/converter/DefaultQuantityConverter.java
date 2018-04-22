package edu.noia.myoffice.common.data.jpa.hibernate.converter;

import edu.noia.myoffice.common.domain.vo.Quantity;
import edu.noia.myoffice.common.domain.vo.Unit;

import javax.persistence.AttributeConverter;

public class DefaultQuantityConverter implements AttributeConverter<Quantity, Long> {
    @Override
    public Long convertToDatabaseColumn(Quantity attribute) {
        return attribute != null ? attribute.itimes(100L).toLong() : null;
    }

    @Override
    public Quantity convertToEntityAttribute(Long dbData) {
        return dbData != null ? Quantity.of(dbData, Unit.SAMPLE) : null;
    }
}
