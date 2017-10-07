package edu.noia.myoffice.common.testing;

import org.junit.Before;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Base class for testing Spring MVC controllers using Spring CDC generated tests.
 * To be used if the Pact test suite requires data from a relational database as fixture.
 * Any (database) modifying REST method call will be rolled back, so that after the test,
 * the database will be in the same state as before the test.
 */
@Transactional
public abstract class EndpointPactITWithFixture extends EndpointPactITBase {

    @PersistenceContext
    private EntityManager em;

    /**
     * This method calls the flush and clear methods on the entity manager.
     * You might use it, if you want to enter some data into the database before making the Endpoint calls.
     * Thus ensuring that the data doesn't come from the first level cache (persistence context), but is
     * retrieved with a query hitting the database.
     */
    protected void flushClear() {
        this.em.flush();
        this.em.clear();
    }

    @Before
    @Override
    public void setup() {
        super.setup();
        setupFixture();
    }

    /**
     * Template method to be implemented by subclasses.
     * It usually calls the entity manager bean and the flushClear method for persisting entities into the relational
     * database. This setup is executed before each Pact test.
     */
    public abstract void setupFixture();
}
