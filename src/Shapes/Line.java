package Shapes;
import java.awt.*;
public class Line extends Shape {

    public Line(boolean dotted ,int x1, int y1, int x2, int y2, ShapeType shapeType, Color col) {
        super(dotted,false, x1, y1, x2, y2, shapeType, col);
    }

}
