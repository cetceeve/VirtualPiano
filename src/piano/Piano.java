package piano;

import constants.Configuration;
import de.mi.ur.midi.Instrument;
import de.mi.ur.midi.Note;
import de.mi.ur.midi.Synthesizer;
import de.ur.mi.graphics.Compound;
import de.ur.mi.graphicsapp.GraphicsApp;
import recorder.Recorder;
import ui.Drawable;

import javax.sound.midi.MidiUnavailableException;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/*
This class holds all piano Systems and the synthesizer.
It also oversees all inputs for the Piano.
The velocity calculation is also done here.
 */
public class Piano implements Drawable{
    private Compound pianoRepresentation;
    private Slider slider;
    private ArrayList<PianoKey> virtualPiano;
    private ArrayList<Integer> pressedKeys;
    private int currentOctave = Configuration.STARTING_OCTAVE;
    private PianoKey currentKey;
    private Synthesizer synthesizer;

    private Recorder recorder;
    private long timeStamp = System.currentTimeMillis();

    public Piano(Recorder recorder) {
        slider = new Slider();
        pressedKeys = new ArrayList<>();
        GraphicsApp.println("Init Piano");
        this.recorder = recorder;
        initSynthesizer();
        initPianoSystems();
    }

    @Override
    public void draw() {
        pianoRepresentation.draw();
        slider.update();
        slider.draw();
    }

    /////////////////////////////////////////////////
    public void handleMouseInput(int mouseX, int mouseY) {
        currentKey = (PianoKey) pianoRepresentation.getObjectAt(mouseX, mouseY);
        if (currentKey != null) {
            currentKey.setColor(Configuration.HIGHLIGHT_COLOR);
        }
    }

    /*
    play note and save it if recording is on
    timeStamp holds the time since the last note was played
     */
    public void handleMouseRelease(long mouseClickDuration) {
        if (currentKey != null) {
            int velocity = velocityCalculation(mouseClickDuration);
            currentKey.playNote(velocity);
            currentKey.resetColor();
            if (recorder.doRecording()) {
                recorder.saveDataPoint(currentKey, velocity, System.currentTimeMillis() - timeStamp);
            }
            timeStamp = System.currentTimeMillis();
        }
    }

    /*
    simple linear sloping strait with top and bottom limits
     */
    private int velocityCalculation(long clickDuration) {
        if (clickDuration < Configuration.VELOCITY_MOUSECLICKDURATION_THRESHOLD) {
            return Configuration.VELOCITY_MAX;
        } else if (clickDuration > Configuration.VELOCITY_MOUSECLICKDURATION_LIMIT) {
            return Configuration.VELOCITY_MIN;
        } else {
            return (int) (Configuration.VELOCITY_GRADIENT * clickDuration + Configuration.VELOCITY_OFFSET);
        }
    }

    /////////////////////////////////////////////////
    /*
    note is played on input not on release, therefore no velocity calculations
    {This seemed more natural to me]

    play note and save it if recording is on
    to avoid playing the notes continually when user holds keys (sounds horrific) the pressed Keys are tracked
     */
    public void handleKeyInput(KeyEvent event) {
        switch (event.getKeyCode()) {
            case (KeyEvent.VK_SHIFT):
                increaseOctave();
                break;
            case (KeyEvent.VK_CONTROL):
                decreaseOctave();
                break;
            default:
                Integer keyIndex = keyIndexTranslator(event);
                if (keyIndex != null && !keyInUse(keyIndex)) {
                    virtualPiano.get(keyIndex).playNote(Configuration.VELOCITY_MAX);
                    virtualPiano.get(keyIndex).setColor(Configuration.HIGHLIGHT_COLOR);
                    pressedKeys.add(keyIndex);
                    if (recorder.doRecording()) {
                        recorder.saveDataPoint(virtualPiano.get(keyIndex), Configuration.VELOCITY_MAX, System.currentTimeMillis() - timeStamp);
                    }
                    timeStamp= System.currentTimeMillis();
                }
        }
    }

    public void handleKeyRelease(KeyEvent event) {
        Integer key = keyIndexTranslator(event);
        if (key != null) {
            virtualPiano.get(key).resetColor();
            pressedKeys.remove(key);
        }
    }

    /*
    checks if a key is currently pressed
     */
    private boolean keyInUse(int keyIndex) {
        if (!pressedKeys.isEmpty()) {
            for (Integer i : pressedKeys) {
                if (i == keyIndex) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    circle threw all octaves upwards
    (currentOctave value is used in keyIndexTranslator)
     */
    private void increaseOctave() {
        if (currentOctave + 1 == Configuration.NUM_OF_OCTAVES) {
            GraphicsApp.println("Octave Decreased");
            currentOctave = 0;
            slider.moveLeft(0);
        } else {
            GraphicsApp.println("Octave Increased");
            currentOctave++;
            slider.moveRight();
        }
    }

    /*
    circle threw all octaves downwards
    (currentOctave value is used in keyIndexTranslator)
     */
    private void decreaseOctave() {
        if (currentOctave == 0) {
            GraphicsApp.println("Octave Increased");
            currentOctave = Configuration.NUM_OF_OCTAVES - 1;
            slider.moveRight(Configuration.NUM_OF_OCTAVES - 1);
        } else {
            GraphicsApp.println("Octave Decreased");
            currentOctave--;
            slider.moveLeft();
        }
    }

    /*
    translates the key input into the correct index value for the virtualPiano ArrayList
     */
    private Integer keyIndexTranslator(KeyEvent event) {
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
            case (0x010000D6): // 'Ö'
                baseNote = Note.H_CONTRA;
                break;
            default:
                return null;
        }
        return baseNote.ordinal() + currentOctave * Configuration.OCTAVE_NUM_OF_KEYS;
    }

    /////////////////////////////////////////////////
    private void initSynthesizer() {
        try {
            synthesizer = new Synthesizer();
            synthesizer.setInstrument(Instrument.PIANO);
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void initPianoSystems() {
        PianoBuilder pianoBuilder = new PianoBuilder(synthesizer);
        pianoBuilder.newPiano(Configuration.NUM_OF_OCTAVES);
        pianoRepresentation = pianoBuilder.getPianoRepresentation();
        virtualPiano = pianoBuilder.getVirtualPiano();
    }
}
