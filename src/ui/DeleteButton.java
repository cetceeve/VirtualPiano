package ui;

import constants.Configuration;
import recorder.RecorderInterfaceListener;

public class DeleteButton extends Button implements RecorderButton {

    public DeleteButton(int posX, int posY, int size) {
        super(posX, posY, size, Configuration.IMAGE_SOURCE_DELETE_BUTTON);
    }

    @Override
    public void executeRecorderFunction(RecorderInterfaceListener recorderInterfaceListener) {
        recorderInterfaceListener.deleteRecording();
    }
}
