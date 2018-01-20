package recorder;

import piano.PianoKey;

/*
This class is a container to store information for the Recorder and Synthesiser class.
In this case the class is specifically storing PianoKeys.
 */
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
    /*
    used for postprocessing of a recording
     */
    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }
}
