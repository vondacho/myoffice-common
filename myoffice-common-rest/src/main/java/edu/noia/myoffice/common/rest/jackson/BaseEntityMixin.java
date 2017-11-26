package edu.noia.myoffice.common.rest.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseEntityMixin {

    @JsonIgnore
    public Long primaryId;
}
