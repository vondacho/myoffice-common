package edu.noia.myoffice.common.domain.vo;

public class MutableAmount extends MutableQuantity {

    public MutableAmount(Long value, Unit unit) {
        super(value, unit);
    }

    public MutableAmount(String value, Unit unit) {
        super(value, unit);
    }

    public MutableAmount(Amount amount) {
        super(amount);
    }

    public MutableAmount toMutable() {
        return this;
    }
}
