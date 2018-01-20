package ui;

import constants.Configuration;
import interfaces.RecorderButton;
import recorder.RecorderInterfaceListener;

public class DeleteButton extends Button implements RecorderButton {

    public DeleteButton(int posX, int posY, int size) {
        super(posX, posY, size, Configuration.DELETE_BUTTON_IMAGE);
    }

    @Override
    public void executeRecorderFunction(RecorderInterfaceListener recorderInterfaceListener) {
        recorderInterfaceListener.deleteRecording();
    }
}
