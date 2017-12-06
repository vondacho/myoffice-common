package edu.noia.myoffice.common.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.function.BiFunction;

@EqualsAndHashCode(of = {"value", "unit"}, callSuper = false, doNotUseGetters = true)
public class Quantity {
    public static final Quantity ZERO = new Quantity(0L, Unit.SAMPLE);

    protected BigDecimal value;
    @Getter
    protected Unit unit;

    public Quantity(@NonNull BigDecimal value, @NonNull Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public Quantity(@NonNull Long value, @NonNull Unit unit) {
        this(BigDecimal.valueOf(value), unit);
    }

    public Quantity(@NonNull String value, @NonNull Unit unit) {
        this(new BigDecimal(value), unit);
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
        return (T) of(plusOp.apply(this, other));
    }

    public <T extends Quantity> T minus(T other) {
        return (T) of(minusOp.apply(this, other));
    }

    public <T extends Quantity> T times(long factor) {
        return (T) of(timesOp.apply(this, BigDecimal.valueOf(factor)));
    }

    public <T extends Quantity> T times(BigDecimal factor) {
        return (T) of(timesOp.apply(this, factor));
    }

    public <T extends Quantity> T times(Quantity factor) {
        return (T) of(timesOp.apply(this, factor.toDecimal()));
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

    public MutableQuantity toMutable() {
        return new MutableQuantity(this);
    }

    protected Quantity of(BigDecimal value) {
        return new Quantity(value, unit);
    }

    protected BiFunction<Quantity, Quantity, BigDecimal> plusOp = (q1, q2) ->
            q1.unit == q2.unit ?
                    q1.value.add(q2.value) : q1.unit.isBasis() ?
                    q1.value.add(q2.value.multiply(q2.unit.toDecimal())) :
                    q1.value.add(q2.value.multiply(q2.unit.toDecimal()).divide(q1.unit.toDecimal()));

    protected BiFunction<Quantity, Quantity, BigDecimal> minusOp = (q1, q2) ->
            q1.unit == q2.unit ?
                    q1.value.subtract(q2.value) : q1.unit.isBasis() ?
                    q1.value.subtract(q2.value.multiply(q2.unit.toDecimal())) :
                    q1.value.subtract(q2.value.multiply(q2.unit.toDecimal()).divide(q1.unit.toDecimal()));

    protected BiFunction<Quantity, BigDecimal, BigDecimal> timesOp = (q1, f) -> q1.value.multiply(f);

}