package edu.noia.myoffice.common.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode(callSuper = true)
public class Tariff extends Rate<Quantity, Amount> {

    private Tariff(Amount amount, Unit unit) {
        super(amount, unit);
    }

    public static Tariff of(@NonNull Amount amount, @NonNull Unit unit) {
        return new Tariff(amount, unit);
    }
}