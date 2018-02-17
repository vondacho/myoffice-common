package edu.noia.myoffice.common.util.broker;

import edu.noia.myoffice.common.util.processor.Processor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class DefaultBroker<T, I> implements Broker<T, I> {

    ConcurrentHashMap<I, Subscriber<T,?>> subscribers = new ConcurrentHashMap<>();

    public <U> void subscribe(I subscriberId, Consumer<U> subscriber, Processor<T,U> processor) {
        subscribe(subscriberId, new Subscriber<>(subscriber, processor));
    }

    public <U> void subscribe(I subscriberId, Subscriber<T,U> subscriber) {
        subscribers.put(subscriberId, subscriber);
    }

    public void unsubscribe(I subscriberId) {
        subscribers.remove(subscriberId);
    }

    @Override
    public void accept(T data) {
        forEach(subscriber -> subscriber.accept(data));
    }

    public void complete() {
        forEach(Subscriber::complete);
        subscribers.clear();
    }

    protected void forEach(Consumer<Subscriber<T,?>> action) {
        subscribers.forEach((subscriberId, subscriber) -> action.accept(subscriber));
    }

    @Getter
    @RequiredArgsConstructor
    public static class Subscriber<E,F> implements Consumer<E> {
        @NonNull
        Consumer<F> consumer;
        @NonNull
        Processor<E,F> processor;

        @Override
        public void accept(E data) {
            processor.apply(data).ifPresent(d -> consumer.accept(d));
        }

        public void complete() {}
    }
}
