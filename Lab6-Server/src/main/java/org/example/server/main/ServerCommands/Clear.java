package org.example.server.main.ServerCommands;

import lombok.Getter;
import lombok.Setter;
import org.example.common.util.ServerResponse;
import org.example.common.util.Status;
import org.example.server.main.ResponseSender.Commander;


@Setter
@Getter
public class Clear extends NoArgCommand {
    private final Commander commander;

    public Clear(Commander commander) {
        super("clear", "remove all element from collection");
        this.commander = commander;
    }

    @Override
    public ServerResponse execute(Object argument) {
        System.out.println();
        this.commander.getCollectionManager().clear();
        return response();
    }

    @Override
    public ServerResponse response() {
        return new ServerResponse(Status.SUCCESS, "All clear", null);
    }
}
