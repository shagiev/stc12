package stc12.sockets.client;

import stc12.sockets.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientSocketThread extends Thread {
    private Socket socket;
    private ChatClient chatClient;
    private Logger logger;
    private BufferedReader reader;

    public ClientSocketThread(Socket socket, ChatClient chatClient, Logger logger) {
        this.socket = socket;
        this.chatClient = chatClient;
        this.logger = logger;

        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            logger.log(e);
        }
    }

    public void run() {
        while (!socket.isClosed()) {
            String message = readFromServer();
            if (message == null) {
                chatClient.close();
            } else {
                outputMessage(message);
            }
        }
    }

    private void outputMessage(String message) {
        System.out.println(message);
    }

    private String readFromServer() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            logger.log(e);
        }
        return null;
    }
}
