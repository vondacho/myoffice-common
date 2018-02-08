package edu.noia.myoffice.common.util.holder;

import java.util.function.Consumer;

public interface Holder<T> {
    void execute(Consumer<T> action);
}
