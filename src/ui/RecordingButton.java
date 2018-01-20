package ui;

import constants.Configuration;
import interfaces.RecorderButton;
import recorder.RecorderInterfaceListener;

public class RecordingButton extends Button implements RecorderButton {
    public RecordingButton(int posX, int posY, int size) {
        super(posX, posY, size, Configuration.RECORDING_BUTTON_IMAGE_START);
    }

    public void switchRepresentation() {
        if (this.getRepresentation().equals(Configuration.RECORDING_BUTTON_IMAGE_START)) {
            this.changeRepresentation(Configuration.RECORDING_BUTTON_IMAGE_STOP);
        } else {
            this.changeRepresentation(Configuration.RECORDING_BUTTON_IMAGE_START);
        }
    }

    @Override
    public void executeRecorderFunction(RecorderInterfaceListener recorderInterfaceListener) {
        recorderInterfaceListener.toggleRecording();
    }
}
