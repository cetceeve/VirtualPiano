package ui;

import constants.Configuration;
import de.ur.mi.graphics.Image;
import interfaces.Clickable;
import interfaces.Drawable;

/*
This class is not actually an overlay but it acts like one.
Actually the class works similar to the Button class but is too specific.
This class is both the controls Button on the top left and an image that is placed on top of the full screen,
showing the user all controls.
 */
public class ControlsOverlay implements Drawable, Clickable {
    private Image repButton;
    private Image repOverlay;
    private boolean showOverlay = false;

    public ControlsOverlay(int posX, int posY) {
        repButton = new Image(posX, posY, Configuration.CONTROLS_BUTTON_IMAGE);
        repOverlay = new Image(-1, 0, Configuration.CONTROLS_OVERLAY_IMAGE);
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
