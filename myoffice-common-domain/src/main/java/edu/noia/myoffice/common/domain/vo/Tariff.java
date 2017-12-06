package edu.noia.myoffice.common.domain.vo;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Tariff extends Rate<Quantity, Amount> {

    public Tariff(Amount amount, Unit unit) {
        super(amount, unit);
    }
}