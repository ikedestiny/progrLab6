package org.example.common.util;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.data.SpaceMarine;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ClientRequest implements Serializable {
    public static final String[] clientReqs = {
            "clear",
            "countGreaterThanCategory",
            "executeScript",
            "exit",
            "filterLessThanHealth",
            "filterStartsWithName",
            "head",
            "help",
            "info",
            "RemoveById",
            "RemoveLower",
            "Show"
    };
    private String commandName;
    private Object argument;
    private SpaceMarine addParam;

    public ClientRequest(String commandName, SpaceMarine spaceMarine) {
        this.commandName = commandName;
        this.argument = spaceMarine;
    }

    public ClientRequest(String commandName, Object argument, SpaceMarine addParam) {
        this.commandName = commandName;
        this.argument = argument;
        this.addParam = addParam;
    }

    public ClientRequest(String commandName, String param) {
        this.commandName = commandName;
        this.argument = param;
    }


}
