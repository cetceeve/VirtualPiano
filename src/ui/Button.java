package ui;

import de.ur.mi.graphics.Image;

public class Button implements Clickable, Drawable {
    private Image representation;

    public Button(int posX, int posY, int size, String src) {
        representation = new Image(posX, posY, size, size, src);
    }

    public void draw() {
        representation.draw();
    }

    public void changeRepresentation(String src) {
        representation = new Image(representation.getX(), representation.getY(), (int) representation.getWidth(), (int) representation.getHeight(), src);
    }

    //TODO: this might not work!
    public String getRepresentation() {
        return representation.toString();
    }

    @Override
    public boolean hitTest(double x, double y) {
        return representation.hitTest(x, y);
    }
}
