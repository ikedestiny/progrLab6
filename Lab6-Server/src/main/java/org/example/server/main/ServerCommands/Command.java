package org.example.server.main.ServerCommands;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.common.exception.IllegalValueException;
import org.example.common.util.CommandType;
import org.example.common.util.ServerResponse;

import java.io.IOException;
import java.io.Serializable;

@Data
@Getter
@Setter
public abstract class Command implements Serializable {
    private String name;
    private CommandType commandType;
    private String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract ServerResponse execute(Object argument) throws IllegalValueException, IOException;

    public abstract ServerResponse response();
}
