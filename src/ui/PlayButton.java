package ui;

import constants.Configuration;
import interfaces.RecorderButton;
import recorder.RecorderInterfaceListener;

public class PlayButton extends Button implements RecorderButton {

    public PlayButton(int posX, int posY, int size) {
        super(posX, posY, size, Configuration.PLAYBACK_BUTTON_IMAGE_PLAY);
    }

    public void switchRepresentation() {
        if (this.getRepresentation().equals(Configuration.PLAYBACK_BUTTON_IMAGE_PLAY)) {
            this.changeRepresentation(Configuration.PLAYBACK_BUTTON_IMAGE_STOP);
        } else {
            this.changeRepresentation(Configuration.PLAYBACK_BUTTON_IMAGE_PLAY);
        }
    }

    @Override
    public void executeRecorderFunction(RecorderInterfaceListener recorderInterfaceListener) {
        recorderInterfaceListener.togglePlayback();
    }
}
