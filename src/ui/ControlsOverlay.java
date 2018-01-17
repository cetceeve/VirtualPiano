package ui;

import constants.Configuration;
import de.ur.mi.graphics.Image;

public class ControlsOverlay implements Drawable, Clickable{
    private Image repButton;
    private Image repOverlay;
    private boolean showOverlay = false;

    public ControlsOverlay(int posX, int posY) {
        repButton = new Image(posX, posY, Configuration.IMAGE_SOURCE_CONTROLS_BUTTON);
        repOverlay = new Image(-1, 0, Configuration.IMAGE_SOURCE_CONTROLS_OVERLAY);
    }

    @Override
    public void draw() {
        if (showOverlay) {
            repOverlay.draw();
        } else {
            repButton.draw();
        }
    }

    @Override
    public boolean hitTest(double x, double y) {
        if (showOverlay) {
            return repOverlay.hitTest(x, y);
        } else {
            return repButton.hitTest(x, y);
        }
    }

    public void toggleOverlay() {
        showOverlay = !showOverlay;
    }
}
