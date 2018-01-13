import de.ur.mi.graphics.Color;
import de.ur.mi.graphicsapp.GraphicsApp;
import piano.Piano;
import processing.event.MouseEvent;
import recorder.PianoRecorder;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MainApp extends GraphicsApp {

    private static final int CANVAS_WIDTH = 1070;
    private static final int CANVAS_HEIGHT = 400;
    private static final int FRAME_RATE = 30;
    private static final int SMOOTH_LEVEL = 8;
    private static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
    private Piano piano;
    private PianoRecorder pianoRecorder;
    private long millis;

    public void setup() {
        initCanvas();
        initRecorder();
        initPiano();
    }

    private void initCanvas() {
        size(CANVAS_WIDTH, CANVAS_HEIGHT);
        frameRate(FRAME_RATE);
        smooth(SMOOTH_LEVEL);
    }

    private void initPiano() {
        piano = new Piano(pianoRecorder);
    }

    private void initRecorder() {
        pianoRecorder = new PianoRecorder();
    }

    public void draw() {
        background(BACKGROUND_COLOR);
        piano.draw();
    }

    @Override
    public void mousePressed(MouseEvent event) {
        int mouseX = event.getX();
        int mouseY = event.getY();
        millis = event.getMillis();
        piano.handleMouseInput(mouseX, mouseY);
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
                if (pianoRecorder.doRecording()) {
                    pianoRecorder.stopRecording();
                } else {
                    pianoRecorder.startRecording();
                }
                break;
            case (KeyEvent.VK_2):
                pianoRecorder.playRecording();
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
