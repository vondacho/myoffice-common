package edu.noia.myoffice.common.domain.vo;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Tariff extends Rate<Quantity, Amount> {

    private Tariff(Amount amount, Unit unit) {
        super(amount, unit);
    }

    public static Tariff of(@NonNull Amount amount, @NonNull Unit unit) {
        return new Tariff(amount, unit);
    }
}