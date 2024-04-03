package org.example.server.main.ServerCommands;


import lombok.Getter;
import lombok.Setter;
import org.example.common.util.CollectionManager;
import org.example.common.util.ServerResponse;
import org.example.common.util.Status;
import org.example.server.main.ResponseSender.Commander;

@Getter
@Setter
public class Info extends NoArgCommand {
    private final CollectionManager collectionManager;
    private final Commander commander;

    public Info(Commander commander) {
        super("info", "prints info about collection like size creation date etc");
        this.commander = commander;
        this.collectionManager = commander.getCollectionManager();
    }

    @Override
    public ServerResponse execute(Object argument) {
        System.out.println();
        return response();
    }

    @Override
    public ServerResponse response() {
        String res = "Collection type    -- " + this.getCollectionManager().getPriorityQueue().getClass().getSimpleName() + "\n" +
                " Number of elements -- " + this.getCollectionManager().getPriorityQueue().size() + "\n" +
                "Creation date      -- " + this.getCollectionManager().getCreationDate();
        return new ServerResponse(Status.SUCCESS, res, null);
    }
}
