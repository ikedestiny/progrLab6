package org.example.server.main.ServerCommands;


import org.example.common.util.ServerResponse;
import org.example.common.util.Status;
import org.example.server.main.ResponseSender.Commander;

import java.util.ArrayList;

public class Help extends NoArgCommand {
    private final Commander commands;

    public Help(Commander commands) {
        super("help", "Prints out all Available ServerCommands and their description");

        this.commands = commands;
    }

    @Override
    public ServerResponse execute(Object argument) {
        System.out.println();
        commands.getCommands().forEach(s -> {
            System.out.println(s.getName().toLowerCase() + " --> " + s.getDescription());
        });
        System.out.println();
        System.out.println("ALL ARGUMENTS ARE THE LAST ITEM ENTERED AFTER THE LAST COMMA(,)");
        return response();
    }

    @Override
    public ServerResponse response() {
        ArrayList<String> desc = new ArrayList<>();
        commands.getCommands().forEach(s -> {
            desc.add("\n" + s.getName() + " --> " + s.getDescription());
        });

        return new ServerResponse(Status.SUCCESS, desc.toString(), null);
    }
}
