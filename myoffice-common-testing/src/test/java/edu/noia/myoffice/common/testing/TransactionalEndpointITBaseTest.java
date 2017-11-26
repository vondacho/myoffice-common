package edu.noia.myoffice.common.testing;

import edu.noia.myoffice.common.testing.endpoint.TransactionalEndpointITBase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TransactionalEndpointITBaseTest {

    @Mock
    private EntityManager em;

    @InjectMocks
    private TransactionalEndpointITBaseTest.TestTransactionalEndpointITBase testing;

    @Test
    public void should_flush_and_clear() throws Exception {
        testing.flushClear();
        verify(em).flush();
        verify(em).clear();
    }

    @Ignore
    private static class TestTransactionalEndpointITBase extends TransactionalEndpointITBase {
        @Override
        public void flushClear() {
            super.flushClear();
        }
    }
}
