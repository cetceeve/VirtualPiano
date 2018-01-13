import com.sun.istack.internal.NotNull;
import de.mi.ur.midi.Note;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphicsapp.GraphicsApp;
import piano.Piano;
import processing.event.MouseEvent;

import java.awt.event.KeyEvent;

public class MainApp extends GraphicsApp {

    private static final int CANVAS_WIDTH = 1070;
    private static final int CANVAS_HEIGHT = 400;
    private static final int FRAME_RATE = 30;
    private static final int SMOOTH_LEVEL = 8;
    private static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
    private Piano piano;
    private long millisMouse;

    public void setup() {
        initCanvas();
        initPiano();
    }

    private void initCanvas() {
        size(CANVAS_WIDTH, CANVAS_HEIGHT);
        frameRate(FRAME_RATE);
        smooth(SMOOTH_LEVEL);
    }

    private void initPiano() {
        piano = new Piano();
    }

    public void draw() {
        background(BACKGROUND_COLOR);
        piano.draw();
    }

    @Override
    public void mousePressed(MouseEvent event) {
        int mouseX = event.getX();
        int mouseY = event.getY();
        millisMouse = event.getMillis();
        piano.handleMouseInput(mouseX, mouseY);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        millisMouse -= event.getMillis();
        println(-1 * millisMouse);
        piano.handleMouseRelease(-1 * millisMouse);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        piano.handleKeyInput(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        piano.handleKeyRelease(event);
    }
}
