package recorder;

import de.ur.mi.graphicsapp.GraphicsApp;
import piano.PianoKey;

import java.util.ArrayList;

public class PianoRecorder implements Recorder, RecorderPlaybackThreadListener {
    private RecorderPlaybackThread recorderPlaybackThread;
    private ArrayList<RecorderDataPoint> recording;
    private boolean isRecording = false;

    public PianoRecorder() {
        recording = new ArrayList<>();
        GraphicsApp.println("Init Background Thread");
        recorderPlaybackThread = new RecorderPlaybackThread(this);
        GraphicsApp.println("Background Thread Ready");
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
        if (!recording.isEmpty() && !isRecording && !recorderPlaybackThread.isAlive()) {
            GraphicsApp.println("Opening Background Thread");
            recorderPlaybackThread.start();
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

    @Override
    public ArrayList<RecorderDataPoint> getRecording() {
        return recording;
    }


    private class RecorderPlaybackThread extends Thread {
        private RecorderPlaybackThreadListener threadListener;

        public RecorderPlaybackThread(RecorderPlaybackThreadListener threadListener) {
            this.threadListener = threadListener;
        }

        @Override
        public void run() {
            GraphicsApp.println("Background Thread Active");
            ArrayList<RecorderDataPoint> recording = threadListener.getRecording();
            for (RecorderDataPoint dataPoint : recording) {
                try {
                    Thread.sleep(dataPoint.getWaitTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dataPoint.getKey().playNote(dataPoint.getVelocity());
            }
            GraphicsApp.println("Closing Background Thread");
        }
    }

}
