package Classes.CustomGraphicsObjects;

import Classes.Gus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class CustomGraffics extends JPanel {

    int x=0;
    int y=0;
    public CustomGraffics() {
        this.setOpaque(false);
        System.out.println("Custom graffics created");
    }
    public void paint(Graphics g) {
        Gus.paintGus(g, x, y);
        this.setOpaque(false);
        System.out.println("Custom graffics done");
    }

    @Override
    public void repaint(long tm, int x, int y, int width, int height) {
        super.repaint(tm, this.x, this.y, width, height);
        System.out.println("Repaint CustomGraphics done");
    }

// doesn't work (
//    protected void paintComponent(Graphics g) {
//        // Let UI delegate paint first
//        // (including background filling, if I'm opaque)
//        super.paintComponent(g);
//        // paint my contents next....
//        Gus.paintGus(g, x, y);
//        System.out.println("Custom graffics paintComp done");
//    }

    @Override
    public void validate() {
        super.validate();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static void main(String[] args) {
//        CustomGraffics m=new CustomGraffics();
//        m.setLayout(null);
//        //m.setLocation(5, m.getY());
//        JFrame f=new JFrame();
//        f.add(m);
//        f.setSize(35,35);
//        f.setLayout(null);
//        f.setVisible(true);
//        System.out.println("Custom graffics main called");
    }
}
