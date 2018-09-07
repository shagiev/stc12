package stc12.sockets.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 68732)) {
            OutputStreamWriter serverOutput = new OutputStreamWriter(socket.getOutputStream());
            InputStreamReader serverInput = new InputStreamReader(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            String message;
            BufferedReader bufferedReader = new BufferedReader(serverInput);
            while (!(message = scanner.nextLine()).equals("exit")) {
                BufferedWriter bufferedWriter = new BufferedWriter(serverOutput);
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                System.out.println("Server echo: " + bufferedReader.readLine());
            }
        } catch (UnknownHostException e) {
            System.out.println("UnknownHostException");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
    }

}
