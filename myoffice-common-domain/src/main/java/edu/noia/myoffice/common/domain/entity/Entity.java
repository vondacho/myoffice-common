package edu.noia.myoffice.common.domain.entity;

import edu.noia.myoffice.common.domain.vo.Identity;
import edu.noia.myoffice.common.domain.repository.EntityRepository;

public interface Entity<
        E extends Entity,
        I extends Identity,
        S extends EntityState,
        R extends EntityRepository> {

    I getId();

    S getState();

    E modify(S modifier);

    E patch(S modifier);

    E save(R repository);
}
