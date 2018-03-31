package edu.noia.myoffice.common.mixin;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.common.domain.vo.Quantity;
import edu.noia.myoffice.common.domain.vo.Tariff;
import edu.noia.myoffice.common.domain.vo.Unit;
import edu.noia.myoffice.common.serializer.CommonSerializers;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class TariffDeserializerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        objectMapper.registerModule(CommonSerializers.getModule());
    }

    @Test
    public void should_deserialize_tariff_in_centimes() throws IOException {
        // given
        String json = "{\"quantity\":{\"value\":\"75\",\"unit\":\"CHF_CENT\"},\"by\":{\"value\":\"1\",\"unit\":\"HOUR\"}}";
        // when
        Tariff tariff = objectMapper.readValue(json, Tariff.class);
        // then
        assertThat(tariff.getQuantity()).isEqualTo(Amount.ofCentimes(75L));
        assertThat(tariff.getBy()).isEqualTo(Quantity.of(1L, Unit.HOUR));
    }

    @Test
    public void should_deserialize_tariff_in_chf() throws IOException {
        // given
        String json = "{\"quantity\":{\"value\":\"0.75\",\"unit\":\"CHF\"},\"by\":{\"value\":\"1\",\"unit\":\"HOUR\"}}";
        // when
        Tariff tariff = objectMapper.readValue(json, Tariff.class);
        // then
        assertThat(tariff.getQuantity()).isEqualTo(Amount.ofCentimes(75L));
        assertThat(tariff.getBy()).isEqualTo(Quantity.of(1L, Unit.HOUR));
    }
}
