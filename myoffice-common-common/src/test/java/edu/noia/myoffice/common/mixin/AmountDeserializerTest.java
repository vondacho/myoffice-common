package edu.noia.myoffice.common.mixin;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.common.domain.vo.Unit;
import edu.noia.myoffice.common.serializer.CommonSerializers;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class AmountDeserializerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        objectMapper.registerModule(CommonSerializers.getModule());
    }

    @Test
    public void should_deserialize_amount() throws IOException {
        // given
        String json = "{\"value\":\"75.00\",\"unit\":\"CHF\"}";
        // when
        Amount amount = objectMapper.readValue(json, Amount.class);
        // then
        assertThat(amount.toCentimes()).isEqualTo(7500L);
        assertThat(amount.getUnit()).isEqualTo(Unit.CHF_CENT);
    }
}
