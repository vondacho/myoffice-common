package edu.noia.myoffice.common.util.processor;

import java.util.Optional;
import java.util.function.Function;

public interface Processor<T,U> extends Function<T, Optional<U>> {
}
