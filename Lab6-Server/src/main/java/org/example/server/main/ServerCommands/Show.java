package org.example.server.main.ServerCommands;


import lombok.Getter;
import lombok.Setter;
import org.example.common.data.SpaceMarine;
import org.example.common.util.ServerResponse;
import org.example.common.util.Status;
import org.example.server.main.ResponseSender.Commander;

import java.util.ArrayList;

@Setter
@Getter
public class Show extends NoArgCommand {
    private final Commander commander;
    ArrayList<SpaceMarine> marines = new ArrayList<>();

    public Show(Commander commander) {
        super("show", "prints all the elements in the collection");
        this.commander = commander;
    }

    @Override
    public ServerResponse execute(Object argument) {
        return response();
    }


    public ServerResponse response() {
        marines.addAll(commander.getCollectionManager().getPriorityQueue());
        return new ServerResponse(Status.SUCCESS, "", marines);
    }
}
