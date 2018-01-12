package piano;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Compound;
import de.ur.mi.graphics.Rect;

public class Piano {
    private Compound pianoRepresentation;

    public Piano() {
        PianoBuilder pianoBuilder = new PianoBuilder();
        pianoRepresentation = pianoBuilder.newPiano();
    }

    public void update() {

    }

    public void draw() {
        pianoRepresentation.draw();
    }

    public void handleMouseInput(int mouseX, int mouseY) {
        PianoKey key = (PianoKey) pianoRepresentation.getObjectAt(mouseX, mouseY);
        key.playNote(127);
    }
}
