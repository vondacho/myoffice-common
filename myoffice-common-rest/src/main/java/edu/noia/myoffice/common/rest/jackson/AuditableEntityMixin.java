package edu.noia.myoffice.common.rest.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class AuditableEntityMixin extends BaseEntityMixin {

    @JsonIgnore
    LocalDateTime createdDate;
    @JsonIgnore
    LocalDateTime lastModifiedDate;
    @JsonIgnore
    String createdBy;
    @JsonIgnore
    String lastModifiedBy;
}
