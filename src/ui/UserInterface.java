package ui;

import constants.Configuration;
import interfaces.Drawable;
import interfaces.RecorderButton;
import recorder.RecorderInterfaceListener;

import java.util.ArrayList;

/*
This class provides all user interface elements that are not connected to the piano itself (keys + slider).
 */
public class UserInterface implements Drawable, RecorderEventListener {
    private RecorderInterfaceListener recorderInterfaceListener;
    private ArrayList<RecorderButton> recorderButtons;
    private RecordingButton recordingButton;
    private PlayButton playButton;
    private DeleteButton deleteButton;
    private ControlsOverlay controlsOverlay;

    public UserInterface() {
        createRecorderButtons();
        createRecorderButtonArrayList();
        controlsOverlay = new ControlsOverlay(Configuration.CONTROLS_OVERLAY_POSITION_X, Configuration.CONTROLS_OVERLAY_POSITION_Y);
    }

    public void setRecorderInterfaceListener(RecorderInterfaceListener recorderInterfaceListener) {
        this.recorderInterfaceListener = recorderInterfaceListener;
    }

    @Override
    public void draw() {
        recordingButton.draw();
        playButton.draw();
        deleteButton.draw();
        controlsOverlay.draw();
    }

    public void toggleControlsOverlay() {
        controlsOverlay.toggleOverlay();
    }

    /////////////////////////////////////////////////
    /*
    RecorderEventListener methods
    (used be the pianoRecorder to control the ui)
     */
    @Override
    public void toggleRecordingButton() {
        recordingButton.switchRepresentation();
    }

    @Override
    public void togglePlaybackButton() {
        playButton.switchRepresentation();
    }

    /////////////////////////////////////////////////
    public boolean handleMouseClick(double x, double y) {
        for (RecorderButton c : recorderButtons) {
            if (c.hitTest(x, y)) {
                c.executeRecorderFunction(recorderInterfaceListener);
                return true;
            }
        }
        if (controlsOverlay.hitTest(x, y)) {
            controlsOverlay.toggleOverlay();
            return true;
        }
        return false;
    }

    /////////////////////////////////////////////////
    private void createRecorderButtons() {
        recordingButton = new RecordingButton(Configuration.RECORDING_BUTTON_POSITION_X, Configuration.RECORDING_BUTTON_POSITION_Y, Configuration.BUTTON_STANDARD_SIZE);
        playButton = new PlayButton(Configuration.PLAYBACK_BUTTON_POSITION_X, Configuration.PLAYBACK_BUTTON_POSITION_Y, Configuration.BUTTON_STANDARD_SIZE);
        deleteButton = new DeleteButton(Configuration.DELETE_BUTTON_POSITION_X, Configuration.DELETE_BUTTON_POSITION_Y, Configuration.BUTTON_STANDARD_SIZE);
    }

    private void createRecorderButtonArrayList() {
        recorderButtons = new ArrayList<>();
        recorderButtons.add(playButton);
        recorderButtons.add(recordingButton);
        recorderButtons.add(deleteButton);
    }

}
