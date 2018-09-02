package stc12.multithreading;

public class Main {

    public static void main(String[] args) {
        Monitor monitor = new Monitor();

        Counter[] counters = {
                new Counter(5, monitor),
                new Counter(7, monitor)
        };
        for (Counter counter : counters) {
            counter.start();
        }
        Chronometer chronometer = new Chronometer(monitor, 50);
        chronometer.start();

    }
}
