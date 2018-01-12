package piano;

import de.mi.ur.midi.Instrument;
import de.mi.ur.midi.Note;
import de.mi.ur.midi.Synthesizer;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Rect;

import javax.sound.midi.MidiUnavailableException;

public class PianoKey extends Rect {
    private Synthesizer synthesizer;
    private Note note;
    private int octave = Configuration.OCTAVE_CONTRA;

    public PianoKey(Note note, int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        this.note = note;
        try {
            synthesizer = new Synthesizer();
        } catch (MidiUnavailableException e){
            e.printStackTrace();
        }
        synthesizer.setInstrument(Instrument.PIANO);
    }

    public void playNote(int velocity) {
        try {
            synthesizer.playNote(note, velocity, octave);
        } catch (Synthesizer.NoteOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    public void setNote(Note note) {
        this.note = note;
        octaveResolver();
    }

    private void octaveResolver() {
        int noteAsInt = note.ordinal();
        if (noteAsInt < 12) {
            octave = Configuration.OCTAVE_CONTRA;
        } else if (noteAsInt < 24) {
            octave = Configuration.OCTAVE_GREAT;
        } else {
            octave = Configuration.OCTAVE_SMALL;
        }
    }
}
