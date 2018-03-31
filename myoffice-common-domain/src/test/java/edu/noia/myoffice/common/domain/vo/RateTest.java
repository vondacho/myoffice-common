package edu.noia.myoffice.common.domain.vo;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RateTest {

    @Test
    public void should_be_calculate_tariff_x_chf_hour_as_expected() {
        // given
        Rate<Amount, Quantity> rate = new Rate(Amount.ofFrancs(50L), Quantity.of(1L, Unit.HOUR));
        // when
        // then
        assertThat(rate.apply(Quantity.of(10L, Unit.HOUR)).toCentimes()).isEqualTo(50000L);
        assertThat(rate.apply(Quantity.of(10L, Unit.MINUTE)).toCentimes()).isEqualTo(833L);
    }

    @Test
    public void should_be_calculate_tariff_x_chf_minute_as_expected() {
        // given
        Rate<Amount, Quantity> rate = new Rate(Amount.ofFrancs(2L), Quantity.of(1L, Unit.MINUTE));
        // when
        // then
        assertThat(rate.apply(Quantity.of(10L, Unit.HOUR)).toCentimes()).isEqualTo(120000L);
        assertThat(rate.apply(Quantity.of(10L, Unit.MINUTE)).toCentimes()).isEqualTo(2000L);
    }

    @Test
    public void should_be_calculate_tariff_x_chf_y_minute_as_expected() {
        // given
        Rate<Amount, Quantity> rate = new Rate(Amount.ofFrancs(5L), Quantity.of(2L, Unit.MINUTE));
        // when
        // then
        assertThat(rate.apply(Quantity.of(1L, Unit.HOUR)).toCentimes()).isEqualTo(15000L);
        assertThat(rate.apply(Quantity.of(2L, Unit.MINUTE)).toCentimes()).isEqualTo(500L);
        assertThat(rate.apply(Quantity.of(4L, Unit.MINUTE)).toCentimes()).isEqualTo(1000L);
        assertThat(rate.apply(Quantity.of(5L, Unit.MINUTE)).toCentimes()).isEqualTo(1250L);
    }
}
