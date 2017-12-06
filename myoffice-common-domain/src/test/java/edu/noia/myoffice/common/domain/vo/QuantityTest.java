package edu.noia.myoffice.common.domain.vo;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class QuantityTest {

    @Test
    public void should_create_as_expected() {
        // given
        Quantity a = new Quantity(50L, Unit.CENTIMES);
        Quantity b = new Quantity("50.50", Unit.FRANCS);
        Quantity c = new Quantity("50.505", Unit.FRANCS);
        // when
        // then
        assertThat(a).isNotEqualTo(b);
        assertThat(a).isNotEqualTo(c);
        assertThat(a).isEqualTo(new Quantity(50L, Unit.CENTIMES));
        assertThat(b).isEqualTo(new Quantity("50.50", Unit.FRANCS));
    }

    @Test
    public void should_convert_to_long_as_expected() {
        // given
        Quantity a = new Quantity(50L, Unit.SAMPLE);
        Quantity b = new Quantity("50.50", Unit.SAMPLE);
        Quantity c = new Quantity("50.505", Unit.SAMPLE);
        // when
        // then
        assertThat(a.toLong()).isEqualTo(50L);
        assertThat(b.toLong()).isEqualTo(50L);
        assertThat(c.toLong()).isEqualTo(50L);
    }

    @Test
    public void should_convert_to_decimal_as_expected() {
        // given
        Quantity a = new Quantity(50L, Unit.SAMPLE);
        Quantity b = new Quantity("50.50", Unit.SAMPLE);
        Quantity c = new Quantity("50.505", Unit.SAMPLE);
        // when
        // then
        assertThat(a.toDecimal().stripTrailingZeros().toPlainString()).isEqualTo("50");
        assertThat(b.toDecimal().stripTrailingZeros().toPlainString()).isEqualTo("50.5");
        assertThat(c.toDecimal().stripTrailingZeros().toPlainString()).isEqualTo("50.505");
    }

    @Test
    public void should_convert_to_string_as_expected() {
        // given
        Quantity a = new Quantity(50L, Unit.SAMPLE);
        Quantity b = new Quantity("50.50", Unit.SAMPLE);
        Quantity c = new Quantity("50.505", Unit.SAMPLE);
        // when
        // then
        assertThat(a.asString()).isEqualTo("50");
        assertThat(b.asString()).isEqualTo("50.5");
        assertThat(c.asString()).isEqualTo("50.505");
    }

    @Test
    public void should_add_as_expected() {
        // given
        Quantity a = new Quantity(50L, Unit.CENTIMES);
        Quantity b = new Quantity(100L, Unit.CENTIMES);
        Quantity c = new Quantity(150L, Unit.CENTIMES);
        Quantity d = new Quantity(1L, Unit.FRANCS);
        // when
        // then
        assertThat(a.plus(b).toLong()).isEqualTo(150L);
        assertThat(b.plus(a).toLong()).isEqualTo(150L);
        assertThat(a.plus(b).plus(c).toLong()).isEqualTo(300L);
        assertThat(a.plus(b.plus(c)).toLong()).isEqualTo(300L);
        assertThat(a.plus(d).toLong()).isEqualTo(150L);
    }

    @Test
    public void should_subtract_as_expected() {
        // given
        Quantity a = new Quantity(50L, Unit.CENTIMES);
        Quantity b = new Quantity(100L, Unit.CENTIMES);
        Quantity d = new Quantity(1L, Unit.FRANCS);
        // when
        // then
        assertThat(a.minus(b).toLong()).isEqualTo(-50L);
        assertThat(b.minus(a).toLong()).isEqualTo(50L);
        assertThat(a.minus(d).toLong()).isEqualTo(-50L);
    }

    @Test
    public void should_multiply_as_expected() {
        // given
        Quantity a = new Quantity(50L, Unit.CENTIMES);
        // when
        Quantity d = a.times(10L);
        Quantity e = a.times(new BigDecimal("10.5"));
        // then
        assertThat(d.toLong()).isEqualTo(500L);
        assertThat(e.toLong()).isEqualTo(525L);
    }
}
