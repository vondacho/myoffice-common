package edu.noia.myoffice.common.data.jpa;

import edu.noia.myoffice.common.domain.event.BaseEvent;
import edu.noia.myoffice.common.domain.event.ProblemEventPayload;
import edu.noia.myoffice.common.event.store.EventPublication;
import edu.noia.myoffice.common.util.exception.Problem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DataComponentTestConfig.class})
@DataJpaTest
public class JpaEventPublicationRepositoryIT {

    @Autowired
    JpaEventPublicationRepository repository;

    @Test
    public void saveEventPublication() {
        JpaEventPublication publication = JpaEventPublication.of(
                BaseEvent.of(new ProblemEventPayload(Problem.from(new Exception("test")))));

        assertThat(repository.save(publication)).isNotNull();
        assertThat(repository.findTop100ByStatus(EventPublication.Status.PENDING)).contains(publication);
    }
}
