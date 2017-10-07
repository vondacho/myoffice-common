package edu.noia.myoffice.common.testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EndpointITBaseTest {

    private final static MessageHolder MESSAGE = new MessageHolder("Test");
    private final static String MESSAGE_JSON = "{\"message\":\"Test\"}";
    private final static String PATH = "/test";
    private final static String CONTENT_TYPE = "Content-Type";

    @Mock
    private MockMvc mockMvc;
    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private TestEndpointITBase testing;
    private MockHttpServletResponse response;

    @Test
    public void should_provide_context_path_for_HATEOAS_links() throws Exception {
        assertThat(testing.getContextPath()).isEqualTo("http://localhost");
    }

    @Before
    public void prepareObjectMapper() throws Exception {
        given(mapper.writeValueAsString(MESSAGE)).willReturn(MESSAGE_JSON);
        given(mapper.readValue(MESSAGE_JSON, MessageHolder.class)).willReturn(MESSAGE);
    }

    @Before
    public void prepareMockMvc() throws Exception {
        // Given
        ResultActions resultActions = mock(ResultActions.class);
        MvcResult result = mock(MvcResult.class);
        response = mock(MockHttpServletResponse.class);
        given(response.getContentAsString()).willReturn(MESSAGE_JSON);
        given(result.getResponse()).willReturn(response);
        given(resultActions.andReturn()).willReturn(result);
        given(mockMvc.perform(any())).willReturn(resultActions);
    }

    @Test
    public void doRequest_should_do_the_request_only_with_path_and_method() throws Exception {
        // Given
        HttpMethod method = HttpMethod.GET;
        // When
        MockHttpServletResponse responseReturned = testing.getResponseFromDoRequest(PATH, method);
        // Then
        MockHttpServletRequest request = checkResponseAndExtractRequest(responseReturned);
        assertCommonRequestAssertions(method, request);
        assertThat(Collections.list(request.getHeaderNames())).containsOnly(CONTENT_TYPE);
        assertThat(request.getReader().readLine()).isNull();
    }

    @Test
    public void doRequest_should_do_the_request_without_header() throws Exception {
        // Given
        HttpMethod method = HttpMethod.POST;
        // When
        MockHttpServletResponse responseReturned = testing.getResponseFromDoRequest(PATH, method, MESSAGE);
        // Then
        MockHttpServletRequest request = checkResponseAndExtractRequest(responseReturned);
        assertCommonRequestAssertions(method, request);
        assertThat(Collections.list(request.getHeaderNames())).containsOnly(CONTENT_TYPE);
        assertThat(request.getReader().readLine()).isEqualTo(MESSAGE_JSON);
    }

    @Test
    public void doRequest_should_do_the_request_without_content() throws Exception {
        // Given
        HttpMethod method = HttpMethod.POST;
        HttpHeaders headers = new HttpHeaders();
        String name = "testHeaderName";
        String value = "testHeaderValue";
        headers.add(name, value);
        // When
        MockHttpServletResponse responseReturned = testing.getResponseFromDoRequest(PATH, method, headers);
        // Then
        MockHttpServletRequest request = checkResponseAndExtractRequest(responseReturned);
        assertCommonRequestAssertions(method, request);
        assertThat(Collections.list(request.getHeaderNames())).containsOnly(CONTENT_TYPE, name);
        assertThat(request.getHeader(name)).isEqualTo(value);
        assertThat(request.getReader().readLine()).isNull();
    }

    @Test
    public void checkIsEqualToComparingFieldByFieldRecursively_should_be_ok() throws Exception {
        testing.checkIsEqualRecursively(response, MESSAGE);
    }

    @Test(expected = AssertionError.class)
    public void checkIsEqualToComparingFieldByFieldRecursively_should_be_ko() throws Exception {
        testing.checkIsEqualRecursively(response, new MessageHolder("WrongTest"));
    }

    private void assertCommonRequestAssertions(HttpMethod method, MockHttpServletRequest request) {
        assertThat(request.getMethod()).isEqualTo(method.name());
        assertThat(request.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8_VALUE);
        assertThat(request.getPathInfo()).isEqualTo(PATH);
    }


    private MockHttpServletRequest checkResponseAndExtractRequest(MockHttpServletResponse responseReturned) throws Exception {
        assertThat(responseReturned).isEqualTo(response);
        ArgumentCaptor<MockHttpServletRequestBuilder> builderCaptor = ArgumentCaptor.forClass(MockHttpServletRequestBuilder.class);
        verify(mockMvc).perform(builderCaptor.capture());
        return builderCaptor.getValue().buildRequest(null);
    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class MessageHolder {
        private String message;
    }

    @Ignore
    private static class TestEndpointITBase extends EndpointITBase {

        private ResultActionsUnaryOperator operator;

        public String getContextPath() {
            return SERVER_CONTEXT_PATH;
        }

        private void init() throws Exception {
            operator = mock(ResultActionsUnaryOperator.class);
            given(operator.apply(any())).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        }

        public MockHttpServletResponse getResponseFromDoRequest(String path, HttpMethod method) throws Exception {
            init();
            return doRequest(path, method, operator);
        }

        public MockHttpServletResponse getResponseFromDoRequest(String path, HttpMethod method, MessageHolder content) throws Exception {
            init();
            return doRequest(path, method, content, operator);
        }

        public MockHttpServletResponse getResponseFromDoRequest(String path, HttpMethod method, HttpHeaders headers) throws Exception {
            init();
            return doRequest(path, method, headers, operator);
        }

        public void checkIsEqualRecursively(MockHttpServletResponse response, MessageHolder message) throws Exception {
            init();
            checkIsEqualToComparingFieldByFieldRecursively(response, message);
        }
    }


}
