package org.example.client.util;


import org.example.common.util.ClientRequest;
import org.example.common.util.Serializer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class ConsoleManager {
    private final OutputStream out;
    private final InputStream in;
    Scanner scanner = new Scanner(new BufferedInputStream(System.in));
    ClientRequest clientRequest = new ClientRequest();
    SpaceMarineCreator spaceMarineCreator = new SpaceMarineCreator();


    /**
     * class responsible for reading commands and its args from console
     */
    public ConsoleManager(OutputStream out, InputStream in) {

        this.out = out;
        this.in = in;
    }

    public void Start() throws IOException, ClassNotFoundException {
        //checkXml();
        String fullRequest = "";
        System.out.println("You can enter commands, Enter \\help\\ for more info...");


        while (!fullRequest.equalsIgnoreCase("exit") && scanner.hasNext()) {
            fullRequest = scanner.nextLine();
            String commandName = fullRequest.split(" ")[0];
            Object commandObject = null;
            var split = fullRequest.split(" ");
            String argument = split.length > 1 ? split[1] : "no args";

            for (String command : ClientRequest.clientReqs) {
                if (commandName.equalsIgnoreCase(command)) {
                    commandObject = new ClientRequest(commandName, argument);
                }
            }

            if (commandObject == null) {
                System.out.println("no such command");
            } else {
                var obj = Serializer.serialize(commandObject);
                out.write(obj);
            }
            var response = in.readAllBytes();
            // System.out.println(Serializer.deserialize(response));
            out.flush();


        }
    }


}
