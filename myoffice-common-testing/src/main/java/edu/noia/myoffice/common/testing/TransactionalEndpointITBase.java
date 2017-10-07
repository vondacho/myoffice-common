package edu.noia.myoffice.common.testing;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Base class for testing Spring MVC controllers.
 * To be used if the controller accesses data from a relational database.
 * Any (database) modifying REST method call will be rolled back, so that after the test,
 * the database will be in the same state as before the test.
 */
@Transactional
public abstract class TransactionalEndpointITBase extends EndpointITBase {

    @PersistenceContext
    private EntityManager em;

    /**
     * This method calls the flush and clear methods on the entity manager.
     * You might use it, if you want to enter some data into the database before making the Endpoint calls.
     * Thus ensuring that the data doesn't come from the first level cache (persistence context), but is
     * retrieved with a query hitting the database.
     */
    protected void flushClear() {
        em.flush();
        em.clear();
    }
}
