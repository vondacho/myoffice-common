package edu.noia.myoffice.common.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public interface AuditableEntityMixin extends BaseEntityMixin {

    @JsonIgnore
    LocalDateTime getCreatedDate();
    @JsonIgnore
    LocalDateTime getLastModifiedDate();
    @JsonIgnore
    String getCreatedBy();
    @JsonIgnore
    String getLastModifiedBy();
}
