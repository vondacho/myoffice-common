package edu.noia.myoffice.common.testing.endpoint;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Base class for testing Spring MVC controllers using Spring CDC generated tests.
 * To be used if no fixture in database is required by the Pact test suite, which probably is the exception.
 * Otherwise use {@link EndpointPactITWithFixture} instead.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EndpointPactITBase {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }
}
