package edu.noia.myoffice.common.domain.vo;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Tariff extends Rate<Amount, Quantity> {

    private Tariff(Amount amount, Quantity quantity) {
        super(amount, quantity);
    }

    public static Tariff of(@NonNull Amount amount, @NonNull Quantity quantity) {
        return new Tariff(amount, quantity);
    }
}