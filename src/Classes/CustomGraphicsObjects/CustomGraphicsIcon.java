package Classes.CustomGraphicsObjects;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CustomGraphicsIcon extends JPanel {
    BufferedImage icon;

    public CustomGraphicsIcon(String pathToIcon) {
        try {
            icon= ImageIO.read(new File(pathToIcon));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        if(icon != null){
            g.drawImage(icon, 0, 0, this.getWidth(), this.getHeight(), this);
            //System.out.println("Icon for counter painted");
        }
    }
}
