package edu.noia.myoffice.common.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseEntityMixin {

    @JsonIgnore
    public Long primaryId;
}
