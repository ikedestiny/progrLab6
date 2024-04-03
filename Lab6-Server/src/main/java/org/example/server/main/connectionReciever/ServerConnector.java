package org.example.server.main.connectionReciever;

import lombok.Data;
import org.example.server.main.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

@Data
public class ServerConnector {
    private final Selector selector;
    private final ServerSocket serverSocket;
    private final int PORT;
    private final Logger logger = Logger.getLogger(Server.class.getName());
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;
    private ServerSocketChannel serverSocketChannel;

    public ServerConnector(int PORT) throws IOException {
        this.selector = Selector.open();
        this.serverSocketChannel = ServerSocketChannel.open();
        this.serverSocket = serverSocketChannel.socket();
        this.PORT = PORT;

    }


    public void connect() throws IOException {
        serverSocket.bind(new InetSocketAddress(PORT));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    }

    public void doAccept(SelectionKey key) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        SocketChannel channel = server.accept();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void setUpConnection() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(1234));

        SocketChannel soc = serverSocketChannel.accept();
        // ssc.configureBlocking(false);
        logger.info("connected to client" + soc.getRemoteAddress());
        this.outputStream = new ObjectOutputStream(soc.socket().getOutputStream());
        this.inputStream = new ObjectInputStream(soc.socket().getInputStream());
    }
}
