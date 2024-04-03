package org.example.server.main.ServerCommands;


import lombok.Getter;
import lombok.Setter;
import org.example.common.data.SpaceMarine;
import org.example.common.util.ClientRequest;
import org.example.common.util.ServerResponse;
import org.example.common.util.Status;
import org.example.server.main.ResponseSender.Commander;


@Getter
@Setter
public class Add extends SpaceMarineArgumentCommand {
    private final Commander commander;
    private Status status = Status.FAILED;

    public Add(Commander commander) {
        super("add", "Add element to collection");
        this.commander = commander;

    }


    @Override
    public ServerResponse execute(Object argument) {
        try {
            ClientRequest clientRequest = (ClientRequest) argument;
            SpaceMarine spaceMarine = clientRequest.getAddParam();
            commander.getCollectionManager().getPriorityQueue().add(spaceMarine);
            status = Status.SUCCESS;
        } catch (NullPointerException e) {
            System.out.println("error in creating space marine");
            status = Status.FAILED;
        }
        return response();
    }

    @Override
    public ServerResponse response() {
        return new ServerResponse(getStatus(), "Space Marine Added", null);
    }
}
