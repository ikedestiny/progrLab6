package org.example.server.main.ServerCommands;


import lombok.Getter;
import lombok.Setter;
import org.example.common.exception.IllegalValueException;
import org.example.common.util.ClientRequest;
import org.example.common.util.ServerResponse;
import org.example.common.util.Status;
import org.example.server.main.ResponseSender.Commander;

@Getter
@Setter
public class RemoveById extends IntegerArgumentCommand {
    private final Commander commander;

    public RemoveById(Commander commander) {
        super("removeById",
                "removes space marine whose id matches the given one from the collection");
        this.commander = commander;
    }


    @Override
    public ServerResponse execute(Object argument) throws IllegalValueException {
        ClientRequest clientRequest = (ClientRequest) argument;

        try {
            System.out.println();
            this.commander.getCollectionManager().removeById(Integer.parseInt((String) clientRequest.getArgument()));
            return response();
        } catch (NumberFormatException e) {
            throw new IllegalValueException("The format of the id wasnt right. Its supposed to be a number");
        }
    }

    @Override
    public ServerResponse response() {
        return new ServerResponse(Status.SUCCESS, "Space marine removed", null);
    }
}
