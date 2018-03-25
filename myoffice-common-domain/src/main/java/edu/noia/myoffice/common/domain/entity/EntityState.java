package edu.noia.myoffice.common.domain.entity;

public interface EntityState {

    default EntityState modify(EntityState modifier) {
        return this;
    }

    default EntityState patch(EntityState modifier) {
        return this;
    }
}
