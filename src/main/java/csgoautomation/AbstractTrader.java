package csgoautomation;

import java.util.concurrent.locks.ReentrantLock;

public class AbstractTrader extends Thread {
    protected volatile boolean paused = true;
    protected volatile boolean notNotified = true;

    public void pause() {
        paused = true;
        notNotified = true;
        while(notNotified);
    }

    public boolean isPaused() {
        return paused;
    }

    public void go() {
        paused = false;
    }

    protected void checkLock() {
        while(paused) notNotified = false;
    }
}
