package Panels;

import Shapes.Rectangle;
import Shapes.Shape;
import Shapes.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

abstract class DrawingService extends JPanel {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Color color;
    private ShapeType shape;
    private boolean fill, dotted, shouldClear;
    private List<Shape> shapeArray = new ArrayList<>();
    private List<Shape> poppedList = new ArrayList<>();

    private List<Shape> linesToAdd, eraser;

    //public getters
    public boolean isDotted() {

        return dotted;
    }

    public boolean isFill() {

        return fill;
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

    public Color getColor() {
        return color;
    }

    public ShapeType getShape() {
        return shape;
    }

    public boolean isShouldClear() {
        return shouldClear;
    }

    //protected setters
    protected List<Shape> getShapeArray() {
        return shapeArray;
    }

    protected List<Shape> getPoppedList() {
        return poppedList;
    }

    protected List<Shape> getLinesToAdd() {
        return linesToAdd;
    }

    protected List<Shape> getEraser() {
        return eraser;
    }

    protected void setDotted(boolean dotted) {

        this.dotted = dotted;
    }

    protected void setFill(boolean fill) {

        this.fill = fill;
    }

    protected void setX1(int x1) {
        this.x1 = x1;
    }

    protected void setY1(int y1) {
        this.y1 = y1;
    }

    protected void setX2(int x2) {
        this.x2 = x2;
    }

    protected void setY2(int y2) {
        this.y2 = y2;
    }

    protected void setColor(Color color) {
        this.color = color;
    }

    protected void setShouldClear(boolean shouldClear) {
        this.shouldClear = shouldClear;
    }

    protected void setShapeArray(List<Shape> shapeArray) {
        this.shapeArray = shapeArray;
    }

    protected void setPoppedList(List<Shape> poppedList) {
        this.poppedList = poppedList;
    }

    protected void setLinesToAdd(List<Shape> linesToAdd) {
        this.linesToAdd = linesToAdd;
    }

    protected void setEraser(List<Shape> eraser) {
        this.eraser = eraser;
    }

    protected void setColorMethod(Color col) {
        this.color = col;
    }

    protected void setShape(ShapeType shape) {
        this.shape = shape;
    }


    //methods
    public void pop() {
        if (shapeArray.size() > 0) {
            Shape poppedShape = shapeArray.remove(shapeArray.size() - 1);
            poppedList.add(poppedShape);
            repaint();
            shouldClear = true;
        }
    }

    public void push() {
        if (poppedList.size() > 0) {
            Shape shape = poppedList.remove(poppedList.size() - 1);
            shapeArray.add(shape);
            repaint();
        }

    }

    public void clear() {
        Rectangle r = new Rectangle(true, true, 0, 0, 0, 0, ShapeType.CLEAR, Color.white, getWidth(), getHeight());
        shapeArray.add(r);
        repaint();

    }

    public int calculateWidth() {
        return Math.abs(x2 - x1);

    }

    public int calculateWidth(int num1, int num2) {
        return Math.abs(num2 - num1);

    }

    public int calculateHeight() {

        return Math.abs(y2 - y1);
    }

    public int calculateHeight(int num1, int num2) {

        return Math.abs(num2 - num1);
    }

    public int getStartX() {
        return Math.min(x1, x2);

    }

    public int getStartX(int num1, int num2) {
        return Math.min(num1, num2);

    }

    public int getStartY() {

        return Math.min(y1, y2);
    }

    public int getStartY(int num1, int num2) {

        return Math.min(num1, num2);
    }

    protected void resetEraserList() {
        eraser = new ArrayList<>();
    }

    protected void resetLineList() {
        linesToAdd = new ArrayList<>();
    }

    public void loadImage(int imgNum) {

        try {
            ImageIcon JIcon = new ImageIcon("src/Resources/savedImages/imgNum" + imgNum + ".png");
            Image i = JIcon.getImage();
            ImageShape image = new ImageShape(i, ShapeType.IMAGE, getWidth(), getWidth());
            shapeArray.add(image);
            repaint();

        } catch (Exception e) {
            System.out.println("Image loading failed");
        }

    }

    public void saveImage(int imgNum) {
        BufferedImage buff = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.paint(buff.getGraphics());
        File file = new File("src/Resources/savedImages/imgNum" + imgNum + ".png");
        try {
            ImageIO.write(buff, "png", file);
        } catch (Exception e) {
            System.out.println("Image saving failed");
        }
    }


    protected void drawSavedShapes(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(3, 1, 1, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);


        for (int i = 0; i < shapeArray.size(); i++) {

            Shape current = shapeArray.get(i);
            g.setColor(current.getCol());
            g2d.setColor(current.getCol());

            if (current.getShape() == ShapeType.IMAGE) {
                ImageShape img = (ImageShape) current;
                g.drawImage(img.getImage(), img.getX1(), img.getY1(), getWidth(), getHeight(), this);

            } else if (current.getShape() == ShapeType.LINE) {
                if (current.isDotted()) {
                    g2d.drawLine(current.getX1(), current.getY1(), current.getX2(), current.getY2());
                } else {
                    g.drawLine(current.getX1(), current.getY1(), current.getX2(), current.getY2());
                }
            } else if (current.getShape() == ShapeType.RECTANGLE || current.getShape() == ShapeType.OVAL) {
                int x1 = current.getX1();
                int y1 = current.getY1();
                int x2 = current.getX2();
                int y2 = current.getY2();
                if (current.getShape() == ShapeType.RECTANGLE) {
                    if (current.isDotted()) {
                        g2d.drawRect(getStartX(x1, x2), getStartY(y1, y2), calculateWidth(x2, x1), calculateHeight(y2, y1));
                    } else if (current.isFill()) {
                        g.fillRect(getStartX(x1, x2), getStartY(y1, y2), calculateWidth(x2, x1), calculateHeight(y2, y1));

                    } else {
                        g.drawRect(getStartX(x1, x2), getStartY(y1, y2), calculateWidth(x2, x1), calculateHeight(y2, y1));

                    }
                } else {
                    if (current.isDotted()) {
                        g2d.drawOval(getStartX(x1, x2), getStartY(y1, y2), calculateWidth(x2, x1), calculateHeight(y2, y1));

                    } else if (current.isFill()) {
                        g.fillOval(getStartX(x1, x2), getStartY(y1, y2), calculateWidth(x2, x1), calculateHeight(y2, y1));

                    } else {
                        g.drawOval(getStartX(x1, x2), getStartY(y1, y2), calculateWidth(x2, x1), calculateHeight(y2, y1));

                    }
                }
            } else if (current.getShape() == ShapeType.FREEHAND) {
                Pattern test = (Pattern) current;
                for (int s = 0; s < test.getShapeList().size(); s++) {
                    Shape lineNow = test.getShapeList().get(s);
                    g.drawLine(lineNow.getX1(), lineNow.getY1(), lineNow.getX2(), lineNow.getY2());

                }

            } else if (current.getShape() == ShapeType.ERASER) {
                Pattern test = (Pattern) current;
                for (int s = 0; s < test.getShapeList().size(); s++) {
                    Shape ovalNow = test.getShapeList().get(s);
                    g.fillOval(ovalNow.getX1() - 15, ovalNow.getY1() - 15, 30, 30);
                }
            } else if (current.getShape() == ShapeType.CLEAR) {
                g.fillRect(current.getX1(), current.getY1(), getWidth(), getHeight());
            }

        }
    }


    protected void drawLiveShape(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(3, 1, 1, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);


        g.setColor(color);
        g2d.setColor(color);

        if (shape == ShapeType.LINE) {
            if (dotted) {
                g2d.drawLine(x1, y1, x2, y2);
            } else {
                g.drawLine(x1, y1, x2, y2);
            }
        } else if (shape == ShapeType.RECTANGLE || shape == ShapeType.OVAL) {
            int width = calculateWidth();
            int height = calculateHeight();
            int startX = getStartX();
            int startY = getStartY();
            if (shape == ShapeType.RECTANGLE) {
                if (dotted) {
                    g2d.drawRect(startX, startY, width, height);
                } else if (fill) {
                    g.fillRect(startX, startY, width, height);
                } else {
                    g.drawRect(startX, startY, width, height);

                }
            } else {
                if (dotted) {
                    g2d.drawOval(startX, startY, width, height);
                } else if (fill) {
                    g.fillOval(startX, startY, width, height);
                } else {
                    g.drawOval(startX, startY, width, height);
                }
            }

        } else if (shape == ShapeType.FREEHAND) {
            for (int s = 0; s < linesToAdd.size(); s++) {
                g.drawLine(linesToAdd.get(s).getX1(), linesToAdd.get(s).getY1(), linesToAdd.get(s).getX2(), linesToAdd.get(s).getY2());

            }
        } else if (shape == ShapeType.ERASER) {
            g.setColor(Color.white);
            for (int o = 0; o < eraser.size(); o++) {
                g.fillOval(eraser.get(o).getX1() - 15, eraser.get(o).getY1() - 15, 30, 30);
            }

        }
    }
}

