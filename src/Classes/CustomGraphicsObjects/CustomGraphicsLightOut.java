package Classes.CustomGraphicsObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CustomGraphicsLightOut extends CustomGraffics{
    BufferedImage image;
    @Override
    public void paint(Graphics g) {
        try {
            image = ImageIO.read(new File("Images/lightOut.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);
        if(image != null){
            g.drawImage(image, x, y, this.getWidth(), this.getHeight(), this);
        }
    }
}
