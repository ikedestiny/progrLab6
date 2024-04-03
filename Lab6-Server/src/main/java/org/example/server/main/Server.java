package org.example.server.main;

import org.example.common.exception.IllegalValueException;
import org.example.common.exception.InvalidInputException;
import org.example.common.util.*;
import org.example.server.main.RequestReader.Reader;
import org.example.server.main.ResponseSender.Commander;
import org.example.server.main.ResponseSender.Sender;
import org.example.server.main.connectionReciever.ServerConnector;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Logger;

public class Server {
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) {
        try {
            XmlParser xmlParser = new XmlParser();
            CollectionManager collectionManager = xmlParser.convertXmlToObject(System.getenv("PATH_TO_XML"));
            Commander commander = new Commander(collectionManager, System.getenv("PATH_TO_XML"));
            ServerConnector serverConnector = new ServerConnector(1234);
            Reader reader = new Reader(serverConnector);
            Sender sender = new Sender(serverConnector);
            logger.info("Starting up server....");
            logger.info("Waitng for clients connections");
            serverConnector.setUpConnection();
            String name = "";
//            Map<String, CommandType> commandMap = new HashMap<>();
//            for (var comm: commander.getIntParamCommands()){
//                commandMap.put(comm.getName(),CommandType.INT);
//            }   this is the logiccccc
            sender.getServerConnector().getOutputStream().writeObject(commander.getCommandTypeMap());
            while (true) {
                ClientRequest clientRequest = reader.read();
                //Thread.sleep(10000);
                name = clientRequest.getCommandName();
                if (name == null) {
                    sender.write(new ServerResponse(Status.ERROR, "No such command", null));
                    continue;
                }
                if (name.equalsIgnoreCase("exit")) {
                    xmlParser.convertToXML(collectionManager, System.getenv("PATH_TO_XML"));
                    System.exit(1111);
                } else {
                    sender.write(commander.sendResponse(clientRequest));
                    xmlParser.convertToXML(collectionManager, System.getenv("PATH_TO_XML"));
                }
            }

        } catch (SocketException | InvalidInputException e) {
            logger.info(e.getMessage().toUpperCase());
        } catch (IOException | ClassNotFoundException | IllegalValueException | NullPointerException |
                 JAXBException e) {
            e.printStackTrace();
        }
    }
}
