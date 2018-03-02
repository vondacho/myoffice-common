package edu.noia.myoffice.common.domain.entity;

import edu.noia.myoffice.common.domain.repository.EntityRepository;
import edu.noia.myoffice.common.domain.vo.Identity;

public interface Entity<E extends Entity<E, I, S>, I extends Identity, S extends EntityState> {

    I getId();

    S getState();

    E modify(S modifier);

    E patch(S modifier);

    E save(EntityRepository<E, I, S> repository);

    void validate(S state);
}
