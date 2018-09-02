package stc12.multithreading;

/**
 * Класс Chronometer отсчитывает каждую секунду и вызывает notifyAll по переданному монитору.
 */
public class Chronometer extends Thread {
    private final Monitor monitor;
    private int steps;

    /**
     * @param monitor - object for synchronization.
     * @param steps - seconds to work.
     */
    public Chronometer(Monitor monitor, int steps) {
        this.monitor = monitor;
        this.steps = steps;
    }

    @Override
    public void run () {
        long startTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + " started.");
        for (int i = 1; i <= steps; i++) {
            // Calculate time, leaved to start of next second.
            long sleepMillis = i * 1000 - (System.currentTimeMillis() - startTime);
            try {
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Секунда " + i);
            synchronized (monitor) {
                monitor.increaseTime();
                monitor.notifyAll();
            }
        }
        monitor.finish();
    }
}
