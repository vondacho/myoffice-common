package edu.noia.myoffice.common.domain.entity;

public interface EntityMutableState<
        M extends EntityMutableState,
        S extends EntityState> {

    M modify(S modifier);
    M patch(S modifier);
}
