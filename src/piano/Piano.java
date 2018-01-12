package piano;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Compound;
import de.ur.mi.graphics.Rect;

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
            //currentKey.playNote(127);
            currentKey.setColor(Color.GREEN);
        }
    }

    public void handleMouseRelease() {
        if (currentKey != null) {
            currentKey.playNote(127);
            currentKey.resetColor();
        }
    }
}
