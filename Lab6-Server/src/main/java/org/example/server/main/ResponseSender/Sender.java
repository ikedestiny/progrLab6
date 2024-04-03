package org.example.server.main.ResponseSender;

import lombok.Data;
import org.example.common.exception.IllegalValueException;
import org.example.common.exception.InvalidInputException;
import org.example.common.util.ServerResponse;
import org.example.server.main.connectionReciever.ServerConnector;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

@Data
public class Sender {
    private final ServerConnector serverConnector;

    public Sender(ServerConnector serverConnector) throws IOException {
        this.serverConnector = serverConnector;
    }


    public void doWrite(SelectionKey key) throws IOException, ClassNotFoundException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.flip();
        channel.write(buffer);
        if (buffer.hasRemaining()) {
            buffer.compact();
        } else {
            buffer.clear();
        }
        key.interestOps(SelectionKey.OP_READ);
    }


    public void write(ServerResponse serverResponse) throws InvalidInputException, IllegalValueException, IOException {
        //serverConnector.getLogger().info("sending response for request "+serverResponse.getStatus());
        serverConnector.getOutputStream().writeObject(serverResponse);
    }
}
