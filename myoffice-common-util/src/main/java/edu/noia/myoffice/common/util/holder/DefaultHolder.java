package edu.noia.myoffice.common.util.holder;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.function.Consumer;

@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DefaultHolder<T> implements Holder<T> {

    @NonNull
    T object;

    @Override
    public void execute(Consumer<T> action) {
        action.accept(object);
    }
}
