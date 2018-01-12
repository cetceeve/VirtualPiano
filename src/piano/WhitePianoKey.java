package piano;

import de.mi.ur.midi.Note;
import de.ur.mi.graphics.Color;

public class WhitePianoKey extends PianoKey {

    public WhitePianoKey(Note note, int x, int y) {
        super(note, x, y, Configuration.WHITE_PIANO_KEY_SIZE_X, Configuration.WHITE_PIANO_KEY_SIZE_Y, Color.WHITE);
        this.setBorder(Color.BLACK, 2);
    }
}
