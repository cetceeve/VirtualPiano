package constants;

import de.ur.mi.graphics.Color;

public final class Configuration {

    private Configuration() {}

    //piano
    public static final int NUM_OF_OCTAVES = 3;
    public static final int STARTING_OCTAVE = 1;
    public static final Color HIGHLIGHT_COLOR = Color.BLUE;
    public static final int PIANO_POS_Y = 70;
    public static final int KEY_COLOR_FADE_SPEED = 7;
    public static final Color KEY_PLAYBACK_COLOR = Color.RED;
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
    public static final int BUTTON_STANDARD_SIZE = 50;

    public static final String IMAGE_SOURCE_RECORDING_ON_BUTTON = "data/assets/recording_button.png";
    public static final String IMAGE_SOURCE_RECORDING_OFF_BUTTON = "data/assets/stop_recording_button.png";
    public static final int RECORDING_BUTTON_POSITION_X = OCTAVE_LENGTH * 3 - 3 * BUTTON_STANDARD_SIZE - 10;
    public static final int RECORDING_BUTTON_POSITION_Y = 10;

    public static final String IMAGE_SOURCE_PLAY_BUTTON = "data/assets/playback_button_2.png";
    //http://pngimages.net/sites/default/files/stop-png-image-99225.png
    public static final String IMAGE_SOURCE_STOP_BUTTON = "data/assets/stop_playback_button.png";
    public static final int PLAY_BUTTON_POSITION_X = OCTAVE_LENGTH * 3 - 2 * BUTTON_STANDARD_SIZE;
    public static final int PLAY_BUTTON_POSITION_Y = 10;

    public static final String IMAGE_SOURCE_DELETE_BUTTON = "data/assets/trash_can.png";
    public static final int DELETE_BUTTON_POSITION_X = 10 + OCTAVE_LENGTH * 3 - BUTTON_STANDARD_SIZE;
    public static final int DELETE_BUTTON_POSITION_Y = 10;

    public static final int SLIDER_START_POSITION = 1;
    public static final int SLIDER_POSITION_Y = PIANO_POS_Y + WHITE_PIANO_KEY_SIZE_Y + 5;
    public static final int SLIDER_WIDTH = OCTAVE_LENGTH;
    public static final int SLIDER_HEIGHT = 20;
    public static final Color SLIDER_COLOR = Color.BLUE;
    public static final double SLIDER_MOVEMENT_SPEED = SLIDER_WIDTH / 5;

    /////////////////////////////////////////
    public static final int CANVAS_WIDTH = 3 * OCTAVE_LENGTH + 20;
    public static final int CANVAS_HEIGHT = SLIDER_POSITION_Y + SLIDER_HEIGHT + 10;
}
