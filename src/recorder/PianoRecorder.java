package recorder;

import piano.PianoKey;

import java.util.ArrayList;

public class PianoRecorder implements Recorder {
    private ArrayList<RecorderDataPoint> recording;
    private boolean isRecording = false;

    public PianoRecorder() {
        recording = new ArrayList<>();
    }

    @Override
    public void saveDataPoint(PianoKey key, int velocity, long waitTime) {
        RecorderDataPoint dataPoint = new RecorderDataPoint(key, velocity, waitTime);
        recording.add(dataPoint);
    }

    @Override
    public boolean doRecording() {
        return isRecording;
    }

    @Override
    public void playRecording() {
        if (!recording.isEmpty() && !isRecording) {
            for (RecorderDataPoint dataPoint : recording) {
                try {
                    Thread.sleep(dataPoint.getWaitTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dataPoint.getKey().playNote(dataPoint.getVelocity());
            }
        }
    }
    
    @Override
    public void startRecording() {
        if (deleteRecording()) {
            isRecording = true;
        }
    }

    @Override
    public void stopRecording() {
        if (!recording.isEmpty()) {
            postprocessing();
        }
        isRecording = false;
    }

    @Override
    public boolean deleteRecording() {
        if (!isRecording) {
            recording.clear();
            return true;
        } else {
            return false;
        }
    }

    private void postprocessing() {
        recording.get(0).setWaitTime(0);
    }

}
