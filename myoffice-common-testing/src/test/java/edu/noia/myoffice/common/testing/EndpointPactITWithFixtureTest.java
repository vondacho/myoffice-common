package edu.noia.myoffice.common.testing;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EndpointPactITWithFixtureTest {

    @Mock
    private EntityManager em;

    @InjectMocks
    private TestEndpointPactITWithFixture testing;

    @Test
    public void should_flush_and_clear() throws Exception {
        testing.flushClear();
        verify(em).flush();
        verify(em).clear();
    }

    @Test
    public void setup_should_call_setup_fixture() throws Exception {
        testing.setup();
        assertThat(testing.fixtureHasBeenInitialized).isTrue();
    }

    @Ignore
    private static class TestEndpointPactITWithFixture extends EndpointPactITWithFixture {
        public boolean fixtureHasBeenInitialized = false;

        @Override
        public void flushClear() {
            super.flushClear();
        }

        @Override
        public void setupFixture() { fixtureHasBeenInitialized = true;}
    }
}
