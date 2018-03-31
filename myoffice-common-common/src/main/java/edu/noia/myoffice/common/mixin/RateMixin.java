package edu.noia.myoffice.common.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.noia.myoffice.common.domain.vo.Unit;

public interface RateMixin {

    @JsonIgnore
    Unit getUnit();

    @JsonIgnore
    Unit getTargetUnit();
}
