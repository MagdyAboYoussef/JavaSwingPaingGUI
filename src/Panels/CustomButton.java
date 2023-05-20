package Panels;

import Shapes.ShapeType;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class CustomButton extends JButton {
    private Color buttonColor;

    public CustomButton(String buttonText, Color color, JPanel colorPreview, JPanel toAdd, DrawingPanel drawingPanel) {
        super(buttonText);
        this.buttonColor = color;
        this.setBackground(buttonColor);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                colorPreview.setBackground(buttonColor);
                drawingPanel.setColorMethod(buttonColor);

            }
        });
        toAdd.add(this);
    }


    public CustomButton(JPanel toAdd, String imagePath, ShapeType shape, DrawingPanel drawingPanel) {
        try {
            Image img = ImageIO.read(new File(imagePath));
            this.setIcon(new ImageIcon(img));
            toAdd.add(this);
        } catch (Exception e) {
            System.out.println("Error loading image");
        }
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                drawingPanel.setShape(shape);
            }
        });
    }


}





