package Classes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.ColorModel;

public class Gus {
    int jarOfDonates=0; //score counter
    int savedLives=0; //analog of life counter, a little different conception
    int currentLevel=1;
    boolean isTakingDoubleDonuts=false;
    boolean isSpeedUp=false;

    public void collectDonate() {
        this.jarOfDonates+=isTakingDoubleDonuts? 2:1;
    }
    public void buySUV() {
        this.jarOfDonates -=10;
        this.savedLives+=1;
//        currentLevel=levelUp();
    }
    public void buyBird() {
        this.jarOfDonates -=5;
        this.savedLives+=1;
//        currentLevel=levelUp();
    }
    public void levelUp() {
        currentLevel++;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getJarOfDonates() {
        return jarOfDonates;
    }

    public int getSavedLives() {
        return savedLives;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
    public void makeTakingDoubleDonuts() {
        isTakingDoubleDonuts=true;
    }
    public void undoTakingDoubleDonates() {
        isTakingDoubleDonuts=false;
    }
    public void makeSpeedUp() {
        isSpeedUp=true;
    }
    public void undoSpeedUp() {
        isSpeedUp=false;
    }
    public void saveFamily(int lives) {
        this.savedLives+=lives;
    }

    public boolean isSpeedUp() {
        return isSpeedUp;
    }

    public static void paintGus(Graphics g, int rightMargin, int upMargin) {
        var g2=(Graphics2D) g;
        Stroke s=new BasicStroke(2);
        g2.setStroke(s);
        Paint p=Color.BLACK;
        g2.setPaint(p);
        Arc2D body=new Arc2D.Double(rightMargin+1.75,upMargin+8,25.5,15.5,30, -200, Arc2D.PIE);
        //g2.draw(body);
        //shape.append(body, true);
        g2.draw(body);
        Ellipse2D head=new Ellipse2D.Double(rightMargin+17.5, upMargin+2, 15.5, 10.5);
        //shape.append(head, true);
        g2.draw(head);
        Arc2D leftWing=new Arc2D.Double(rightMargin+1.75, upMargin+3,
                20.5, 13, -40, -160, Arc2D.CHORD);
        g2.draw(leftWing);
        Polygon nose=new Polygon(new int[]{rightMargin+30, rightMargin+35, rightMargin+30},
                new int[]{upMargin+5, upMargin+8, upMargin+11}, 3);
        g2.draw(nose);
        Ellipse2D eye=new Ellipse2D.Double(rightMargin+28, upMargin+5, 3, 3);
        g2.setPaint(Color.WHITE);
        g2.fill(body);
        g2.fill(leftWing);
        g2.fill(head);
        g2.setPaint(Color.ORANGE);
        g2.fill(nose);
        g2.setPaint(Color.BLACK);
        g2.fill(eye);
        System.out.println("Gus painted");
    }
}
