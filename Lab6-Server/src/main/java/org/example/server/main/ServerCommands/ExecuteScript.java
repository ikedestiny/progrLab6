package org.example.server.main.ServerCommands;


import org.example.common.exception.IllegalValueException;
import org.example.common.exception.InvalidInputException;
import org.example.common.util.ClientRequest;
import org.example.common.util.ServerResponse;
import org.example.common.util.Status;
import org.example.server.main.ResponseSender.Commander;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ExecuteScript extends NoArgCommand {

    private final Commander commander;
    private final Scanner scanner = new Scanner(new BufferedReader(new FileReader(System.getenv("PATH_TO_SCRIPT"))));


    public ExecuteScript(Commander commander) throws FileNotFoundException {
        super("executeScript", "collects a list of ServerCommands and executes all the ServerCommands");
        this.commander = commander;
    }


    @Override
    public ServerResponse execute(Object argument) throws IllegalValueException, IOException {
        String commandName;
        String command;
        String arg;
        while (scanner.hasNextLine()) {
            command = scanner.nextLine();
            commandName = command.split(" ")[0];
            if (command.split(" ").length > 1) {
                arg = command.split(" ")[1];
            } else {
                arg = "no args";
            }
            try {
                commander.sendResponse(new ClientRequest(commandName, arg));
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }

        }
        return response();
    }


    @Override
    public ServerResponse response() {
        return new ServerResponse(Status.SUCCESS, "All commands executed", null);
    }
}
