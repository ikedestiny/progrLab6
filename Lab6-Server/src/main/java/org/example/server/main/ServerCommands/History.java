package org.example.server.main.ServerCommands;

import lombok.Getter;
import lombok.Setter;
import org.example.common.exception.IllegalValueException;
import org.example.common.util.ServerResponse;
import org.example.common.util.Status;
import org.example.server.main.ResponseSender.Commander;

@Getter
@Setter
public class History extends NoArgCommand {
    private Commander commander;
    private StringBuilder builder = new StringBuilder();

    public History(Commander commander) {
        super("history", " Print the last 15 commands (without their arguments)");
        this.commander = commander;
    }


    @Override
    public ServerResponse execute(Object argument) throws IllegalValueException {
        int size = this.getCommander().getHistory().size();
        if (size > 15) {
            for (int i = size - 15; i <= size - 1; i++) {
                builder.append(this.getCommander().getHistory().get(i)).append("\n");
            }
        } else {
            for (var com : this.getCommander().getHistory()) {
                builder.append(com).append("\n");
            }
        }
        return response();
    }

    @Override
    public ServerResponse response() {
        var c = builder;
        builder = new StringBuilder();
        return new ServerResponse(Status.SUCCESS, "\n" + c, null);
    }
}
