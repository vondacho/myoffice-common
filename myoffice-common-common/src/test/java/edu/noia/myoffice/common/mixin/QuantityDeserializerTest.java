package edu.noia.myoffice.common.mixin;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.noia.myoffice.common.domain.vo.Quantity;
import edu.noia.myoffice.common.domain.vo.Unit;
import edu.noia.myoffice.common.serializer.CommonSerializers;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class QuantityDeserializerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        objectMapper.registerModule(CommonSerializers.getModule());
    }

    @Test
    public void should_deserialize_quantity() throws IOException {
        // given
        String json = "{\"value\": \"3\",\"unit\": \"HOUR\"}";
        // when
        Quantity quantity = objectMapper.readValue(json, Quantity.class);
        // then
        assertThat(quantity).isEqualTo(Quantity.of(3L, Unit.HOUR));
    }
}
