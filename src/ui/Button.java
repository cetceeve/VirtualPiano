package ui;

import de.ur.mi.graphics.Image;
import interfaces.Clickable;
import interfaces.Drawable;

/*
This is a class for buttons represented by an image.
 */
public class Button implements Clickable, Drawable {
    private Image representation;
    private String src;

    public Button(int posX, int posY, int size, String src) {
        representation = new Image(posX, posY, size, size, src);
        this.src = src;
    }

    @Override
    public void draw() {
        representation.draw();
    }

    public void changeRepresentation(String src) {
        representation = new Image(representation.getX(), representation.getY(), (int) representation.getWidth(), (int) representation.getHeight(), src);
        this.src = src;
    }

    public String getRepresentation() {
        return src;
    }

    @Override
    public boolean hitTest(double x, double y) {
        return representation.hitTest(x, y);
    }
}
