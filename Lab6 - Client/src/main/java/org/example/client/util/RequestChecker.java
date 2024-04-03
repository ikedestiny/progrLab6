package org.example.client.util;

import org.example.common.data.SpaceMarine;
import org.example.common.util.ClientRequest;
import org.example.common.util.CommandType;

import java.util.Map;

public class RequestChecker {
    private final Map<String, CommandType> commandTypeMap;

    public RequestChecker(Map<String, CommandType> commandTypeMap) {
        this.commandTypeMap = commandTypeMap;
    }

    public CommandType getCommandType(String request) {
        String commandName = request.split(" ")[0];
        return commandTypeMap.get(commandName.toLowerCase());

    }

    public boolean validInt(String intParam) {
        boolean amValid = false;

        try {
            Integer.parseInt(intParam);
            amValid = true;
        } catch (NumberFormatException ignored) {
        }

        return amValid;
    }


    public ClientRequest check(String param, String command) {
        ClientRequest clientRequest = new ClientRequest();
        CommandType commandType = getCommandType(command);
        switch (commandType) {
            case NO_ARG -> {
                clientRequest = new ClientRequest(command, param);
            }
            case STRING -> {
                if (param.equalsIgnoreCase("no args")) {
                    System.out.println(command + " requires a string argument");
                } else clientRequest = new ClientRequest(command, param);
            }
            case INT -> {
                if (param.equalsIgnoreCase("no args")) {
                    System.out.println("The command " + command + " requires an integer argument");
                } else if (!validInt(param)) {
                    System.out.println("The command " + command + " requires an integer argument");
                    System.out.println("Wrong number format for parameter");
                } else {
                    clientRequest = new ClientRequest(command, param);
                }
            }
            case SPACE -> {
                SpaceMarineCreator spaceMarineCreator = new SpaceMarineCreator();
                SpaceMarine sp = spaceMarineCreator.createSpaceMarine();
                param = String.valueOf(spaceMarineCreator.getId());
                clientRequest = new ClientRequest(command, param, sp);
            }
        }
        if (command.equalsIgnoreCase("exit")) {
            clientRequest = new ClientRequest();
            System.exit(0);
        }
        return clientRequest;
    }
}

