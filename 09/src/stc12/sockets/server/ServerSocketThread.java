package stc12.sockets.server;

import stc12.sockets.Logger;

import java.io.*;
import java.net.Socket;

public class ServerSocketThread extends Thread {

    private Socket socket;
    private ChatServer chatServer;
    private Logger logger;

    private BufferedWriter writer;
    private BufferedReader reader;
    private String userName;

    public ServerSocketThread(Socket socket, ChatServer chatServer, Logger logger) {
        this.socket = socket;
        this.chatServer = chatServer;
        this.logger = logger;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            logger.log(e);
        }
    }

    @Override
    public void run() {
        askUserName();

        while (!socket.isClosed()) {
            String message = read();
            if (message == null) {
                continue;
            }
            if (message.equals("exit")) {
                close();
            }
            chatServer.sendAll(userName, message, this);
        }
    }

    void send(String userName, String message) {
        write(userName + ": " + message);
        write("\n");
    }

    private void askUserName() {
        while (this.userName == null) {
            write("Your name: ");
            String name = read();

            if (name == null || name.equals("")) {
                write("You entered empty name.");
            } else if (chatServer.checkNameExists(name)) {
                write("User with this name already exists. Choose other.");
            } else {
                this.userName = name;
            }

            chatServer.addName(userName);

            write("Welcome, " + userName + ". Send \"exit\" to quit chat.");
        }
    }

    private void write(String message) {
        try {
            writer.write(message);
            writer.write("\r\n");
            writer.flush();
        } catch (IOException e) {
            logger.log(e);
        }
    }

    private String read() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            logger.log(e);
        }
        return null;
    }

    private void close() {
        chatServer.removeSocketThread(this, userName);
        if (!socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                logger.log(e);
            }
        }
    }
}
