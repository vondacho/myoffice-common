package edu.noia.myoffice.common.domain.vo;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PercentageTest {

    @Test
    public void should_be_calculate_percentage_of_hour_as_expected() {
        // given
        Percentage p10 = Percentage.of(10L);
        Percentage p20 = Percentage.of(20L);
        Percentage p50 = Percentage.of(50L);
        Percentage p501 = Percentage.of("50.1");
        Percentage p5011 = Percentage.of("50.11");
        // when
        // then
        assertThat(p10.apply(Amount.ofFrancs(10L)).toCentimes()).isEqualTo(100L);
        assertThat(p20.apply(Quantity.of(60L, Unit.MINUTE)).toLong()).isEqualTo(12L);
        assertThat(p50.apply(Quantity.of(1000L, Unit.GRAM)).toLong()).isEqualTo(500L);
        assertThat(p501.apply(Quantity.of(1000L, Unit.GRAM)).toLong()).isEqualTo(501L);
        assertThat(p5011.apply(Quantity.of(1000L, Unit.GRAM)).toLong()).isEqualTo(501L);
    }
}
