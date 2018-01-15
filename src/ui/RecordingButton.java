package ui;

import constants.Configuration;
import recorder.RecorderInterfaceListener;

public class RecordingButton extends Button implements ToggleButton, RecorderButton {
    public RecordingButton(int posX, int posY, int size) {
        super(posX, posY, size, Configuration.IMAGE_SOURCE_RECORDING_ON_BUTTON);
    }

    @Override
    public void switchRepresentation() {
        if (this.getRepresentation().equals(Configuration.IMAGE_SOURCE_RECORDING_ON_BUTTON)) {
            this.changeRepresentation(Configuration.IMAGE_SOURCE_RECORDING_OFF_BUTTON);
        } else {
            this.changeRepresentation(Configuration.IMAGE_SOURCE_RECORDING_ON_BUTTON);
        }
    }

    @Override
    public void executeRecorderFunction(RecorderInterfaceListener recorderInterfaceListener) {
        recorderInterfaceListener.toggleRecording();
    }
}
