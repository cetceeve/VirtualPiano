package recorder;

import constants.Configuration;
import de.ur.mi.graphicsapp.GraphicsApp;
import interfaces.Recorder;
import piano.PianoKey;
import ui.RecorderEventListener;

import java.util.ArrayList;

/*
This class provides the functionality to record any instrument working with the synthesizer and RecorderDataPoint class.
The Playback is done in a Background thread to make sure the application stays responsive.
All UserInterface functions associated with the recorder are controlled from this class.
 */
public class PianoRecorder implements Recorder, RecorderPlaybackThreadListener, RecorderInterfaceListener {
    private RecorderPlaybackThread recorderPlaybackThread;
    private ArrayList<RecorderDataPoint> recording;
    private RecorderEventListener userInterface;
    private boolean isRecording = false;

    public PianoRecorder(RecorderEventListener userInterface) {
        GraphicsApp.println("Init Recorder");
        this.userInterface = userInterface;
        recording = new ArrayList<>();
        GraphicsApp.println("Init Recorder Background Thread");
        recorderPlaybackThread = new RecorderPlaybackThread(this);
    }

    /////////////////////////////////////////////////
    /*
    Recorder interface methods
     */
    @Override
    public void saveDataPoint(PianoKey key, int velocity, long waitTime) {
        recording.add(new RecorderDataPoint(key, velocity, waitTime));
        GraphicsApp.println("New RecorderDataPoint: " + key + " | " + velocity + " | " + waitTime);
    }

    @Override
    public boolean doRecording() {
        return isRecording;
    }

    /////////////////////////////////////////////////
    /*
    RecorderPlaybackThreadListener methods
     */
    @Override
    public ArrayList<RecorderDataPoint> getRecording() {
        return recording;
    }

    @Override
    public void playbackStopped() {
        userInterface.togglePlaybackButton();
    }

    /////////////////////////////////////////////////
    /*
    RecorderInterfaceListener methods
     */
    @Override
    public void toggleRecording() {
        if (isRecording) {
            stopRecording();
        } else {
            startRecording();
        }
    }

    @Override
    public boolean deleteRecording() {
        if (!isRecording && !recorderPlaybackThread.isAlive()) {
            recording.clear();
            GraphicsApp.println("Recording Deleted");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void togglePlayback() {
        if (recorderPlaybackThread.isAlive()) {
            stopPlayback();
        } else {
            startPlayback();
        }
    }

    /////////////////////////////////////////////////
    private void startRecording() {
        if (deleteRecording()) {
            GraphicsApp.println("Start Recording");
            isRecording = true;
            userInterface.toggleRecordingButton();
        }
    }

    private void stopRecording() {
        GraphicsApp.println("Stop Recording");
        if (!recording.isEmpty()) {
            postprocessing();
        }
        isRecording = false;
        userInterface.toggleRecordingButton();
    }

    /*
    this methods starts the playback thread
    if the thread has already run (State == TERMINATED) it creates a new thread overwriting the old one
     */
    private void startPlayback() {
        if (!recording.isEmpty() && !isRecording && !recorderPlaybackThread.isAlive()) {
            GraphicsApp.println("Start Playback");
            try {
                GraphicsApp.println("Recorder Playback Thread State: " + recorderPlaybackThread.getState());
                if (recorderPlaybackThread.getState() == Thread.State.TERMINATED) {
                    GraphicsApp.println("Init New Background Thread");
                    recorderPlaybackThread = new RecorderPlaybackThread(this);
                    GraphicsApp.println("Opening Background Thread");
                    recorderPlaybackThread.start();
                } else {
                    GraphicsApp.println("Opening Background Thread");
                    recorderPlaybackThread.start();
                }
            } catch (IllegalThreadStateException e) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                e.printStackTrace();
            }
            userInterface.togglePlaybackButton();
        }
    }

    /*
    the interrupt call stops the threads execution
    the thread goes into State: TERMINATED
     */
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


    /////////////////////////////////////////////////
    /*
    thread to play back one recording
     */
    private class RecorderPlaybackThread extends Thread {
        private RecorderPlaybackThreadListener threadListener;

        public RecorderPlaybackThread(RecorderPlaybackThreadListener threadListener) {
            this.threadListener = threadListener;
        }

        /*
        get the most recent recording and replay every note + visualisation
        between playing the notes the thread waits the correct time
        all necessary information comes from each dataPoint
         */
        @Override
        public void run() {
            GraphicsApp.println("Background Thread Active");
            ArrayList<RecorderDataPoint> recording = threadListener.getRecording();
            for (RecorderDataPoint dataPoint : recording) {
                try {
                    Thread.sleep(dataPoint.getWaitTime());
                } catch (InterruptedException e) {
                    GraphicsApp.println("Recorder Playback Thread Interrupted");
                    break;
                }
                dataPoint.getKey().playNote(dataPoint.getVelocity());
                dataPoint.getKey().setColor(Configuration.KEY_PLAYBACK_COLOR);
                dataPoint.getKey().activateColorFadeOut();
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
            threadListener.playbackStopped();
            GraphicsApp.println("Closing Background Thread");
        }
    }

}
