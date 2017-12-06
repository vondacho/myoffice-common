package edu.noia.myoffice.common.domain.vo;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Percentage extends Rate<Quantity, Quantity> {

    public Percentage(Quantity value) {
        super(value, Unit.PERCENTAGE);
    }
}