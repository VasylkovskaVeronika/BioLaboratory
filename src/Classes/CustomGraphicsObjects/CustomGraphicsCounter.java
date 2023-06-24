package Classes.CustomGraphicsObjects;

import javax.swing.*;
import java.awt.*;

public class CustomGraphicsCounter extends JPanel {
    //BufferedImage icon;
    JLabel text=new JLabel();
    CustomGraphicsIcon icon;

    public CustomGraphicsCounter(String pathToIcon, String t) {
        icon=new CustomGraphicsIcon(pathToIcon);
//        icon.setSize(50, 50);
        icon.setPreferredSize(new Dimension(25, 25));
//        icon.setMinimumSize(new Dimension(50, 50));
        text = new JLabel(t);
        text.setFont(new java.awt.Font("Arial", Font.ITALIC, 16));
        //text.setOpaque(true);
        //text.setBackground(Color.WHITE);
        text.setForeground(Color.BLUE);
//        System.out.println(image);
//        add(image, BorderLayout.WEST);
        add(icon, BorderLayout.WEST);
        add(text, BorderLayout.EAST);
        this.setSize(60, 50);
        System.out.println(this);
        this.setOpaque(true);
        this.setBackground(Color.ORANGE);
    }

    public void updateText(String value, JFrame owner) {
        this.text.setText(value);
        owner.repaint();
    }

    public String getText() {
        return text.getText();
    }
}
