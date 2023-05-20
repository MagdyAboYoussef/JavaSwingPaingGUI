package Panels;

import Shapes.ShapeType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LeftPanel extends JPanel {

    private DrawingPanel drawingPanel = new DrawingPanel();

    public DrawingPanel getDrawingPanel() {

        return drawingPanel;
    }

    public LeftPanel() {


        setBackground(Color.ORANGE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JScrollBar blueScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 255);
        JScrollBar redScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 255);
        JScrollBar greenScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 255);

        JTextField redValue = new JTextField(3);
        JTextField greenValue = new JTextField(3);
        JTextField blueValue = new JTextField(3);
        JPanel colorPreview = new JPanel();
        colorPreview.setPreferredSize(new Dimension(50, 50));

        AdjustmentListener adjustmentListener = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                int r = redScrollBar.getValue();
                int g = greenScrollBar.getValue();
                int b = blueScrollBar.getValue();
                redValue.setText(String.valueOf(r));
                greenValue.setText(String.valueOf(g));
                blueValue.setText(String.valueOf(b));
                Color current = new Color(r, g, b);
                colorPreview.setBackground(current);
                drawingPanel.setColorMethod(current);
            }
        };


        redScrollBar.addAdjustmentListener(adjustmentListener);
        greenScrollBar.addAdjustmentListener(adjustmentListener);
        blueScrollBar.addAdjustmentListener(adjustmentListener);


        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Red:"), gbc);
        gbc.gridy = 1;
        add(redScrollBar, gbc);
        gbc.gridy = 2;
        add(redValue, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(new JLabel("Green:"), gbc);
        gbc.gridy = 1;
        add(greenScrollBar, gbc);
        gbc.gridy = 2;
        add(greenValue, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        add(new JLabel("Blue:"), gbc);
        gbc.gridy = 1;
        add(blueScrollBar, gbc);
        gbc.gridy = 2;
        add(blueValue, gbc);

        JPanel buttonPanel = new JPanel();
        CustomButton redButton = new CustomButton("Red", Color.RED, colorPreview, buttonPanel, drawingPanel);
        CustomButton greenButton = new CustomButton("Green", Color.GREEN, colorPreview, buttonPanel, drawingPanel);
        CustomButton blueButton = new CustomButton("Blue", Color.BLUE, colorPreview, buttonPanel, drawingPanel);


        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        add(buttonPanel, gbc);

        gbc.gridy = 4;
        add(colorPreview, gbc);
        gbc.gridy = 5;
        JPanel shapes = new JPanel();


        CustomButton circleButton = new CustomButton(shapes, "src/Resources/images/circle.png", ShapeType.OVAL, drawingPanel);
        CustomButton rectangleButton = new CustomButton(shapes, "src/Resources/images/rectangle.png", ShapeType.RECTANGLE, drawingPanel);
        CustomButton line = new CustomButton(shapes, "src/Resources/images/line.png", ShapeType.LINE, drawingPanel);
        add(shapes, gbc);
        JPanel shapes2 = new JPanel();
        gbc.gridy = 6;
        CustomButton freeHand = new CustomButton(shapes2, "src/Resources/images/pencil.png", ShapeType.FREEHAND, drawingPanel);
        CustomButton eraser = new CustomButton(shapes2, "src/Resources/images/eraser.png", ShapeType.ERASER, drawingPanel);
        add(shapes2, gbc);
        gbc.gridy = 7;
        JPanel action = new JPanel();
        CustomButton undo = new CustomButton(action, "src/Resources/images/undo.png", ShapeType.UNDO, drawingPanel);
        undo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                drawingPanel.pop();
            }
        });
        CustomButton redo = new CustomButton(action, "src/Resources/images/redo.png", ShapeType.REDO, drawingPanel);
        redo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                drawingPanel.push();
            }
        });
        CustomButton clear = new CustomButton(action, "src/Resources/images/delete.png", ShapeType.CLEAR, drawingPanel);
        clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                drawingPanel.clear();
            }
        });
        add(action, gbc);
        gbc.gridy = 8;
        JPanel boxes = new JPanel();
        JCheckBox dotted = new JCheckBox("Dotted");
        dotted.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                drawingPanel.setDotted(!drawingPanel.isDotted());
            }
        });

        JCheckBox fill = new JCheckBox("Fill");

        fill.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                drawingPanel.setFill(!drawingPanel.isFill());
            }
        });

        boxes.add(fill);
        boxes.add(dotted);

        add(boxes, gbc);
        JTextField textField = new JTextField("Image Number", 10);

        gbc.gridy = 9;
        add(textField, gbc);
        gbc.gridy = 10;

        JButton save = new JButton("Save image");
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int x;
                try {
                    x = Integer.parseInt(textField.getText());
                    drawingPanel.saveImage(x);
                } catch (Exception exception) {
                    System.out.println("Enter an Integer");
                }
            }
        });

        JButton load = new JButton("Load image");
        load.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int x;
                try {
                    x = Integer.parseInt(textField.getText());
                    drawingPanel.setShape(ShapeType.IMAGE);
                    drawingPanel.loadImage(x);
                } catch (Exception exception) {
                    System.out.println("Enter an Integer");
                }
            }
        });
        JPanel loadSave = new JPanel();
        loadSave.add(load);
        loadSave.add(save);
        add(loadSave, gbc);

    }

}
