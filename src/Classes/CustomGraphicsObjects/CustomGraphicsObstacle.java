package Classes.CustomGraphicsObjects;

import Classes.Obstacle;

import javax.swing.*;
import java.awt.*;

public class CustomGraphicsObstacle extends JPanel {
    Dimension size=new Dimension(35, 35);

    public void paint(Graphics g) {
        Obstacle.paintObstacle(g, size);
    }
    public static void main(String[] args) {
        CustomGraphicsObstacle m=new CustomGraphicsObstacle();
        JFrame f=new JFrame();
        f.add(m);
        f.setSize(35,35);
        //f.setLayout(null);
        f.setVisible(true);
    }
}
