package edu.noia.myoffice.common.domain.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.MathContext;

import static java.math.MathContext.DECIMAL32;

@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Rate<V extends Quantity, D extends Quantity> {

    protected static final MathContext MATH_CONTEXT = DECIMAL32;

    @NonNull
    V quantity;
    @NonNull
    D by;

    public Unit getUnit() {
        return quantity.getUnit();
    }

    public Unit getTargetUnit() {
        return by.getUnit();
    }

    public V apply(long quantity) {
        return apply(Quantity.of(quantity, Unit.SAMPLE));
    }

    public V apply(String quantity) {
        return apply(Quantity.of(quantity, Unit.SAMPLE));
    }

    public <Q extends Quantity> V apply(Q quantity) {
        if (getTargetUnit().compatibleTo(quantity.getUnit())) {
            return this.quantity.itimes(
                    quantity.itimes(
                            quantity.getUnit().toDecimal().divide(
                                    by.toDecimal().multiply(getTargetUnit().toDecimal()), MATH_CONTEXT)));
        }
        throw new IllegalArgumentException(String.format(
                "incompatible operand type; actual is %s, expected is %s",
                quantity.getUnit().getFamily(), getTargetUnit().getFamily()));
    }
}