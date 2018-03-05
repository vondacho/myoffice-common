package edu.noia.myoffice.common.data.jpa;

import edu.noia.myoffice.common.event.store.EventPublication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaEventPublicationRepository extends JpaRepository<JpaEventPublication, Long> {

    List<JpaEventPublication> findTop100ByStatus(EventPublication.Status status);
}
