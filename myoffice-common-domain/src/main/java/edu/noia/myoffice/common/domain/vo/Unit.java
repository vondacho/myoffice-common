package edu.noia.myoffice.common.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static edu.noia.myoffice.common.domain.vo.Unit.Family.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Unit {
    HOUR(60L, TIME),
    MINUTE(1L, TIME),
    SAMPLE(1L, OTHER),
    KILO(1000L, WEIGHT),
    GRAM(1L, WEIGHT),
    CHF(100L, CURRENCY),
    CHF_CENT(1L, CURRENCY),
    LITER(1000L, VOLUME),
    CUBIC_METER(1L, VOLUME),
    KILOMETER(1000L, DISTANCE),
    METER(1L, DISTANCE);

    BigDecimal factor;
    @Getter
    Family family;

    Unit(@NonNull Long factor, @NonNull Family family) {
        this.factor = BigDecimal.valueOf(factor);
        this.family = family;
    }

    public long toLong() {
        return factor.longValue();
    }

    public BigDecimal toDecimal() {
        return factor;
    }

    public boolean compatibleTo(Unit other) {
        return family == other.family;
    }

    enum Family {
        WEIGHT(KILO, GRAM),
        CURRENCY(CHF, CHF_CENT),
        VOLUME(LITER, CUBIC_METER),
        TIME(HOUR, MINUTE),
        DISTANCE(KILOMETER, METER),
        OTHER(SAMPLE);

        @NonNull
        Unit[] members;

        Family(Unit... members) {
            this.members = members;
        }
    }
}
