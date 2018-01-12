package piano;

public final class Configuration {

    private Configuration() {}

    //piano
    public static final int NUM_OF_OCTAVES = 3;
    // white key
    public static final int WHITE_PIANO_KEY_SIZE_X = 40;
    public static final int WHITE_PIANO_KEY_SIZE_Y = 110;
    // black key
    public static final int BLACK_PIANO_KEY_SIZE_X = 25;
    public static final int BLACK_PIANO_KEY_SIZE_Y = 60;
    // octave
    public static final int OCTAVE_LENGTH = 7 * WHITE_PIANO_KEY_SIZE_X;
    public enum Octave {
        CONTRA,
        GREAT,
        SMALL
    }
}
