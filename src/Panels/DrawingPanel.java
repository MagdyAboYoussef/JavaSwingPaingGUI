package Panels;

import Shapes.Rectangle;
import Shapes.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class DrawingPanel extends DrawingService {


    public DrawingPanel() {


        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setX1(e.getX());
                setY1(e.getY());
                setX2(e.getX());
                setY2(e.getY());
                if (getShape() == ShapeType.ERASER) {
                    Oval tmp = new Oval(false, false, e.getX(), e.getY(), e.getX(), e.getY(), getShape(), Color.white, 30, 30);
                    resetEraserList();
                    getEraser().add(tmp);
                    repaint();

                } else if (getShape() == ShapeType.FREEHAND) {
                    Line tmp = new Line(isDotted(), e.getX(), e.getY(), e.getX(), e.getY(), getShape(), getColor());
                    resetLineList();
                    getLinesToAdd().add(tmp);
                }
                repaint();

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setX2(e.getX());
                setY2(e.getY());
                if (getShape() == ShapeType.LINE) {
                    Line tmp = new Line(isDotted(), getX1(), getY1(), getX2(), getY2(), getShape(), getColor());
                    getShapeArray().add(tmp);
                } else if (getShape() == ShapeType.OVAL) {
                    Oval tmp = new Oval(isDotted(), isFill(), getStartX(), getStartY(), getX2(), getY2(), getShape(), getColor(), calculateWidth(), calculateHeight());
                    getShapeArray().add(tmp);
                } else if (getShape() == ShapeType.RECTANGLE) {

                    Rectangle tmp = new Rectangle(isDotted(), isFill(), getStartX(), getStartY(), getX2(), getY2(), getShape(), getColor(), calculateWidth(), calculateHeight());
                    getShapeArray().add(tmp);


                } else if (getShape() == ShapeType.FREEHAND) {
                    Pattern pattern = new Pattern(getLinesToAdd(), isDotted(), isFill(), getX1(), getY1(), getX2(), getY2(), getShape(), getColor());
                    getShapeArray().add(pattern);

                } else if (getShape() == ShapeType.ERASER) {
                    Pattern pattern = new Pattern(getEraser(), true, true, getX1(), getY1(), getX2(), getY2(), getShape(), Color.WHITE);
                    getShapeArray().add(pattern);

                }

                if (isShouldClear()) {
                    getPoppedList().clear();

                    setShouldClear(false);
                }
                repaint();

            }


        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setX2(e.getX());
                setY2(e.getY());
                if (getShape() == ShapeType.FREEHAND || getShape() == ShapeType.ERASER) {
                    setX1(getX2());
                    setY1(getY2());
                    setY2(e.getY());
                    setX2(e.getX());
                    if (getShape() == ShapeType.FREEHAND) {
                        Line tmp = new Line(isDotted(), getX1(), getY1(), getX2(), getY2(), getShape(), getColor());
                        getLinesToAdd().add(tmp);
                    } else {
                        Oval tmp = new Oval(true, true, getX1(), getY1(), getX2(), getY2(), getShape(), Color.white, 30, 30);
                        getEraser().add(tmp);
                    }
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawSavedShapes(g);

        drawLiveShape(g);
    }
}