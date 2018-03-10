package edu.noia.myoffice.common.domain.entity;

public interface EntityState {

    EntityState modify(EntityState modifier);

    EntityState patch(EntityState modifier);
}
