package Classes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Obstacle {
    int row;
    int column;
    static BufferedImage image;
    public Obstacle(int r, int c) {
        row=r;
        column=c;
        try {
            image = ImageIO.read(new File("Images/obstacleTexture.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    public static void paintObstacle(Graphics g, Dimension size) {
        var g2=(Graphics2D) g;
        //Stroke s=new BasicStroke(2);
        //Paint p=new TexturePaint(image, new Rectangle(18, 18));
        Paint p=new GradientPaint(new Point(0, 0), Color.ORANGE, new Point(30, 30), Color.PINK);
        //g2.setStroke(s);
        g2.setPaint(p);
        Rectangle r=new Rectangle(0, 0, size.width, size.height);
        g2.fill(r);
    }
}
