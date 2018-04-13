package edu.noia.myoffice.common.domain.entity;

/**
 * This interface specifies the minimal api of a domain entity state, it basically provide modifier methods.
 */
public interface EntityState {

    /**
     * Replaces the internal attributes values by the ones contained inside the given modifier state.
     * The default behavior does nothing.
     *
     * @param modifier instance of {@link EntityState}
     * @return the current updated instance
     */
    default EntityState modify(EntityState modifier) {
        return this;
    }

    /**
     * Replaces the internal attributes values by the non-null ones contained inside the given modifier state.
     * The default behavior does nothing.
     *
     * @param modifier instance of {@link EntityState}
     * @return the current updated instance
     */
    default EntityState patch(EntityState modifier) {
        return this;
    }
}
