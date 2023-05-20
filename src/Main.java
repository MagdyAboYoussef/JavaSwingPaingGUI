import Panels.DrawingPanel;
import Panels.LeftPanel;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {


        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1000, 800);


        LeftPanel left = new LeftPanel();
        frame.add(left, BorderLayout.WEST);
        DrawingPanel drawingPanel = left.getDrawingPanel();
        drawingPanel.setBounds(0, 0, 250, 800);
        drawingPanel.setBackground(Color.WHITE);

        frame.add(drawingPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}