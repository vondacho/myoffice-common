package edu.noia.myoffice.common.rest.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public class BaseEntityMixin {

    @JsonIgnore
    public Long primaryId;
}
