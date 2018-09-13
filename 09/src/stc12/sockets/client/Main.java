package stc12.sockets.client;

import stc12.sockets.ConsoleLogger;

public class Main {
    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient("127.0.0.1", 2397, new ConsoleLogger());
        chatClient.start();
    }

}
