package org.example.server.main.ServerCommands;

import lombok.Getter;
import org.example.common.data.SpaceMarine;
import org.example.common.util.ClientRequest;
import org.example.common.util.ServerResponse;
import org.example.common.util.Status;
import org.example.server.main.ResponseSender.Commander;

import java.util.ArrayList;
import java.util.InputMismatchException;

@Getter
public class FilterLessThanHealth extends IntegerArgumentCommand {
    private final Commander commander;
    StringBuilder builder = new StringBuilder();
    ArrayList<SpaceMarine> marines;

    public FilterLessThanHealth(Commander commander) {
        super("FilterLessThanHealth", "display elements whose health field value is less than the specified value");

        this.commander = commander;
    }

    @Override
    public ServerResponse execute(Object argument) {
        ClientRequest clientRequest = (ClientRequest) argument;
        marines = new ArrayList<>();

        try {

            for (SpaceMarine sp : this.commander.getCollectionManager().getPriorityQueue()
            ) {
                if (sp.getHealth() < Long.parseLong((String) clientRequest.getArgument())) {
                    builder.append(sp).append("\n");
                    marines.add(sp);
                }
            }

            return new ServerResponse(Status.SUCCESS, "All spacemarines with health less than " + clientRequest.getArgument(), marines);
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("You entered a wrong argument type for the health");
        }


        return response();
    }

    @Override
    public ServerResponse response() {
        var c = builder;
        builder = new StringBuilder();
        return new ServerResponse(Status.SUCCESS, "", marines);
    }
}
