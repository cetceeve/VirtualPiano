package recorder;

import piano.PianoKey;

public class RecorderDataPoint {
    private PianoKey key;
    private int velocity;
    private long waitTime;

    public RecorderDataPoint(PianoKey key, int velocity, long waitTime) {
        this.key = key;
        this.velocity = velocity;
        this.waitTime = waitTime;
    }

    public PianoKey getKey() {
        return key;
    }

    public int getVelocity() {
        return velocity;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }
}
