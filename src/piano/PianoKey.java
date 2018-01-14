package piano;

import de.mi.ur.midi.Instrument;
import de.mi.ur.midi.Note;
import de.mi.ur.midi.Synthesizer;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Rect;
import de.ur.mi.graphicsapp.GraphicsApp;

import javax.sound.midi.MidiUnavailableException;

public class PianoKey extends Rect {
    private Synthesizer synthesizer;
    private Note note;
    private Color keyColor;

    public PianoKey(Note note, int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        this.note = note;
        this.keyColor = color;
        try {
            synthesizer = new Synthesizer();
        } catch (MidiUnavailableException e){
            e.printStackTrace();
        }
        synthesizer.setInstrument(Instrument.PIANO);
    }

    public void playNote(int velocity) {
        try {
            synthesizer.playNote(note, velocity);
        } catch (Synthesizer.NoteOutOfBoundsException e){
            e.printStackTrace();
        }
        GraphicsApp.println("Playing: " + this.getNote() + " | " + velocity);
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Note getNote() { return note; }

    public void resetColor() {
        this.setColor(keyColor);
    }
}
