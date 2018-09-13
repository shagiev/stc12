package stc12.sockets.server;

import stc12.sockets.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;

public class ChatServer {
    private Logger logger;
    private Integer port;
    private ArrayList<ServerSocketThread> serverSocketThreadList = new ArrayList<>();

    private HashSet<String> names;

    public ChatServer(Logger logger, Integer port) {
        this.logger = logger;
        this.port = port;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            do {
                Socket socket = serverSocket.accept();
                ServerSocketThread serverThread = new ServerSocketThread(socket, this, logger);
                serverThread.start();

                serverSocketThreadList.add(serverThread);


            } while (!serverSocketThreadList.isEmpty());

        } catch (IOException e) {
            logger.log(e);
        }
    }

    boolean checkNameExists(String name) {
        return !names.contains(name);
    }

    void addName(String userName) {
        names.add(userName);
    }

    void removeSocketThread(ServerSocketThread serverSocketThread, String userName) {
        this.names.remove(userName);
        this.serverSocketThreadList.remove(serverSocketThread);
    }

    void sendAll(String userName, String message, ServerSocketThread serverSocketThread) {
        for (ServerSocketThread socketThread: serverSocketThreadList) {
            if (socketThread != serverSocketThread) {
                serverSocketThread.send(userName, message);
            }
        }
    }
}
