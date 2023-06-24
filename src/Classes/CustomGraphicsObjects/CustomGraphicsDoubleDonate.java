package Classes.CustomGraphicsObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CustomGraphicsDoubleDonate extends CustomGraphicsSpace {
    BufferedImage image;
    @Override
    public void paint(Graphics g) {
        //Image background = Toolkit.getDefaultToolkit().createImage("Background.png");
        //Image bgImage=Toolkit.getDefaultToolkit().createImage("Images/donut.png");
        try {
            image = ImageIO.read(new File("Images/doubleDonuts.png"));
//            JLabel i=new JLabel(new ImageIcon(image));
//            add(i);
//            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);
        if(image != null){
            g.drawImage(image, 5, 5, this.getWidth()-10, this.getHeight()-10, this);
        }
        //setBackground(Color.PINK);
        //g.drawImage(bgImage, -50, 100, null);

    }
}
