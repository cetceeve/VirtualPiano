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
    private boolean uiHandledMouseClick;

    public void setup() {
        initCanvas();
        initUi();
        initRecorder();
        initPiano();
    }

    private void initCanvas() {
        size(Configuration.CANVAS_WIDTH, Configuration.CANVAS_HEIGHT);
        frameRate(FRAME_RATE);
        smooth(SMOOTH_LEVEL);
    }

    private void initRecorder() {
        pianoRecorder = new PianoRecorder(ui);
        ui.setRecorderInterfaceListener(pianoRecorder);
    }

    private void initPiano() {
        piano = new Piano(pianoRecorder);
    }

    private void initUi() {
        ui = new UserInterface();
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
        uiHandledMouseClick = ui.handleMouseClick(mouseX, mouseY);
        if (!uiHandledMouseClick) {
            piano.handleMouseInput(mouseX, mouseY);
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        millis -= event.getMillis();
        if (!uiHandledMouseClick) {
            piano.handleMouseRelease(-1 * millis);
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case (KeyEvent.VK_1):
                pianoRecorder.toggleRecording();
                break;
            case (KeyEvent.VK_2):
                pianoRecorder.togglePlayback();
                break;
            case (KeyEvent.VK_3):
                pianoRecorder.deleteRecording();
                break;
            case (KeyEvent.VK_ESCAPE):
                ui.toggleControlsOverlay();
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
