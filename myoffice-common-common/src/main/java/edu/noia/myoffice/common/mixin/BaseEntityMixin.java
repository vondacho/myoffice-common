package edu.noia.myoffice.common.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface BaseEntityMixin {

    @JsonIgnore
    Long getPrimaryId();
}
