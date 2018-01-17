package edu.noia.myoffice.common.domain.event;

import java.util.function.BiFunction;

public interface EventFactory extends BiFunction<Class<? extends Event>, Object[], Event> {
}
