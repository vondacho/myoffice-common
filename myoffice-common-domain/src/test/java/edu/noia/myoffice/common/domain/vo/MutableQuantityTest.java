package edu.noia.myoffice.common.domain.vo;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MutableQuantityTest {

    @Test
    public void should_add_as_expected() {
        // given
        MutableQuantity a = new MutableQuantity(50L, Unit.CENTIMES);
        MutableQuantity b = new MutableQuantity(100L, Unit.CENTIMES);
        // when
        a.plus(b);
        // then
        assertThat(a.toLong()).isEqualTo(150L);
    }

    @Test
    public void should_subtract_as_expected() {
        // given
        MutableQuantity a = new MutableQuantity(50L, Unit.CENTIMES);
        MutableQuantity b = new MutableQuantity(100L, Unit.CENTIMES);
        // when
        a.minus(b);
        // then
        assertThat(a.toLong()).isEqualTo(-50L);
    }

    @Test
    public void should_multiply_as_expected() {
        // given
        MutableQuantity a = new MutableQuantity(50L, Unit.CENTIMES);
        // when
        a.times(10L);
        // then
        assertThat(a.toLong()).isEqualTo(500L);
    }
}
