package org.example.server.main.ServerCommands;


import lombok.Getter;
import lombok.Setter;
import org.example.common.data.SpaceMarine;
import org.example.common.util.ClientRequest;
import org.example.common.util.ServerResponse;
import org.example.common.util.Status;
import org.example.server.main.ResponseSender.Commander;

import java.util.ArrayList;

@Setter
@Getter
public class FilterStartsWithName extends StringArgumentCommand {
    private final Commander commander;
    private StringBuilder builder = new StringBuilder();
    private ArrayList<SpaceMarine> marines;

    public FilterStartsWithName(Commander commander) {
        super("FilterStartsWithName", "display elements whose name field value begins with the given substring");

        this.commander = commander;
    }

    @Override
    public ServerResponse execute(Object argument) {
        ClientRequest clientRequest = (ClientRequest) argument;
        marines = new ArrayList<>();
        var arg = clientRequest.getArgument();

        for (SpaceMarine sp : this.getCommander().getCollectionManager().getPriorityQueue()) {
            if (sp.getName().startsWith((String) arg)) {
                builder.append(sp).append("\n");
                marines.add(sp);
            }
        }
        return new ServerResponse(Status.SUCCESS, "All SpaceMarines wiht name starting with " + clientRequest.getArgument(), marines);
    }

    @Override
    public ServerResponse response() {
        var c = builder;
        builder = new StringBuilder();
        return new ServerResponse(Status.SUCCESS, "", marines);
    }
}
