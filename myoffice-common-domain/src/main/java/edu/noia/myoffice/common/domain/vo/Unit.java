package edu.noia.myoffice.common.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Unit {
    HOUR(60L, false),
    MINUTE(1L, true),
    SAMPLE(1L, true),
    KILO(1000L, false),
    GRAM(1L, true),
    FRANCS(100L, false),
    CENTIMES(1L, true),
    PERCENTAGE(100L, false);

    Unit(@NonNull Long factor, boolean isBasis) {
        this.factor = BigDecimal.valueOf(factor);
        this.isBasis = isBasis;
    }

    Unit(@NonNull String factor, boolean isBasis) {
        this.factor = new BigDecimal(factor);
        this.isBasis = isBasis;
    }

    BigDecimal factor;
    @Getter
    boolean isBasis;

    public long toLong() {
        return factor.longValue();
    }

    public BigDecimal toDecimal() {
        return factor;
    }
}
