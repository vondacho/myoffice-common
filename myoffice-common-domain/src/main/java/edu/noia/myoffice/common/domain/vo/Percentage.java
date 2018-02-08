package edu.noia.myoffice.common.domain.vo;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Percentage extends Rate<Quantity, Quantity> {

    public static Percentage ZERO = new Percentage(Quantity.ZERO);
    public static Percentage HUNDRED = Percentage.of(100L);

    private Percentage(Quantity value) {
        super(value, Unit.PERCENTAGE);
    }

    public static Percentage of(Long value) {
        return new Percentage(new Quantity(value, Unit.SAMPLE));
    }

    public static Percentage of(String value) {
        return new Percentage(new Quantity(value, Unit.SAMPLE));
    }
}