package stc12.multithreading;

/**
 * Monitor class for synchronization between threads.
 */
public class Monitor {
    private int time = 0;

    /**
     * Increase timer value to call from chronometer.
     */
    public void increaseTime() {
        time++;
    }

    /**
     * @return seconds from start.
     */
    public int getTime() {
        return time;
    }
}
