package org.example.server.main.ServerCommands;


import org.example.common.util.ClientRequest;
import org.example.common.util.ServerResponse;
import org.example.common.util.Status;
import org.example.server.main.ResponseSender.Commander;

public class RemoveLower extends IntegerArgumentCommand {
    private final Commander commander;
    int id;


    public RemoveLower(Commander commander) {
        super("RemoveLower", "remove all elements from a collection smaller than a given one");
        this.commander = commander;
    }

    @Override
    public ServerResponse execute(Object argument) {
        ClientRequest clientRequest = (ClientRequest) argument;

        try {
            id = Integer.parseInt((String) clientRequest.getArgument());
            this.commander.getCollectionManager().getPriorityQueue().removeIf(s -> s.getId() < Integer.parseInt((String) clientRequest.getArgument()));
            return response();
        } catch (NumberFormatException e) {
            System.out.println("The format of the argument is not right, collects an Integer Id");
            return new ServerResponse(Status.FAILED, "The command was not properly stated", null);
        }
    }

    @Override
    public ServerResponse response() {
        return new ServerResponse(Status.SUCCESS, "Removed all space marines with id less than " + id, null);
    }
}
