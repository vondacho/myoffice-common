package edu.noia.myoffice.common.testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.io.IOException;
import java.util.function.UnaryOperator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

/**
 * Base class for testing Spring MVC controllers. To be used if no database is accessed which probably is the exception.
 * Otherwise use {@link TransactionalEndpointITBase} instead.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public abstract class EndpointITBase {

    /**
     * Base path to test HATEOAS link.
     */
    protected static String SERVER_CONTEXT_PATH = "http://localhost";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    /**
     * Method for testing assertions against a REST service call.
     * E.g, testing a GET request controller method might look something like this :
     * <pre>{@code
     *  doRequest(
     *  endpoint,
     *  HttpMethod.GET,
     *  null,
     *  headers
     *  ra ->
     *    ra
     *       .andExpect(status().isOk())
     *       .andExpect(jsonPath("$.jsonPathToSomeProperty").value(expectedValue))
     *       ...(more asertions here));
     * }</pre>
     *
     * @param endpoint        mandatory, the URI without the server context path and starting with a '/', e.g. '/items'
     * @param method          mandatory, the http method to be applied (GET,POST,PUT...)
     * @param content         optional, the POJO object to be jsonized and sent in the body of the request
     * @param headers         optional the HTTP request headers to be included in the request
     * @param resultActionsOp mandatory, containing the sequence of assertions to be applied to the HTTP response.
     * @return the mocked HTTP servlet response
     * @throws Exception some exception might be thrown if something goes wrong
     */
    protected MockHttpServletResponse doRequest(@NonNull String endpoint,
                                                @NonNull HttpMethod method,
                                                Object content,
                                                HttpHeaders headers,
                                                @NonNull ResultActionsUnaryOperator resultActionsOp) throws Exception {
        LOG.info("{} {}", method, endpoint);
        String json = null;
        if (content != null) {
            json = mapper.writeValueAsString(content);
            LOG.info("content : {}", json);
        }
        MockHttpServletRequestBuilder builder = request(method, endpoint)
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        if (json != null) {
            builder = builder.content(json);
        }
        if (headers != null) {
            LOG.info("headers : {}", headers);
            builder = builder.headers(headers);
        }
        ResultActions resultActions = mockMvc.perform(builder);
        resultActions = resultActionsOp.apply(resultActions);
        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        LOG.info("response --> {}", response.getContentAsString());
        return response;
    }

    /**
     * Calling {@link #doRequest(String, HttpMethod, Object, HttpHeaders, ResultActionsUnaryOperator)} with {@code headers = null}.
     *
     * @param endpoint        mandatory, the URI without the server context path and starting with a '/', e.g. '/items'
     * @param method          mandatory, the http method to be applied (GET,POST,PUT...)
     * @param content         optional, the POJO object to be jsonized and sent in the body of the request
     * @param resultActionsOp mandatory, containing the sequence of assertions to be applied to the HTTP response.
     * @return the mocked HTTP servlet response
     * @throws Exception some exception might be thrown if something goes wrong
     */
    protected MockHttpServletResponse doRequest(String endpoint, HttpMethod method, Object content,
                                                ResultActionsUnaryOperator resultActionsOp) throws Exception {
        return doRequest(endpoint, method, content, null, resultActionsOp);
    }

    /**
     * Calling {@link #doRequest(String, HttpMethod, Object, HttpHeaders, ResultActionsUnaryOperator)} with {@code content = null}.
     *
     * @param endpoint        mandatory, the URI without the server context path and starting with a '/', e.g. '/items'
     * @param method          mandatory, the http method to be applied (GET,POST,PUT...)
     * @param headers         optional the HTTP request headers to be included in the request
     * @param resultActionsOp mandatory, containing the sequence of assertions to be applied to the HTTP response.
     * @return the mocked HTTP servlet response
     * @throws Exception some exception might be thrown if something goes wrong
     */
    protected MockHttpServletResponse doRequest(String endpoint, HttpMethod method, HttpHeaders headers,
                                                ResultActionsUnaryOperator resultActionsOp) throws Exception {
        return doRequest(endpoint, method, null, headers, resultActionsOp);
    }

    /**
     * Calling {@link #doRequest(String, HttpMethod, Object, HttpHeaders, ResultActionsUnaryOperator)} with {@code headers = null, content = null}.
     *
     * @param endpoint        mandatory, the URI without the server context path and starting with a '/', e.g. '/items'
     * @param method          mandatory, the http method to be applied (GET,POST,PUT...)
     * @param resultActionsOp mandatory, containing the sequence of assertions to be applied to the HTTP response.
     * @return the mocked HTTP servlet response
     * @throws Exception some exception might be thrown if something goes wrong
     */
    protected MockHttpServletResponse doRequest(String endpoint, HttpMethod method, ResultActionsUnaryOperator resultActionsOp)
            throws Exception {
        return doRequest(endpoint, method, null, null, resultActionsOp);
    }

    /**
     * Checks the response body's JSON object against the passed in POJO.
     * The object parsed from the body's JSON must be equal comparing field by field recursively to the passed in POJO.
     *
     * @param response not {@code null}
     * @param expected not {@code null}
     * @throws IOException some exception might be thrown if something goes wrong
     */
    protected void checkIsEqualToComparingFieldByFieldRecursively(MockHttpServletResponse response, Object expected)
            throws IOException {
        @SuppressWarnings("unchecked")
        Class<?> clazz = expected.getClass();
        Object returnedT = mapper.readValue(response.getContentAsString(), clazz);
        assertThat(returnedT).isEqualToComparingFieldByFieldRecursively(expected);
    }

    /**
     * This functional interface is like an {@link UnaryOperator} of {@link ResultActions} but accepting to throw any exception.
     */
    @FunctionalInterface
    protected interface ResultActionsUnaryOperator {
        ResultActions apply(ResultActions ra) throws Exception;
    }

}
