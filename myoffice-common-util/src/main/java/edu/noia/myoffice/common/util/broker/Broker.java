package edu.noia.myoffice.common.util.broker;

import edu.noia.myoffice.common.util.processor.Processor;

import java.util.function.Consumer;

public interface Broker<T,I> extends Consumer<T> {

    <U> void subscribe(I subscriberId, Consumer<U> subscriber, Processor<T,U> processor);

    void unsubscribe(I subscriberId);
}
