package piano;

import de.mi.ur.midi.Note;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Compound;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Piano {
    private Compound pianoRepresentation;
    private ArrayList<PianoKey> virtualPiano;
    private ArrayList<Integer> pressedKeys;
    private int currentOctave = 1;
    private PianoKey currentKey;

    public Piano() {
        PianoBuilder pianoBuilder = new PianoBuilder();
        pianoRepresentation = pianoBuilder.newPiano();
        virtualPiano = pianoBuilder.getVirtualPiano();
        pressedKeys = new ArrayList<>();
    }

    public void draw() {
        pianoRepresentation.draw();
    }

    public void handleMouseInput(int mouseX, int mouseY) {
        currentKey = (PianoKey) pianoRepresentation.getObjectAt(mouseX, mouseY);
        if (currentKey != null) {
            currentKey.setColor(Color.GREEN);
        }
    }

    public void handleMouseRelease(long mouseClickDuration) {
        if (currentKey != null) {
            currentKey.playNote(velocityCalculation(mouseClickDuration));
            currentKey.resetColor();
        }
    }

    private int velocityCalculation(long clickDuration) {
        if (clickDuration < Configuration.VELOCITY_MOUSECLICKDURATION_THRESHOLD_LOW) {
            return Configuration.VELOCITY_MAX;
        } else if (clickDuration > Configuration.VELOCITY_MOUSECLICKDURATION_THRESHOLD_HIGH) {
            return Configuration.VELOCITY_MIN;
        } else {
            return (int) (Configuration.VELOCITY_GRADIENT * clickDuration + Configuration.VELOCITY_OFFSET);
        }
    }

    public void handleKeyInput(KeyEvent event) {
        switch (event.getKeyCode()) {
            case (KeyEvent.VK_SHIFT):
                increaseOctave();
                break;
            case (KeyEvent.VK_CONTROL):
                decreaseOctave();
                break;
            default:
                Integer key = keyTranslator(event);
                if (key != null && !keyInUse(key)) {
                    virtualPiano.get(key).playNote(Configuration.VELOCITY_MAX);
                    virtualPiano.get(key).setColor(Color.GREEN);
                    pressedKeys.add(key);
                }
        }
    }

    public void handleKeyRelease(KeyEvent event) {
        Integer key = keyTranslator(event);
        if (key != null) {
            virtualPiano.get(key).resetColor();
            pressedKeys.remove(key);
        }
    }

    private void increaseOctave() {
        if (currentOctave + 1 == Configuration.NUM_OF_OCTAVES) {
            currentOctave = 0;
        } else {
            currentOctave++;
        }
    }

    private void decreaseOctave() {
        if (currentOctave == 0) {
            currentOctave = Configuration.NUM_OF_OCTAVES - 1;
        } else {
            currentOctave--;
        }
    }

    private boolean keyInUse(int note) {
        if (!pressedKeys.isEmpty()) {
            for (Integer i : pressedKeys) {
                if (i == note) {
                    return true;
                }
            }
        }
        return false;
    }

    private Integer keyTranslator(KeyEvent event) {
        Note baseNote;
        switch (event.getExtendedKeyCode()) {
            case (KeyEvent.VK_S):
                baseNote = Note.C_CONTRA;
                break;
            case (KeyEvent.VK_E):
                baseNote = Note.C_SHARP_CONTRA;
                break;
            case (KeyEvent.VK_D):
                baseNote = Note.D_CONTRA;
                break;
            case (KeyEvent.VK_R):
                baseNote = Note.D_SHARP_CONTRA;
                break;
            case (KeyEvent.VK_F):
                baseNote = Note.E_CONTRA;
                break;
            case (KeyEvent.VK_J):
                baseNote = Note.F_CONTRA;
                break;
            case (KeyEvent.VK_I):
                baseNote = Note.F_SHARP_CONTRA;
                break;
            case (KeyEvent.VK_K):
                baseNote = Note.G_CONTRA;
                break;
            case (KeyEvent.VK_O):
                baseNote = Note.G_SHARP_CONTRA;
                break;
            case (KeyEvent.VK_L):
                baseNote = Note.A_CONTRA;
                break;
            case (KeyEvent.VK_P):
                baseNote = Note.A_SHARP_CONTRA;
                break;
            case (0x010000D6):
                baseNote = Note.H_CONTRA;
                break;
            default:
                return null;
        }
        return baseNote.ordinal() + currentOctave * Configuration.OCTAVE_NUM_OF_KEYS;
    }
}
