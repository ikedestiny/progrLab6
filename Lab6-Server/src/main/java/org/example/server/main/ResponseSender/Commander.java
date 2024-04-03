package org.example.server.main.ResponseSender;


import lombok.Data;
import org.example.common.exception.IllegalValueException;
import org.example.common.exception.InvalidInputException;
import org.example.common.util.*;
import org.example.server.main.ServerCommands.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
public class Commander {
    private final CollectionManager collectionManager;
    private final String XMLFilePath;
    private ArrayList<Command> commands;
    private ArrayList<String> history;
    private Map<String, CommandType> commandTypeMap;


    public Commander(CollectionManager collectionManager, String xmlFilePath) throws FileNotFoundException {
        this.collectionManager = collectionManager;
        XMLFilePath = xmlFilePath;
        this.commands = new ArrayList<>();
        addNoParamCommands();
        this.history = new ArrayList<>();
        commandTypeMap = new HashMap<>();
        setUpMap();

    }


    public void addNoParamCommands() throws FileNotFoundException {
        this.commands.add(new Clear(this));
        this.commands.add(new Exit());
        this.commands.add(new Head(this));
        this.commands.add(new Help(this));
        this.commands.add(new Info(this));
        this.commands.add(new Show(this));
        this.commands.add(new History(this));
        this.commands.add(new ExecuteScript(this));
        this.commands.add(new FilterLessThanHealth(this));
        this.commands.add(new UpdateById(collectionManager, this));
        this.commands.add(new RemoveLower(this));
        this.commands.add(new RemoveById(this));
        this.commands.add(new CountGreaterThanCategory(collectionManager));
        this.commands.add(new FilterStartsWithName(this));
        this.commands.add(new Add(this));
    }


    public ServerResponse sendResponse(ClientRequest clientRequest) throws InvalidInputException, IllegalValueException {
        ServerResponse response = new ServerResponse(Status.ERROR, "No Such Command", null);
        String name = clientRequest.getCommandName();

        response = commands.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .map(s -> {
                    try {
                        history.add(s.getName());
                        return s.execute(clientRequest);
                    } catch (IllegalValueException | IOException e) {
                        e.printStackTrace();
                    }
                    return new ServerResponse(Status.FAILED, "Something went wrong", null);
                })
                .findFirst()
                .orElse(new ServerResponse(Status.ERROR, "the command has some problems", null));
        return response;
        //return  new ServerResponse(Status.ERROR,"the messa",null);
    }

    public void setUpMap() {
        for (var command : commands) {
            commandTypeMap.put(command.getName().toLowerCase(), command.getCommandType());
        }
    }

}
