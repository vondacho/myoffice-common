package edu.noia.myoffice.common.domain.vo;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class QuantityTest {

    @Test
    public void should_create_as_expected() {
        // given
        Quantity a = Quantity.of(50L, Unit.CHF_CENT);
        Quantity b = Quantity.of("50.50", Unit.CHF);
        Quantity c = Quantity.of("50.505", Unit.CHF);
        // when
        // then
        assertThat(a).isNotEqualTo(b);
        assertThat(a).isNotEqualTo(c);
        assertThat(a).isEqualTo(Quantity.of(50L, Unit.CHF_CENT));
        assertThat(b).isEqualTo(Quantity.of("50.50", Unit.CHF));
    }

    @Test
    public void should_convert_to_long_as_expected() {
        // given
        Quantity a = Quantity.of(50L, Unit.SAMPLE);
        Quantity b = Quantity.of("50.50", Unit.SAMPLE);
        Quantity c = Quantity.of("50.505", Unit.SAMPLE);
        // when
        // then
        assertThat(a.toLong()).isEqualTo(50L);
        assertThat(b.toLong()).isEqualTo(50L);
        assertThat(c.toLong()).isEqualTo(50L);
    }

    @Test
    public void should_convert_to_decimal_as_expected() {
        // given
        Quantity a = Quantity.of(50L, Unit.SAMPLE);
        Quantity b = Quantity.of("50.50", Unit.SAMPLE);
        Quantity c = Quantity.of("50.505", Unit.SAMPLE);
        // when
        // then
        assertThat(a.toDecimal().stripTrailingZeros().toPlainString()).isEqualTo("50");
        assertThat(b.toDecimal().stripTrailingZeros().toPlainString()).isEqualTo("50.5");
        assertThat(c.toDecimal().stripTrailingZeros().toPlainString()).isEqualTo("50.505");
    }

    @Test
    public void should_convert_to_string_as_expected() {
        // given
        Quantity a = Quantity.of(50L, Unit.SAMPLE);
        Quantity b = Quantity.of("50.50", Unit.SAMPLE);
        Quantity c = Quantity.of("50.505", Unit.SAMPLE);
        // when
        // then
        assertThat(a.asString()).isEqualTo("50");
        assertThat(b.asString()).isEqualTo("50.5");
        assertThat(c.asString()).isEqualTo("50.505");
    }

    @Test
    public void should_add_in_an_immutable_way_as_expected() {
        // given
        Quantity a = Quantity.of(50L, Unit.CHF_CENT);
        Quantity b = Quantity.of(100L, Unit.CHF_CENT);
        Quantity c = Quantity.of(150L, Unit.CHF_CENT);
        Quantity d = Quantity.of(1L, Unit.CHF);
        // when
        // then
        assertThat(a.iplus(b).toLong()).isEqualTo(150L);
        assertThat(b.iplus(a).toLong()).isEqualTo(150L);
        assertThat(a.iplus(b).plus(c).toLong()).isEqualTo(300L);
        assertThat(a.iplus(b.plus(c)).toLong()).isEqualTo(300L);
        assertThat(a.iplus(d).toLong()).isEqualTo(150L);
        assertThat(d.iplus(a).asString()).isEqualTo("1.5");
    }

    @Test
    public void should_subtract_in_an_immutable_way_as_expected() {
        // given
        Quantity a = Quantity.of(50L, Unit.CHF_CENT);
        Quantity b = Quantity.of(100L, Unit.CHF_CENT);
        Quantity d = Quantity.of(1L, Unit.CHF);
        // when
        // then
        assertThat(a.iminus(b).toLong()).isEqualTo(-50L);
        assertThat(b.iminus(a).toLong()).isEqualTo(50L);
        assertThat(a.iminus(d).toLong()).isEqualTo(-50L);
        assertThat(d.iminus(a).asString()).isEqualTo("0.5");
    }

    @Test
    public void should_multiply_in_an_immutable_way_as_expected() {
        // given
        Quantity a = Quantity.of(50L, Unit.CHF_CENT);
        // when
        Quantity d = a.itimes(10L);
        Quantity e = a.itimes(new BigDecimal("10.5"));
        // then
        assertThat(d.toLong()).isEqualTo(500L);
        assertThat(e.toLong()).isEqualTo(525L);
    }


    @Test
    public void should_add_as_expected() {
        // given
        Quantity a = Quantity.of(50L, Unit.CHF_CENT);
        Quantity b = Quantity.of(100L, Unit.CHF_CENT);
        // when
        a.plus(b);
        // then
        assertThat(a.toLong()).isEqualTo(150L);
    }

    @Test
    public void should_subtract_as_expected() {
        // given
        Quantity a = Quantity.of(50L, Unit.CHF_CENT);
        Quantity b = Quantity.of(100L, Unit.CHF_CENT);
        // when
        a.minus(b);
        // then
        assertThat(a.toLong()).isEqualTo(-50L);
    }

    @Test
    public void should_multiply_as_expected() {
        // given
        Quantity a = Quantity.of(50L, Unit.CHF_CENT);
        // when
        a.times(10L);
        // then
        assertThat(a.toLong()).isEqualTo(500L);
    }

}
