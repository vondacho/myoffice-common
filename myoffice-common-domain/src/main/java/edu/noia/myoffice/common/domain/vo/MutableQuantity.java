package edu.noia.myoffice.common.domain.vo;

import java.math.BigDecimal;

public class MutableQuantity extends Quantity {

    public MutableQuantity(Long value, Unit unit) {
        super(value, unit);
    }

    public MutableQuantity(String value, Unit unit) {
        super(value, unit);
    }

    public MutableQuantity(Quantity quantity) {
        super(quantity.value, quantity.unit);
    }

    public <T extends Quantity> T plus(T other) {
        value = plusOp.apply(this, other);
        return (T) this;
    }

    public <T extends Quantity> T minus(T other) {
        value = minusOp.apply(this, other);
        return (T) this;
    }

    public <T extends Quantity> T times(long factor) {
        value = value.multiply(BigDecimal.valueOf(factor));
        return (T) this;
    }

    public <T extends Quantity> T times(BigDecimal factor) {
        value = this.value.multiply(factor);
        return (T) this;
    }

    public <T extends Quantity> T times(Quantity factor) {
        value = timesOp.apply(this, factor.toDecimal());
        return (T) this;
    }

    public MutableQuantity toMutable() {
        return this;
    }

}
