package edu.noia.myoffice.common.domain.command;

import java.util.function.BiFunction;

public interface CommandFactory extends BiFunction<Class<? extends Command>, Object[], Command> {
}
