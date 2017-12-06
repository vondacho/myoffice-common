package edu.noia.myoffice.common.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
public class Amount extends Quantity {
    public static final Amount ZERO = Amount.ofCentimes(0L);

    private Amount(long value) {
        super(value, Unit.CENTIMES);
    }

    private Amount (BigDecimal value) {
        super(value, Unit.CENTIMES);
    }

    public static Amount of(@NonNull Amount amount) {
        return new Amount(amount.value);
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

    public MutableAmount toMutable() {
        return new MutableAmount(this);
    }

    protected Amount of(BigDecimal value) {
        return new Amount(value);
    }
}