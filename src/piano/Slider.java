package piano;

import constants.Configuration;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Rect;
import interfaces.Drawable;

/*
This is a visual elements to show the user which octave it can currently play with the keyboard.
 */
public class Slider extends Rect implements Drawable{

    private int targetOctave = Configuration.SLIDER_START_OCTAVE;
    private int targetPosValue;
    private int movementDirection = 0;

    public Slider() {
        super(10 + (Configuration.SLIDER_WIDTH * Configuration.SLIDER_START_OCTAVE), Configuration.SLIDER_POSITION_Y, Configuration.SLIDER_WIDTH, Configuration.SLIDER_HEIGHT, Configuration.SLIDER_COLOR);
        this.setBorder(Color.BLACK, 1);
    }

    /*
    slider is animated to 'slide' to its target octave
     */
    public void update() {
        if (this.getX() != targetPosValue) {
            this.move(Configuration.SLIDER_MOVEMENT_SPEED * movementDirection, 0);
        }
        if (this.getX() == targetPosValue)
            movementDirection = 0;

    }

    /*
    (!)overloaded method
     */
    public void moveRight() {
        targetOctave++;
        targetPosValue = 10 + targetOctave * Configuration.SLIDER_WIDTH;
        movementDirection = 1;
    }

    public void moveLeft() {
        targetOctave--;
        targetPosValue = 10 + targetOctave * Configuration.SLIDER_WIDTH;
        movementDirection = -1;
    }

    public void moveRight(int targetOctave) {
        this.targetOctave = targetOctave;
        targetPosValue = 10 + targetOctave * Configuration.SLIDER_WIDTH;
        movementDirection = 1;
    }


    public void moveLeft(int targetOctave) {
        this.targetOctave = targetOctave;
        targetPosValue = 10 + targetOctave * Configuration.SLIDER_WIDTH;
        movementDirection = -1;
    }
}
