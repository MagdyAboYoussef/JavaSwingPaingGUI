package Shapes;

import java.awt.*;
import java.util.List;

public class Pattern extends Shape {
    private List<Shape> shapeList;

    public Pattern(List<Shape> shapes, boolean dotted,boolean fill, int x1, int y1, int x2, int y2, ShapeType shape, Color col) {
        super(dotted,fill, x1, y1, x2, y2, shape, col);
        this.shapeList = shapes;

    }

    public List<Shape> getShapeList() {
        return shapeList;
    }
}

