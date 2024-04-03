package org.example.server.main;

import org.example.server.main.RequestReader.Reader;
import org.example.server.main.ResponseSender.Sender;
import org.example.server.main.connectionReciever.ServerConnector;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.Iterator;

public class ServerClass {
    private final ServerConnector serverConnector;
    private final Reader reader;
    private final Sender sender;

    public ServerClass(ServerConnector serverConnector) throws IOException {
        this.serverConnector = serverConnector;
        this.reader = new Reader(serverConnector);
        this.sender = new Sender(serverConnector);
    }


    public void start() throws IOException, ClassNotFoundException {
        while (true) {
            serverConnector.getSelector().select();
            Iterator<SelectionKey> iterator = serverConnector.getSelector().selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    serverConnector.doAccept(key);
                } else if (key.isReadable()) {
                    reader.doRead(key);
                    //reader.read(key);

                } else if (key.isWritable()) {
                    sender.doWrite(key);
                    //sender.write(key);
                }
            }


        }
    }


}
