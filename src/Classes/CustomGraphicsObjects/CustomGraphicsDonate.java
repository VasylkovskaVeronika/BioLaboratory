package Classes.CustomGraphicsObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CustomGraphicsDonate extends CustomGraphicsSpace {
    BufferedImage image;
    @Override
    public void paint(Graphics g) {
        try {
            image = ImageIO.read(new File("Images/donut.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);
        if(image != null){
            g.drawImage(image, 5, 5, this.getWidth()-10, this.getHeight()-10, this);
        }


    }
}
