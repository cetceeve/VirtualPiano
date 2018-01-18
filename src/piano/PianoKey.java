package piano;

import constants.Configuration;
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
    private boolean useColorFadeOut = false;

    public PianoKey(Note note, int x, int y, int width, int height, Color color, Synthesizer synthesizer) {
        super(x, y, width, height, color);
        this.note = note;
        this.keyColor = color;
        this.synthesizer = synthesizer;
        /*
        try {
            synthesizer = new Synthesizer();
            synthesizer.setInstrument(Instrument.PIANO);
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
        */
    }

    @Override
    public void draw() {
        if (useColorFadeOut) {
            colorFadeOut();
        }
        super.draw();
    }

    public void playNote(int velocity) {
        try {
            synthesizer.playNote(note, velocity);
        } catch (Synthesizer.NoteOutOfBoundsException e) {
            e.printStackTrace();
        }
        GraphicsApp.println("Playing: " + this.getNote() + " | " + velocity);
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Note getNote() {
        return note;
    }

    public void resetColor() {
        this.setColor(keyColor);
    }

    public void activateColorFadeOut() {
        useColorFadeOut = true;
    }

    private void colorFadeOut() {
        int colorValueRed = this.getColor().getRed();
        int colorValueGreen = this.getColor().getGreen();
        int colorValueBlue = this.getColor().getBlue();
        if (colorValueRed != keyColor.getRed()) {
            colorValueRed = colorValueUpdater(colorValueRed, keyColor.getRed());
        }
        if (colorValueGreen != keyColor.getGreen()) {
            colorValueGreen = colorValueUpdater(colorValueGreen, keyColor.getGreen());
        }
        if (colorValueBlue != keyColor.getBlue()) {
            colorValueBlue = colorValueUpdater(colorValueBlue, keyColor.getBlue());
        }
        this.setColor(colorValueRed, colorValueGreen, colorValueBlue);
        if (colorValueRed == keyColor.getRed() && colorValueGreen == keyColor.getGreen() && colorValueBlue == keyColor.getBlue()) {
            useColorFadeOut = false;
        }
    }

    private int colorValueUpdater(int colorValue, int targetColorValue) {
        if (colorValue > targetColorValue) {
            colorValue -= Configuration.KEY_COLOR_FADE_SPEED;
            if (colorValue < 0) {
                colorValue = 0;
            }
        } else {
            colorValue += Configuration.KEY_COLOR_FADE_SPEED;
            if (colorValue > 255) {
                colorValue = 255;
            }
        }
        return colorValue;
    }
}
