package piano;

import de.mi.ur.midi.Note;
import de.ur.mi.graphics.Color;

public class BlackPianoKey extends PianoKey {

    public BlackPianoKey(Note note, int x, int y) {
        super(note, x, y, Configuration.BLACK_PIANO_KEY_SIZE_X, Configuration.BLACK_PIANO_KEY_SIZE_Y, Color.BLACK);
    }
}
