import constants.Configuration;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphicsapp.GraphicsApp;
import piano.Piano;
import processing.event.MouseEvent;
import recorder.PianoRecorder;
import ui.UserInterface;

import java.awt.event.KeyEvent;

public class MainApp extends GraphicsApp {

    private static final int FRAME_RATE = 30;
    private static final int SMOOTH_LEVEL = 8;
    private static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
    private Piano piano;
    private PianoRecorder pianoRecorder;
    private UserInterface ui;
    private long millis;

    public void setup() {
        initCanvas();
        initRecorder();
        initPiano();
        initUi();
    }

    private void initCanvas() {
        size(Configuration.CANVAS_WIDTH, Configuration.CANVAS_HEIGHT);
        frameRate(FRAME_RATE);
        smooth(SMOOTH_LEVEL);
    }

    private void initRecorder() {
        pianoRecorder = new PianoRecorder();
    }

    private void initPiano() {
        piano = new Piano(pianoRecorder);
    }

    private void initUi() {
        ui = new UserInterface(pianoRecorder);
    }

    public void draw() {
        background(BACKGROUND_COLOR);
        piano.draw();
        ui.draw();
    }

    @Override
    public void mousePressed(MouseEvent event) {
        int mouseX = event.getX();
        int mouseY = event.getY();
        millis = event.getMillis();
        piano.handleMouseInput(mouseX, mouseY);
        ui.handleMouseClick(mouseX, mouseY);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        millis -= event.getMillis();
        piano.handleMouseRelease(-1 * millis);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case (KeyEvent.VK_1):
                pianoRecorder.toggleRecording();
                ui.toggleRecordingButton();
                break;
            case (KeyEvent.VK_2):
                pianoRecorder.togglePlayback();
                ui.togglePlaybackButton();
                break;
            case (KeyEvent.VK_3):
                pianoRecorder.deleteRecording();
                break;
            default:
                piano.handleKeyInput(event);
        }

    }

    @Override
    public void keyReleased(KeyEvent event) {
        piano.handleKeyRelease(event);
    }
}
