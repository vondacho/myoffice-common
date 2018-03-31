package edu.noia.myoffice.common.mixin;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.noia.myoffice.common.domain.vo.Quantity;
import edu.noia.myoffice.common.domain.vo.Rate;
import edu.noia.myoffice.common.domain.vo.Unit;
import edu.noia.myoffice.common.serializer.CommonSerializers;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class RateDeserializerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        objectMapper.registerModule(CommonSerializers.getModule());
    }

    @Test
    public void should_deserialize_rate() throws IOException {
        // given
        String json = "{\"quantity\":{\"value\":\"75\",\"unit\":\"CHF_CENT\"},\"by\":{\"value\":\"1\",\"unit\":\"HOUR\"}}";
        // when
        Rate rate = objectMapper.readValue(json, Rate.class);
        // then
        assertThat(rate.getQuantity()).isEqualTo(Quantity.of(75L, Unit.CHF_CENT));
        assertThat(rate.getBy()).isEqualTo(Quantity.of(1L, Unit.HOUR));
    }
}
