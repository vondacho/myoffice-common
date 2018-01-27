package edu.noia.myoffice.common.domain.vo;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Percentage extends Rate<Quantity, Quantity> {

    public static Percentage ZERO = new Percentage(Quantity.ZERO);
    public static Percentage HUNDRED = new Percentage(new Quantity(100L, Unit.SAMPLE));

    public Percentage(Quantity value) {
        super(value, Unit.PERCENTAGE);
    }
}