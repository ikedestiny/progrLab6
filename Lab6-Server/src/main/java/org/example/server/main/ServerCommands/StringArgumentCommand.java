package org.example.server.main.ServerCommands;

import lombok.Getter;
import org.example.common.util.CommandType;

@Getter
public abstract class StringArgumentCommand extends Command {
    private final CommandType commandType = CommandType.STRING;

    public StringArgumentCommand(String name, String description) {
        super(name, description);
    }
}
