package org.example.server.main.ServerCommands;


import lombok.Getter;
import lombok.Setter;
import org.example.common.data.SpaceMarine;
import org.example.common.util.ClientRequest;
import org.example.common.util.CollectionManager;
import org.example.common.util.ServerResponse;
import org.example.common.util.Status;
import org.example.server.main.ResponseSender.Commander;

@Getter
@Setter
public class UpdateById extends SpaceMarineArgumentCommand {
    private final CollectionManager collectionManager;
    private final Commander commander;
    private Status status = Status.FAILED;

    public UpdateById(CollectionManager collectionManager, Commander commander) {
        super("UpdateById", "updates a given element in the collection by its id");
        this.collectionManager = collectionManager;
        this.commander = commander;
    }


    @Override
    public ServerResponse execute(Object argument) {

        ClientRequest clientRequest = (ClientRequest) argument;
        if (clientRequest.getAddParam() == null) {
            return new ServerResponse(Status.ERROR, "Space Marine not properly formed", null);
        }

        for (SpaceMarine sp : commander.getCollectionManager().getPriorityQueue()) {
            if (sp.getId() == Integer.parseInt((String) clientRequest.getArgument())) {
                commander.getCollectionManager().
                        updateById(clientRequest.getAddParam(),
                                Integer.parseInt((String) clientRequest.getArgument()));
                status = Status.SUCCESS;
            }
        }
        return response();
    }

    @Override
    public ServerResponse response() {
        return new ServerResponse(status, "The SpaceMarine has been Updated", null);
    }


}
