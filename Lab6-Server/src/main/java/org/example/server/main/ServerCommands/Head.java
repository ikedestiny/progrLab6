package org.example.server.main.ServerCommands;


import org.example.common.data.SpaceMarine;
import org.example.common.util.ServerResponse;
import org.example.common.util.Status;
import org.example.server.main.ResponseSender.Commander;

import java.util.ArrayList;


public class Head extends NoArgCommand {
    private final Commander commander;
    ArrayList<SpaceMarine> marines = new ArrayList<>();


    public Head(Commander commander) {
        super("head", "prints first element in the collection");

        this.commander = commander;
    }


    @Override
    public ServerResponse execute(Object argument) {
        System.out.println();
        System.out.println(this.commander.getCollectionManager().getPriorityQueue().peek());
        return response();
    }

    @Override
    public ServerResponse response() {
        marines.add(this.commander.getCollectionManager().getPriorityQueue().peek());
        return new ServerResponse(Status.SUCCESS, "", marines);
    }


}
