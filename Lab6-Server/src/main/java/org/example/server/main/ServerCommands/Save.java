package org.example.server.main.ServerCommands;


import org.example.common.util.CollectionManager;
import org.example.common.util.ServerResponse;
import org.example.common.util.Status;
import org.example.common.util.XmlParser;
import org.example.server.main.ResponseSender.Commander;

public class Save extends Command {
    private final CollectionManager collectionManager;
    private final XmlParser parser = new XmlParser();
    private final String fileName;


    /**
     * this class saves the collection to a file in xml format
     */
    public Save(Commander commander) {
        super("save", "saves the collection to a file");
        this.collectionManager = commander.getCollectionManager();
        this.fileName = commander.getXMLFilePath();
    }

    @Override
    public ServerResponse execute(Object argument) {
        parser.convertToXML(collectionManager, fileName);
        return response();
    }

    @Override
    public ServerResponse response() {
        return new ServerResponse(Status.SUCCESS, "Collection saved.", null);
    }
}
