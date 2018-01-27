package edu.noia.myoffice.common.util;

import java.util.function.Consumer;

public interface Holder<T> {
    void execute(Consumer<T> action);
}
