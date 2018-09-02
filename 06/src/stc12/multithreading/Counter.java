package stc12.multithreading;

/**
 * Counter class, that does work every x seconds.
 */
public class Counter extends Thread {
    private int interval;
    private Monitor monitor;

    /**
     * @param interval - seconds between jobs.
     * @param monitor - object for synchronization.
     */
    public Counter(int interval, Monitor monitor) {
        this.interval = interval;
        this.monitor = monitor;
    }

    public void run() {
        int lastTime = 0;
        while (!monitor.getIsFinished()) {
            synchronized (monitor) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int currentTime = monitor.getTime();
            if (currentTime != lastTime && (currentTime % interval) == 0) {
                System.out.println(interval + " секунд");
                lastTime = currentTime;
            }
        }
    }
}
