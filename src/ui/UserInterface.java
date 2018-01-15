package ui;

import constants.Configuration;
import piano.Slider;
import recorder.RecorderInterfaceListener;

import java.util.ArrayList;

public class UserInterface implements Drawable, RecorderEventListener {
    private RecorderInterfaceListener recorderInterfaceListener;
    private ArrayList<RecorderButton> recorderButtons;
    private RecordingButton recordingButton;
    private PlayButton playButton;
    private DeleteButton deleteButton;

    public UserInterface() {
        createButtons();
        createRecorderButtonArrayList();
    }

    public void setRecorderInterfaceListener(RecorderInterfaceListener recorderInterfaceListener) {
        this.recorderInterfaceListener = recorderInterfaceListener;
    }

    @Override
    public void draw() {
        recordingButton.draw();
        playButton.draw();
        deleteButton.draw();
    }

    public void handleMouseClick(double x, double y) {
        for (RecorderButton c : recorderButtons) {
            if (c.hitTest(x, y)) {
                c.executeRecorderFunction(recorderInterfaceListener);
                break;
            }
        }
    }

    @Override
    public void toggleRecordingButton() {
        recordingButton.switchRepresentation();
    }

    @Override
    public void togglePlaybackButton() {
        playButton.switchRepresentation();
    }

    private void createButtons() {
        recordingButton = new RecordingButton(Configuration.RECORDING_BUTTON_POSITION_X, Configuration.RECORDING_BUTTON_POSITION_Y, Configuration.BUTTON_STANDARD_SIZE);
        playButton = new PlayButton(Configuration.PLAY_BUTTON_POSITION_X, Configuration.PLAY_BUTTON_POSITION_Y, Configuration.BUTTON_STANDARD_SIZE);
        deleteButton = new DeleteButton(Configuration.DELETE_BUTTON_POSITION_X, Configuration.DELETE_BUTTON_POSITION_Y, Configuration.BUTTON_STANDARD_SIZE);
    }

    private void createRecorderButtonArrayList() {
        recorderButtons = new ArrayList<>();
        recorderButtons.add(playButton);
        recorderButtons.add(recordingButton);
        recorderButtons.add(deleteButton);
    }

}
