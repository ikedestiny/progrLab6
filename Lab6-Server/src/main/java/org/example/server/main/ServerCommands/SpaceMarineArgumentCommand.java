package org.example.server.main.ServerCommands;

import lombok.Getter;
import org.example.common.util.CommandType;

@Getter
public abstract class SpaceMarineArgumentCommand extends Command {
    private final CommandType commandType = CommandType.SPACE;

    public SpaceMarineArgumentCommand(String name, String description) {
        super(name, description);
    }
}
