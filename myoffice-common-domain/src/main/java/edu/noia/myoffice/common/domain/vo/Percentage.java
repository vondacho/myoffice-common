package edu.noia.myoffice.common.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Percentage {

    private static final BigDecimal HUNDRED_FACTOR = new BigDecimal("0.01");

    @NonNull
    Quantity value;

    public static Percentage from(Percentage percentage) {
        return new Percentage(Quantity.from(percentage.getValue()));
    }

    public static Percentage of(long value) {
        return new Percentage(Quantity.of(value, Unit.SAMPLE).times(HUNDRED_FACTOR));
    }

    public static Percentage of(String value) {
        return new Percentage(Quantity.of(value, Unit.SAMPLE).times(HUNDRED_FACTOR));
    }

    public <Q extends Quantity> Q apply(Q quantity) {
        return quantity.times(value);
    }

    public <Q extends Quantity> Q iapply(Q quantity) {
        return quantity.itimes(value);
    }
}