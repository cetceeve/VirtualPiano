package piano;

import constants.Configuration;
import de.mi.ur.midi.Note;
import de.mi.ur.midi.Synthesizer;
import de.ur.mi.graphics.Compound;
import de.ur.mi.graphicsapp.GraphicsApp;

import java.util.ArrayList;
import java.util.Collections;

/*
This class can creates a piano of any number of octaves in two different data structures.
First as a compound (all keys extend from graphics object) ordered so that all keys can be drawn correctly.
Second as an ArrayList arranged for the notes to be in correct order.
 */
public class PianoBuilder {
    private Compound pianoCompound;
    private ArrayList<PianoKey> virtualPiano;
    private Synthesizer synthesizer;

    public PianoBuilder(Synthesizer synthesizer) {
        this.synthesizer = synthesizer;
    }

    public void newPiano(int numOfOctaves) {
        GraphicsApp.println("Creating New Piano");
        long timeStamp = System.currentTimeMillis();
        createPiano(numOfOctaves);
        GraphicsApp.println("Piano Creation Complete in [millis]: " + ((System.currentTimeMillis() - timeStamp)));
    }

    public ArrayList<PianoKey> getVirtualPiano() {
        return virtualPiano;
    }

    public Compound getPianoRepresentation() {
        return pianoCompound;
    }

    /////////////////////////////////////////////////
    private void createPiano(int numOfOctaves) {
        pianoCompound = new Compound();
        virtualPiano = new ArrayList<>();
        for (int i = 0; i < numOfOctaves; i++) {
            Collections.addAll(virtualPiano, nextOctave(10 + i * Configuration.OCTAVE_LENGTH));
        }
        setNotes(virtualPiano);
    }

    private PianoKey[] nextOctave(int startPosition) {
        PianoKey octave[] = new PianoKey[12];

        WhitePianoKey[] whiteKeys = createWhiteKeys(startPosition);
        sortWhiteKeysIntoOctaveArray(octave, whiteKeys);

        for (int i = 0; i < 11; i++) {
            if (octave[i] != null && i != 4) {
                octave[i + 1] = createBlackKey(octave[i]);
                i++;
            }
        }
        return octave;
    }

    private WhitePianoKey[] createWhiteKeys(int startPosition) {
        WhitePianoKey whitePianoKeys[] = new WhitePianoKey[7];
        for (int i = 0; i < whitePianoKeys.length; i++) {
            whitePianoKeys[i] = new WhitePianoKey(startPosition + i * Configuration.WHITE_PIANO_KEY_SIZE_X, Configuration.PIANO_POS_Y, synthesizer);
            pianoCompound.add(whitePianoKeys[i]);
        }
        return whitePianoKeys;
    }

    /*
    the parent key is always the white key to the left (e.g. C_CONTRA -> C_SHARP_CONTRA)
     */
    private BlackPianoKey createBlackKey(PianoKey whiteParentKey) {
        int posX = (int)whiteParentKey.getRightBorder() - Configuration.BLACK_PIANO_KEY_SIZE_X / 2;
        BlackPianoKey blackPianoKey = new BlackPianoKey(posX, Configuration.PIANO_POS_Y, synthesizer);
        pianoCompound.add(blackPianoKey);
        return blackPianoKey;
    }

    private void sortWhiteKeysIntoOctaveArray(PianoKey[] octave, WhitePianoKey[] whiteKeys) {
        octave[0] = whiteKeys[0];
        octave[2] = whiteKeys[1];
        octave[4] = whiteKeys[2];
        octave[5] = whiteKeys[3];
        octave[7] = whiteKeys[4];
        octave[9] = whiteKeys[5];
        octave[11] = whiteKeys[6];
    }

    /*
    keys sorting of keys in ArrayList must be equal to sorting in Note enumeration
     */
    private void setNotes(ArrayList<PianoKey> keys) {
        Note notes[] = Note.values();
        for (int i = 0; i < keys.size(); i++) {
            keys.get(i).setNote(notes[i]);
        }
    }
}
