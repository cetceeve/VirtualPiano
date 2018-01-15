package constants;

public final class Configuration {

    private Configuration() {}

    //piano
    public static final int NUM_OF_OCTAVES = 3;
    public static final int STARTING_OCTAVE = 1;
    // white key
    public static final int WHITE_PIANO_KEY_SIZE_X = 40;
    public static final int WHITE_PIANO_KEY_SIZE_Y = 110;
    // black key
    public static final int BLACK_PIANO_KEY_SIZE_X = 25;
    public static final int BLACK_PIANO_KEY_SIZE_Y = 60;
    // octave
    public static final int OCTAVE_LENGTH = 7 * WHITE_PIANO_KEY_SIZE_X;
    public static final int OCTAVE_NUM_OF_KEYS = 12;

    /////////////////////////////////////////
    // velocity calculation
    public static final int VELOCITY_MIN = 30;
    public static final int VELOCITY_MAX = 127;
    // threshold for min/max values
    public static final long VELOCITY_MOUSECLICKDURATION_THRESHOLD_LOW = 40;
    public static final long VELOCITY_MOUSECLICKDURATION_THRESHOLD_HIGH = 804;
    /*
    any input value lower than (roughly) 40 will be 127 velocity [empirical value]
     */
    public static final int VELOCITY_OFFSET = 132;
    /*
    any input value higher than (roughly) 1000 will be 0 velocity [calculated value]
     */
    public static final double VELOCITY_GRADIENT = -0.127;

    /////////////////////////////////////////
    public static final String IMAGE_SOURCE_PLAY_BUTTON = "";
    public static final String IMAGE_SOURCE_PAUSE_BUTTON = "";

    public static final String IMAGE_SOURCE_RECORDING_ON_BUTTON = "";
    public static final String IMAGE_SOURCE_RECORDING_OFF_BUTTON = "";

    public static final String IMAGE_SOURCE_DELETE_BUTTON = "";
}
