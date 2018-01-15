package ui;

import constants.Configuration;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Rect;

public class Slider extends Rect implements Drawable{
    public enum SliderPositions {
        CONTRA,
        GREAT,
        SMALL
    }

    private int endPosition;
    private int sliderPosition = Configuration.SLIDER_START_POSITION;
    private int movementDirection = 1;

    public Slider() {
        super(10 + (Configuration.SLIDER_WIDTH * Configuration.SLIDER_START_POSITION), Configuration.SLIDER_POSITION_Y, Configuration.SLIDER_WIDTH, Configuration.SLIDER_HEIGHT, Configuration.SLIDER_COLOR);
        this.setBorder(Color.BLACK, 2);
    }

    public void update() {
        if (this.getX() != endPosition) {
            this.move(Configuration.SLIDER_MOVEMENT_SPEED * movementDirection, 0);
        }
    }

    public void moveRight() {
        sliderPosition++;
        endPosition = sliderPosition * Configuration.SLIDER_WIDTH;
        movementDirection = 1;
    }

    public void moveRight(SliderPositions sP) {
        sliderPosition = sP.ordinal();
        endPosition = sliderPosition * Configuration.SLIDER_WIDTH;
        movementDirection = 1;
    }

    public void moveLeft() {
        sliderPosition--;
        endPosition = sliderPosition * Configuration.SLIDER_WIDTH;
        movementDirection = -1;
    }

    public void moveLeft(SliderPositions sP) {
        sliderPosition = sP.ordinal();
        endPosition = sliderPosition * Configuration.SLIDER_WIDTH;
        movementDirection = -1;
    }
}
