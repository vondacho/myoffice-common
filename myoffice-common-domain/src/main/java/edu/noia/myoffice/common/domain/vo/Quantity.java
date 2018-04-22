package edu.noia.myoffice.common.domain.vo;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.BiFunction;

import static java.math.MathContext.DECIMAL32;

@EqualsAndHashCode(of = {"value", "unit"}, callSuper = false, doNotUseGetters = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Quantity {
    public static final Quantity ZERO = Quantity.of(0L, Unit.SAMPLE);

    private static final MathContext MATH_CONTEXT = DECIMAL32;

    @Getter
    protected BigDecimal value;
    @Getter
    protected Unit unit;

    private BiFunction<Quantity, Quantity, BigDecimal> plusOp = (q1, q2) ->
            q1.unit == q2.unit ? q1.value.add(q2.value) :
                    q1.value.add(q2.value.multiply(q2.unit.toDecimal()).divide(q1.unit.toDecimal(), MATH_CONTEXT));
    private BiFunction<Quantity, Quantity, BigDecimal> minusOp = (q1, q2) ->
            q1.unit == q2.unit ? q1.value.subtract(q2.value) :
                    q1.value.subtract(q2.value.multiply(q2.unit.toDecimal()).divide(q1.unit.toDecimal(), MATH_CONTEXT));
    private BiFunction<Quantity, BigDecimal, BigDecimal> timesOp = (q1, f) -> q1.value.multiply(f);

    public Quantity(@NonNull BigDecimal value, @NonNull Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public static Quantity from(@NonNull Quantity quantity) {
        return new Quantity(quantity.value, quantity.unit);
    }

    public static Quantity of(@NonNull Long value, @NonNull Unit unit) {
        return new Quantity(BigDecimal.valueOf(value), unit);
    }

    public static Quantity of(@NonNull String value, @NonNull Unit unit) {
        return new Quantity(new BigDecimal(value), unit);
    }

    public long toLong() {
        return value.longValue();
    }

    public BigDecimal toDecimal() {
        return value;
    }

    public String asString() {
        return value.stripTrailingZeros().toPlainString();
    }

    public <T extends Quantity> T plus(T other) {
        value = plusOp.apply(this, other);
        return (T) this;
    }

    public <T extends Quantity> T minus(T other) {
        value = minusOp.apply(this, other);
        return (T) this;
    }

    public <T extends Quantity> T times(long factor) {
        value = value.multiply(BigDecimal.valueOf(factor));
        return (T) this;
    }

    public <T extends Quantity> T times(BigDecimal factor) {
        value = this.value.multiply(factor);
        return (T) this;
    }

    public <T extends Quantity> T times(Quantity factor) {
        value = timesOp.apply(this, factor.toDecimal());
        return (T) this;
    }

    public <T extends Quantity> T neg() {
        value = value.negate();
        return (T) this;
    }

    public <T extends Quantity> T change(Unit target) {
        if (unit.compatibleTo(target)) {
            return times(unit.toDecimal().divide(target.toDecimal(), MATH_CONTEXT));
        }
        throw new IllegalArgumentException(String.format(
                "incompatible operand type; actual is %s, expected is %s", target.getFamily(), getUnit().getFamily()));
    }

    public <T extends Quantity> T iplus(T other) {
        return (T) of(plusOp.apply(this, other));
    }

    public <T extends Quantity> T iminus(T other) {
        return (T) of(minusOp.apply(this, other));
    }

    public <T extends Quantity> T itimes(long factor) {
        return (T) of(timesOp.apply(this, BigDecimal.valueOf(factor)));
    }

    public <T extends Quantity> T itimes(BigDecimal factor) {
        return (T) of(timesOp.apply(this, factor));
    }

    public <T extends Quantity> T itimes(Quantity factor) {
        return (T) of(timesOp.apply(this, factor.toDecimal()));
    }

    public <T extends Quantity> T ineg() {
        return (T) of(value.negate());
    }

    public <T extends Quantity> T ichange(Unit target) {
        if (unit.compatibleTo(target)) {
            return itimes(unit.toDecimal().divide(target.toDecimal(), MATH_CONTEXT));
        }
        throw new IllegalArgumentException(String.format(
                "incompatible operand type; actual is %s, expected is %s", target.getFamily(), getUnit().getFamily()));
    }

    public <T extends Quantity> T set(T quantity) {
        value = quantity.value;
        return (T) this;
    }

    public boolean gt(Quantity other) {
        return compareTo(other) > 0;
    }

    public boolean st(Quantity other) {
        return compareTo(other) < 0;
    }

    public boolean ge(Quantity other) {
        return compareTo(other) >= 0;
    }

    public boolean se(Quantity other) {
        return compareTo(other) <= 0;
    }

    public int compareTo(Quantity other) {
        return value.compareTo(other.value);
    }

    protected Quantity of(BigDecimal value) {
        return new Quantity(value, unit);
    }

}