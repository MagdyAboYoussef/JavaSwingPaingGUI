package Shapes;

import java.awt.*;

public class Rectangle extends Shape {
    private int width, height;

    public Rectangle(boolean dotted,boolean fill, int x1, int y1, int x2, int y2, ShapeType shapeType, Color col, int width, int height) {
        super(dotted,fill, x1, y1, x2, y2, shapeType, col);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
