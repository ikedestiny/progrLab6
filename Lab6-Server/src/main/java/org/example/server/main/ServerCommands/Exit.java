package org.example.server.main.ServerCommands;


import org.example.common.util.ServerResponse;
import org.example.common.util.Status;


public class Exit extends NoArgCommand {
    public Exit() {
        super("exit", "terminates the program without saving anything");
    }

    @Override
    public ServerResponse execute(Object argument) {
        System.exit(1);
        return response();
    }

    @Override
    public ServerResponse response() {
        return new ServerResponse(Status.SUCCESS, "Program Ended", null);
    }
}
