package Shapes;

import java.awt.*;

public class ImageShape extends Shape{
    Image image;

    public Image getImage() {
        return image;
    }

    public ImageShape(Image image, ShapeType shape, int width, int height) {
        super(false, false, 0,0, width, height, shape, null);
        this.image = image;
    }


}
