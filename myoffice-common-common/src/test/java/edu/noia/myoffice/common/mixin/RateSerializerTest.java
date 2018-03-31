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

public class RateSerializerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        objectMapper.registerModule(CommonSerializers.getModule());
        objectMapper.addMixIn(Quantity.class, QuantityMixin.class);
        objectMapper.addMixIn(Rate.class, RateMixin.class);
    }

    @Test
    public void should_serialize_rate() throws IOException {
        // given
        Rate<?, ?> rate = new Rate(Quantity.of(75L, Unit.CHF_CENT), Quantity.of(1L, Unit.HOUR));
        // when
        String json = objectMapper.writeValueAsString(rate);
        // then
        assertThat(json).containsIgnoringCase("\"quantity\":{\"unit\":\"CHF_CENT\",\"value\":\"75\"}");
        assertThat(json).containsIgnoringCase("\"by\":{\"unit\":\"HOUR\",\"value\":\"1\"}");
    }
}
