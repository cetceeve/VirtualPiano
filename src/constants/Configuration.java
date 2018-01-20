package constants;

import de.ur.mi.graphics.Color;

public final class Configuration {

    private Configuration() {}

    /////////////////////////////////////////
    /*
    constants for piano builder
     */
    // piano
    public static final int PIANO_POS_Y = 70;
    public static final int NUM_OF_OCTAVES = 3;
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
    /*
    constants for piano
     */
    public static final int PIANO_STARTING_OCTAVE = 1;

    public static final Color KEY_HIGHLIGHT_COLOR = Color.BLUE;
    public static final Color KEY_PLAYBACK_COLOR = Color.RED;
    public static final int KEY_COLOR_FADE_SPEED = 7;

    public static final int VELOCITY_MIN = 30;
    public static final int VELOCITY_MAX = 127;
    /*
    constants for velocity calculation
     */
    // threshold and limit values
    public static final long VELOCITY_MOUSECLICKDURATION_THRESHOLD = 40;
    public static final long VELOCITY_MOUSECLICKDURATION_LIMIT = 804;
    /*
    any input value lower than (roughly) 40 will be 127 velocity [empirical value]
     */
    public static final int VELOCITY_OFFSET = 132;
    /*
    any input value higher than (roughly) 1000 will be 0 velocity [calculated value]
     */
    public static final double VELOCITY_GRADIENT = -0.127;
    /*
    constants for slider
     */
    public static final int SLIDER_START_OCTAVE = 1;
    public static final int SLIDER_POSITION_Y = PIANO_POS_Y + WHITE_PIANO_KEY_SIZE_Y + 5;
    public static final int SLIDER_WIDTH = OCTAVE_LENGTH;
    public static final int SLIDER_HEIGHT = 20;
    public static final Color SLIDER_COLOR = Color.BLUE;
    public static final double SLIDER_MOVEMENT_SPEED = SLIDER_WIDTH / 5;
    /////////////////////////////////////////
    /*
    constants for user interface
    all image originals from pngimages.net or iconfinder.com
     */
    public static final int BUTTON_STANDARD_SIZE = 50;
    // recording button
    public static final String RECORDING_BUTTON_IMAGE_START = "data/assets/recording_button.png";
    public static final String RECORDING_BUTTON_IMAGE_STOP = "data/assets/stop_recording_button.png";
    public static final int RECORDING_BUTTON_POSITION_X = OCTAVE_LENGTH * 3 - 3 * BUTTON_STANDARD_SIZE - 10;
    public static final int RECORDING_BUTTON_POSITION_Y = 10;
    // play button
    public static final String PLAYBACK_BUTTON_IMAGE_PLAY = "data/assets/playback_button_2.png";
    public static final String PLAYBACK_BUTTON_IMAGE_STOP = "data/assets/stop_playback_button.png";
    public static final int PLAYBACK_BUTTON_POSITION_X = OCTAVE_LENGTH * 3 - 2 * BUTTON_STANDARD_SIZE;
    public static final int PLAYBACK_BUTTON_POSITION_Y = 10;
    // delete button
    public static final String DELETE_BUTTON_IMAGE = "data/assets/trash_can.png";
    public static final int DELETE_BUTTON_POSITION_X = 10 + OCTAVE_LENGTH * 3 - BUTTON_STANDARD_SIZE;
    public static final int DELETE_BUTTON_POSITION_Y = 10;
    // controls button
    public static final String CONTROLS_BUTTON_IMAGE = "data/assets/controls_button.png";
    public static final String CONTROLS_OVERLAY_IMAGE = "data/assets/controls_overlay.png";
    public static final int CONTROLS_OVERLAY_POSITION_X = 10;
    public static final int CONTROLS_OVERLAY_POSITION_Y = 15;

    /////////////////////////////////////////
    /*
    constants for GraphicsApp
     */
    public static final int CANVAS_WIDTH = 3 * OCTAVE_LENGTH + 20;
    public static final int CANVAS_HEIGHT = SLIDER_POSITION_Y + SLIDER_HEIGHT + 10;
    public static final int FRAME_RATE = 30;
    public static final int SMOOTH_LEVEL = 8;
    public static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
}
