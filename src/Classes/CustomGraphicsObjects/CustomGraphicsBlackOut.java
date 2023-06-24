package Classes.CustomGraphicsObjects;

import Classes.BlackOut;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CustomGraphicsBlackOut extends CustomGraffics{
    BlackOut e=new BlackOut();
    public CustomGraphicsBlackOut(BlackOut e) {
        this.e=e;
    }
    @Override
    public void paint(Graphics g) {
        BufferedImage image=null;
        try {
            image = ImageIO.read(new File(e.getPathToImage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);
        if(image != null){
            g.drawImage(image, x, y, this.getWidth()- e.getHowScaledIs(),
                    this.getHeight()- e.getHowScaledIs(), this);
        }
        System.out.println("Black out painted");
    }
}
