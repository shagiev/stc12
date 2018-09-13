package stc12.sockets.server;

import stc12.sockets.ConsoleLogger;

public class Main {
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer(new ConsoleLogger(), 2397);
        chatServer.run();
    }
}
