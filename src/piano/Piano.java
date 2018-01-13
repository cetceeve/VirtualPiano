package piano;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Compound;

public class Piano {
    private Compound pianoRepresentation;
    private PianoKey currentKey;

    public Piano() {
        PianoBuilder pianoBuilder = new PianoBuilder();
        pianoRepresentation = pianoBuilder.newPiano();
    }

    public void draw() {
        pianoRepresentation.draw();
    }

    public void handleMouseInput(int mouseX, int mouseY) {
        currentKey = (PianoKey) pianoRepresentation.getObjectAt(mouseX, mouseY);
        if (currentKey != null) {
            currentKey.setColor(Color.GREEN);
        }
    }

    public void handleMouseRelease(long mouseClickDuration) {
        if (currentKey != null) {
            currentKey.playNote(velocityCalculation(mouseClickDuration));
            currentKey.resetColor();
        }
    }

    private int velocityCalculation(long mouseClickDuration) {
        if (mouseClickDuration < Configuration.VELOCITY_MOUSECLICKDURATION_THRESHOLD_LOW) {
            return Configuration.VELOCITY_MAX;
        } else if (mouseClickDuration > Configuration.VELOCITY_MOUSECLICKDURATION_THRESHOLD_HIGH) {
            return Configuration.VELOCITY_MIN;
        } else {
           return (int) (Configuration.VELOCITY_GRADIENT * mouseClickDuration + Configuration.VELOCITY_OFFSET);
        }
    }
}
