package edu.noia.myoffice.common.mixin;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.common.domain.vo.Quantity;
import edu.noia.myoffice.common.serializer.CommonSerializers;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class AmountSerializerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        objectMapper.registerModule(CommonSerializers.getModule());
        objectMapper.addMixIn(Quantity.class, QuantityMixin.class);
    }

    @Test
    public void should_serialize_quantity() throws IOException {
        // given
        Amount amount = Amount.ofCentimes(75L);
        // when
        String json = objectMapper.writeValueAsString(amount);
        // then
        assertThat(json).containsIgnoringCase("\"unit\":\"CHF\"");
        assertThat(json).containsIgnoringCase("\"value\":\"0.75\"");
    }
}
