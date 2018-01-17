package edu.noia.myoffice.common.domain.command;

import java.util.function.Consumer;

public interface CommandPublisher extends Consumer<Command> {
}
