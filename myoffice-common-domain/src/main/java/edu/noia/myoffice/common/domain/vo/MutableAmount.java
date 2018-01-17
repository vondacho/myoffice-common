package edu.noia.myoffice.common.domain.vo;

public class MutableAmount extends MutableQuantity {
    public static final MutableAmount ZERO = Amount.ZERO.toMutable();

    public MutableAmount(Amount amount) {
        super(amount);
    }

    public MutableAmount toMutable() {
        return this;
    }

    public Amount toImmutable() {
        return Amount.ofCentimes(this.toLong());
    }

    public MutableAmount negate() {
        value = value.negate();
        return this;
    }

}
