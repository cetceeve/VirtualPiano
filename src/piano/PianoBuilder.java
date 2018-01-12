package piano;

import de.mi.ur.midi.Note;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Compound;
import de.ur.mi.graphics.Rect;

public class PianoBuilder {

    public PianoBuilder() {}

    public Compound newPiano() {
        WhitePianoKey keys[] = new WhitePianoKey[7];
        Compound compound = new Compound();
        for (int i = 0; i < keys.length; i++) {
            keys[i] = new WhitePianoKey(Note.C_CONTRA, 10 + i * Configuration.WHITE_PIANO_KEY_SIZE_X, 10);
        }
        keys[1].setNote(Note.D_CONTRA);
        keys[2].setNote(Note.E_CONTRA);
        keys[3].setNote(Note.F_CONTRA);
        keys[4].setNote(Note.G_CONTRA);
        keys[5].setNote(Note.A_CONTRA);
        keys[6].setNote(Note.H_CONTRA);
        for (PianoKey key: keys) {
            compound.add(key);
        }
        return compound;
    }
}
