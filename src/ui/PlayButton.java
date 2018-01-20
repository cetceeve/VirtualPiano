package ui;

import constants.Configuration;
import interfaces.RecorderButton;
import recorder.RecorderInterfaceListener;

public class PlayButton extends Button implements RecorderButton {

    public PlayButton(int posX, int posY, int size) {
        super(posX, posY, size, Configuration.IMAGE_SOURCE_PLAY_BUTTON);
    }

    public void switchRepresentation() {
        if (this.getRepresentation().equals(Configuration.IMAGE_SOURCE_PLAY_BUTTON)) {
            this.changeRepresentation(Configuration.IMAGE_SOURCE_STOP_BUTTON);
        } else {
            this.changeRepresentation(Configuration.IMAGE_SOURCE_PLAY_BUTTON);
        }
    }

    @Override
    public void executeRecorderFunction(RecorderInterfaceListener recorderInterfaceListener) {
        recorderInterfaceListener.togglePlayback();
    }
}
