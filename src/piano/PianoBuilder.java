package piano;

import constants.Configuration;
import de.mi.ur.midi.Note;
import de.mi.ur.midi.Synthesizer;
import de.ur.mi.graphics.Compound;
import de.ur.mi.graphicsapp.GraphicsApp;

import java.util.ArrayList;
import java.util.Collections;

public class PianoBuilder {
    private Compound compound;
    private ArrayList<PianoKey> virtualPiano;
    private Synthesizer synthesizer;

    public PianoBuilder(Synthesizer synthesizer) {
        this.synthesizer = synthesizer;
    }

    public void newPiano() {
        GraphicsApp.println("Creating New Piano");
        long timeStamp = System.currentTimeMillis();
        compound = new Compound();
        virtualPiano = new ArrayList<>();
        for (int i = 0; i < Configuration.NUM_OF_OCTAVES; i++) {
            Collections.addAll(virtualPiano, nextOctave(10 + i * Configuration.OCTAVE_LENGTH));
        }
        setNotes(virtualPiano);
        GraphicsApp.println("Piano Creation Complete in [s]: " + ((System.currentTimeMillis() - timeStamp) / 1000));
    }

    public ArrayList<PianoKey> getVirtualPiano() {
        return virtualPiano;
    }

    public Compound getPianoRepresentation() {
        return compound;
    }

    private void setNotes(ArrayList<PianoKey> keys) {
        Note noteValues[] = Note.values();
        for (int i = 0; i < keys.size(); i++) {
            keys.get(i).setNote(noteValues[i]);
        }
    }

    private PianoKey[] nextOctave(int startPosition) {
        PianoKey octave[] = new PianoKey[12];
        sortWhiteKeysIntoOctaveArray(octave, createWhiteKeys(startPosition));
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
            whitePianoKeys[i] = new WhitePianoKey(Note.C_CONTRA, startPosition + i * Configuration.WHITE_PIANO_KEY_SIZE_X, Configuration.PIANO_POS_Y, synthesizer);
            compound.add(whitePianoKeys[i]);
        }
        return whitePianoKeys;
    }

    private BlackPianoKey createBlackKey(PianoKey whiteParentKey) {
        int posX = (int)whiteParentKey.getRightBorder() - Configuration.BLACK_PIANO_KEY_SIZE_X / 2;
        BlackPianoKey blackPianoKey = new BlackPianoKey(Note.C_SHARP_CONTRA, posX, Configuration.PIANO_POS_Y, synthesizer);
        compound.add(blackPianoKey);
        return blackPianoKey;
    }

    private void sortWhiteKeysIntoOctaveArray(PianoKey[] keys, WhitePianoKey[] whiteKeys) {
        keys[0] = whiteKeys[0];
        keys[2] = whiteKeys[1];
        keys[4] = whiteKeys[2];
        keys[5] = whiteKeys[3];
        keys[7] = whiteKeys[4];
        keys[9] = whiteKeys[5];
        keys[11] = whiteKeys[6];
    }
}
