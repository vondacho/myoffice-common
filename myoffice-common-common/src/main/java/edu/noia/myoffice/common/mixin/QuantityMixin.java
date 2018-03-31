package edu.noia.myoffice.common.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public interface QuantityMixin {

    @JsonProperty("value")
    String asString();

    @JsonIgnore
    BigDecimal getValue();
}
