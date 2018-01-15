package piano;

import constants.Configuration;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Rect;
import ui.Drawable;

public class Slider extends Rect implements Drawable {
    public enum SliderPositions {
        CONTRA,
        GREAT,
        SMALL
    }

    private int endPosition;
    private int sliderPosition = Configuration.SLIDER_START_POSITION;
    private int movementDirection = 0;

    public Slider() {
        super(10 + (Configuration.SLIDER_WIDTH * Configuration.SLIDER_START_POSITION), Configuration.SLIDER_POSITION_Y, Configuration.SLIDER_WIDTH, Configuration.SLIDER_HEIGHT, Configuration.SLIDER_COLOR);
        this.setBorder(Color.BLACK, 1);
    }

    public void update() {
        if (this.getX() != endPosition) {
            this.move(Configuration.SLIDER_MOVEMENT_SPEED * movementDirection, 0);
        }
        if (this.getX() == endPosition)
            movementDirection = 0;

    }

    public void moveRight() {
        sliderPosition++;
        endPosition = 10 + sliderPosition * Configuration.SLIDER_WIDTH;
        movementDirection = 1;
    }

    public void moveRight(SliderPositions sP) {
        sliderPosition = sP.ordinal();
        endPosition = 10 + sliderPosition * Configuration.SLIDER_WIDTH;
        movementDirection = 1;
    }

    public void moveLeft() {
        sliderPosition--;
        endPosition = 10 + sliderPosition * Configuration.SLIDER_WIDTH;
        movementDirection = -1;
    }

    public void moveLeft(SliderPositions sP) {
        sliderPosition = sP.ordinal();
        endPosition = 10 + sliderPosition * Configuration.SLIDER_WIDTH;
        movementDirection = -1;
    }
}
