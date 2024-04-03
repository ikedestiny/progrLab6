package org.example.server.main.ServerCommands;

import lombok.Getter;
import org.example.common.util.CommandType;

@Getter
public abstract class IntegerArgumentCommand extends Command {
    private final CommandType commandType = CommandType.INT;


    public IntegerArgumentCommand(String name, String description) {
        super(name, description);
    }


}
