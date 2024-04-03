package org.example.server.main.RequestReader;


import lombok.Data;
import org.example.common.util.ClientRequest;
import org.example.server.main.connectionReciever.ServerConnector;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

@Data
public class Reader {
    private final ServerConnector serverConnector;

    public Reader(ServerConnector serverConnector) throws IOException {
        this.serverConnector = serverConnector;
    }


    public void doRead(SelectionKey key) throws IOException, ClassNotFoundException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        channel.read(buffer);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        ClientRequest clientRequest = (ClientRequest) objectInputStream.readObject();

//        Object object = channel.write(buffer);
//        System.out.println(object.toString());
        key.interestOps(SelectionKey.OP_WRITE);

    }


    public ClientRequest read() throws IOException, ClassNotFoundException {
        ClientRequest clientRequest = (ClientRequest) this.serverConnector.getInputStream().readObject();
        this.serverConnector.getLogger().info("Read..." + clientRequest.toString());
        return clientRequest;
    }


}
