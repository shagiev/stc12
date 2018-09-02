package stc12.multithreading;

/**
 * Monitor class for synchronization between threads.
 */
public class Monitor {
    private int time = 0;
    private boolean isFinished = false;

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

    /**
     * Set isFinished flag to stop all threads.
     */
    public void finish() {
        isFinished = true;
    }

    /**
     * Check if timer finished.
     * @return isFinished flag.
     */
    public boolean getIsFinished() {
        return isFinished;
    }
}
