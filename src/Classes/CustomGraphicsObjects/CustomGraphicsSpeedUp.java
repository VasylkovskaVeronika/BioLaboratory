package Classes.CustomGraphicsObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CustomGraphicsSpeedUp extends CustomGraphicsSpace{
    BufferedImage image;
    @Override
    public void paint(Graphics g) {
        try {
            image = ImageIO.read(new File("Images/reactive.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);
        if(image != null){
            g.drawImage(image, 0, 0, 30, 30, this);
        }
    }
}
