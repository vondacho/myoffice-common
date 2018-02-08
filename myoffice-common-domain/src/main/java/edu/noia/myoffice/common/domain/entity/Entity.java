package edu.noia.myoffice.common.domain.entity;

import edu.noia.myoffice.common.domain.repository.EntityRepository;
import edu.noia.myoffice.common.domain.vo.Identity;

public interface Entity<I extends Identity, S extends EntityState> {

    I getId();

    S getState();

    Entity<I,S> modify(S modifier);

    Entity<I,S> patch(S modifier);

    <R extends EntityRepository<Entity<I,S> ,I, S>> Entity<I,S>  save(R repository);

    void validate(S state);
}
