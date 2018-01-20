package piano;

import constants.Configuration;
import de.mi.ur.midi.Synthesizer;
import de.ur.mi.graphics.Color;

public class WhitePianoKey extends PianoKey {

    public WhitePianoKey(int x, int y, Synthesizer synthesizer) {
        super(x, y, Configuration.WHITE_PIANO_KEY_SIZE_X, Configuration.WHITE_PIANO_KEY_SIZE_Y, Color.WHITE, synthesizer);
        this.setBorder(Color.BLACK, 2);
    }
}
