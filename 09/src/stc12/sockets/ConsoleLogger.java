package stc12.sockets;

public class ConsoleLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println(message);
    }

    @Override
    public void log(Exception exception) {
        System.out.println(exception.getMessage());
        exception.printStackTrace();
    }
}
