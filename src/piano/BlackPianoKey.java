package piano;

import constants.Configuration;
import de.mi.ur.midi.Note;
import de.mi.ur.midi.Synthesizer;
import de.ur.mi.graphics.Color;

public class BlackPianoKey extends PianoKey {

    public BlackPianoKey(int x, int y, Synthesizer synthesizer) {
        super(x, y, Configuration.BLACK_PIANO_KEY_SIZE_X, Configuration.BLACK_PIANO_KEY_SIZE_Y, Color.BLACK, synthesizer);
    }
}
