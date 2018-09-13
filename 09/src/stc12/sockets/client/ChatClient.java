package stc12.sockets.client;

import stc12.sockets.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private Socket socket;
    private BufferedWriter writer;
    private Scanner reader;
    private Logger logger;

    public ChatClient (String host, Integer port, Logger logger) {
        this.logger = logger;

        try {
            socket = new Socket(host, port);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            this.logger.log(e);
        }

        reader = new Scanner(System.in);

        new ClientSocketThread(socket, this, logger).start();
    }

    public void start() {
        while (!socket.isClosed()) {
            String message = reader.nextLine();
            try {
                writer.write(message + "\r\n");
                writer.flush();
            } catch (IOException e) {
                logger.log(e);
                close();
            }
        }
    }

    void close() {
        try {
            socket.close();
        } catch (IOException e) {
            logger.log(e);
        }
    }
}
