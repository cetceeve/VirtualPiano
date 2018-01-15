package ui;

import constants.Configuration;

public class PlayButton extends Button {

    public PlayButton(int posX, int posY, int size) {
        super(posX, posY, size, Configuration.IMAGE_SOURCE_PLAY_BUTTON);
    }

    public void switchRepresentation() {
        if (this.getRepresentation().equals(Configuration.IMAGE_SOURCE_PLAY_BUTTON)) {
            this.changeRepresentation(Configuration.IMAGE_SOURCE_PAUSE_BUTTON);
        } else {
            this.changeRepresentation(Configuration.IMAGE_SOURCE_PLAY_BUTTON);
        }
    }
}
