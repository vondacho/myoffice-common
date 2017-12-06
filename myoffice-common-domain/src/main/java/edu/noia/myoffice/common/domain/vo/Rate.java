package edu.noia.myoffice.common.domain.vo;

import lombok.*;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Rate<Q extends Quantity, V extends Quantity> {
    @NonNull
    V value;
    @NonNull
    Unit unit;

    public V apply(long quantity) {
        return value.times(quantity).times(unit.toDecimal());
    }

    public V apply(BigDecimal quantity) {
        return value.times(quantity).times(unit.toDecimal());
    }

    public V apply(Q quantity) {
        return value.times(quantity).times(unit.toDecimal());
    }
}