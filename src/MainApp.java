import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Compound;
import de.ur.mi.graphics.Rect;
import de.ur.mi.graphicsapp.GraphicsApp;
import piano.Piano;
import processing.event.MouseEvent;

public class MainApp extends GraphicsApp {

    private static final int CANVAS_WIDTH = 1070;
    private static final int CANVAS_HEIGHT = 400;
    private static final int FRAME_RATE = 30;
    private static final int SMOOTH_LEVEL = 8;
    private static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
    private Piano piano;

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
        piano.handleMouseInput(mouseX, mouseY);
        println("Clicked at: " + mouseX + "/" + mouseY);
    }



}
