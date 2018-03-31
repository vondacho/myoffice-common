package edu.noia.myoffice.common.mixin;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.noia.myoffice.common.domain.vo.Quantity;
import edu.noia.myoffice.common.domain.vo.Unit;
import edu.noia.myoffice.common.serializer.CommonSerializers;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class QuantitySerializerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        objectMapper.registerModule(CommonSerializers.getModule());
        objectMapper.addMixIn(Quantity.class, QuantityMixin.class);
    }

    @Test
    public void should_serialize_quantity() throws IOException {
        // given
        Quantity quantity = Quantity.of(3L, Unit.HOUR);
        // when
        String json = objectMapper.writeValueAsString(quantity);
        // then
        assertThat(json).containsIgnoringCase("\"unit\":\"HOUR\"");
        assertThat(json).containsIgnoringCase("\"value\":\"3\"");
    }
}
