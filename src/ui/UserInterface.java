package ui;

import constants.Configuration;

public class UserInterface implements Drawable {
    private Slider slider;
    private RecordingButton recordingButton;
    private PlayButton playButton;
    private Button deleteButton;

    public UserInterface() {
        slider = new Slider();
        playButton = new PlayButton(Configuration.PLAY_BUTTON_POSITION_X, Configuration.PLAY_BUTTON_POSITION_Y, Configuration.BUTTON_STANDARD_SIZE);
        recordingButton = new RecordingButton(Configuration.RECORDING_BUTTON_POSITION_X, Configuration.RECORDING_BUTTON_POSITION_Y, Configuration.BUTTON_STANDARD_SIZE);
        deleteButton = new Button(Configuration.DELETE_BUTTON_POSITION_X, Configuration.DELETE_BUTTON_POSITION_Y, Configuration.BUTTON_STANDARD_SIZE, Configuration.IMAGE_SOURCE_DELETE_BUTTON);
    }

    @Override
    public void draw() {
        slider.update();
        recordingButton.draw();
        playButton.draw();
        deleteButton.draw();
    }

    public void toggleRecording() {
        recordingButton.switchRepresentation();
    }

    public void toggleReplay() {
        playButton.switchRepresentation();
    }

}
