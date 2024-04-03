package org.example.server.main.ServerCommands;

import lombok.Getter;
import org.example.common.util.CommandType;

@Getter
public abstract class NoArgCommand extends Command {
    private final CommandType commandType = CommandType.NO_ARG;

    public NoArgCommand(String name, String description) {
        super(name, description);
    }
}
