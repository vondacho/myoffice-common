package edu.noia.myoffice.common.testing;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class EndpointPactITBaseTest {

    @Mock
    private MockMvc mockMvc;

    @InjectMocks
    private TestEndpointPactITBase testing;

    @Test
    public void setup_should_initialize_assured_properly() throws Exception {
        testing.setup();
        assertThat(RestAssuredMockMvc.config().getMockMvcConfig()).isNotNull();
    }

    @Ignore
    private static class TestEndpointPactITBase extends EndpointPactITBase {
    }
}
