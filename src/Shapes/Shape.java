package Shapes;

import java.awt.*;

abstract public class Shape {
    private  int x1, y1, x2, y2;
    private ShapeType shape;

    private Color col;
    boolean fill, dotted;

    public Shape(boolean dotted, boolean fill, int x1, int y1, int x2, int y2, ShapeType shape, Color col) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.shape = shape;
        this.col = col;
        this.fill = fill;
        this.dotted = dotted;
    }

    public ShapeType getShape() {
        return shape;
    }



    public int getX1() {

        return x1;
    }

    public int getY1() {

        return y1;
    }

    public int getX2() {

        return x2;
    }

    public int getY2() {

        return y2;
    }

    public boolean isFill() {
        return fill;
    }
    public boolean isDotted() {
        return dotted;
    }

    public Color getCol() {

        return col;
    }


}
