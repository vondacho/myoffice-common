package edu.noia.myoffice.common.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
public class Amount extends Quantity {
    public static final Amount ZERO = Amount.ofCentimes(0L);

    private Amount(BigDecimal value) {
        super(value, Unit.CHF_CENT);
    }

    private Amount(long value) {
        this(BigDecimal.valueOf(value));
    }

    public static Amount from(@NonNull Amount amount) {
        return new Amount(amount.value);
    }

    public static Amount from(@NonNull Quantity quantity) {
        if (quantity.getUnit().getFamily() == Unit.Family.CURRENCY) {
            return new Amount(quantity.ichange(Unit.CHF_CENT).toLong());
        }
        throw new IllegalArgumentException(String.format(
                "incompatible operand type; actual is %s, expected is %s",
                quantity.getUnit().getFamily(), Unit.Family.CURRENCY));
    }

    public static Amount ofCentimes(@NonNull Long value) {
        return new Amount(value);
    }

    public static Amount ofFrancs(@NonNull Long value) {
        return new Amount(BigDecimal.valueOf(value).movePointRight(2));
    }

    public static Amount ofFrancs(@NonNull String value) {
        return new Amount(new BigDecimal(value).movePointRight(2));
    }

    public Long toCentimes() {
        return toLong();
    }

    public BigDecimal toFrancs() {
        return toDecimal().multiply(new BigDecimal("0.01"));
    }

    public String asCentimes() {
        return value.stripTrailingZeros().toPlainString();
    }

    public String asFrancs() {
        return value.movePointLeft(2).stripTrailingZeros().toPlainString();
    }

    protected Amount of(BigDecimal value) {
        return new Amount(value);
    }
}