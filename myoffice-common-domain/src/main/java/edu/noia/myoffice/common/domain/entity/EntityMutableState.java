package edu.noia.myoffice.common.domain.entity;

public interface EntityMutableState extends EntityState {

    EntityMutableState modify(EntityState modifier);
    EntityMutableState patch(EntityState modifier);
}
