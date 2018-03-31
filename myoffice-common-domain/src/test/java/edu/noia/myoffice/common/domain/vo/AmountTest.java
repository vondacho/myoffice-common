package edu.noia.myoffice.common.domain.vo;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AmountTest {

    @Test
    public void should_create_as_expected() {
        // given
        Amount a = Amount.ofCentimes(50L);
        Amount b = Amount.ofFrancs(50L);
        Amount c = Amount.ofFrancs("50.05");
        // when
        // then
        assertThat(a.toCentimes()).isEqualTo(50L).isEqualTo(a.toLong());
        assertThat(b.toCentimes()).isEqualTo(5000L);
        assertThat(c.toCentimes()).isEqualTo(5005L);
        assertThat(c.asFrancs()).isEqualTo("50.05");
        assertThat(c.toFrancs()).isEqualTo(new BigDecimal("50.05"));
        assertThat(a).isNotEqualTo(b);
        assertThat(a).isNotEqualTo(c);
        assertThat(a).isEqualTo(Amount.ofCentimes(50L));
        assertThat(b).isEqualTo(Amount.ofCentimes(5000L));
        assertThat(c).isEqualTo(Amount.ofCentimes(5005L));
        assertThat(c).isNotEqualTo(Amount.ofCentimes(5006L));
    }

    @Test
    public void should_add_as_expected() {
        // given
        Amount a = Amount.ofCentimes(50L);
        Amount b = Amount.ofFrancs(1L);
        // when
        Amount c = a.iplus(b);
        Amount d = a.plus(b);
        // then
        assertThat(c.toCentimes()).isEqualTo(150L);
        assertThat(d.toCentimes()).isEqualTo(150L);
    }
}
