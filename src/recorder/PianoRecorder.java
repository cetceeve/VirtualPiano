package recorder;

import de.ur.mi.graphicsapp.GraphicsApp;
import piano.PianoKey;

import java.util.ArrayList;

public class PianoRecorder implements Recorder, RecorderPlaybackThreadListener {
    private RecorderPlaybackThread recorderPlaybackThread;
    private ArrayList<RecorderDataPoint> recording;
    private boolean isRecording = false;

    public PianoRecorder() {
        GraphicsApp.println("Init Recorder");
        recording = new ArrayList<>();
        GraphicsApp.println("Init Recorder Background Thread");
        recorderPlaybackThread = new RecorderPlaybackThread(this);
    }

    @Override
    public void saveDataPoint(PianoKey key, int velocity, long waitTime) {
        RecorderDataPoint dataPoint = new RecorderDataPoint(key, velocity, waitTime);
        GraphicsApp.println("New RecorderDataPoint: " + key + " | " + velocity + " | " + waitTime);
        recording.add(dataPoint);
    }

    @Override
    public ArrayList<RecorderDataPoint> getRecording() {
        return recording;
    }

    @Override
    public boolean doRecording() {
        return isRecording;
    }

    @Override
    public void toggleRecording() {
        if (isRecording) {
            stopRecording();
        } else {
            startRecording();
        }
    }

    private void startRecording() {
        if (deleteRecording()) {
            isRecording = true;
            GraphicsApp.println("Start Recording");
        }
    }

    private void stopRecording() {
        if (!recording.isEmpty()) {
            postprocessing();
        }
        isRecording = false;
        GraphicsApp.println("Stop Recording");
    }

    public boolean deleteRecording() {
        if (!isRecording && !recorderPlaybackThread.isAlive()) {
            recording.clear();
            GraphicsApp.println("Recording Deleted");
            return true;
        } else {
            return false;
        }
    }

    public void togglePlayback() {
        if (recorderPlaybackThread.isAlive()) {
            stopPlayback();
        } else {
            startPlayback();
        }
    }

    private void startPlayback() {
        if (!recording.isEmpty() && !isRecording && !recorderPlaybackThread.isAlive()) {
            GraphicsApp.println("Start Playback");
            try {
                GraphicsApp.println("Recorder Playback Thread State: " + recorderPlaybackThread.getState());
                if (recorderPlaybackThread.getState() == Thread.State.TERMINATED) {
                    GraphicsApp.println("Init Background Thread");
                    recorderPlaybackThread = new RecorderPlaybackThread(this);
                    GraphicsApp.println("Opening Background Thread");
                    recorderPlaybackThread.start();
                } else {
                    GraphicsApp.println("Opening Background Thread");
                    recorderPlaybackThread.start();
                }
            } catch (IllegalThreadStateException e) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }

    private void stopPlayback() {
        GraphicsApp.println("Stop Playback");
        try {
            recorderPlaybackThread.interrupt();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void postprocessing() {
        recording.get(0).setWaitTime(0);
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
                    GraphicsApp.println("Recorder Playback Thread Interrupted");
                    return;
                }
                dataPoint.getKey().playNote(dataPoint.getVelocity());
            }
            GraphicsApp.println("Closing Background Thread");
        }
    }

}
