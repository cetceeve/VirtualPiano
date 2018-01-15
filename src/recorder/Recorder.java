package recorder;

import piano.PianoKey;

public interface Recorder {

    public void saveDataPoint(PianoKey key, int velocity, long waitTime);
    public boolean doRecording();
}
