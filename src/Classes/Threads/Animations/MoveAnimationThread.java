package Classes.Threads.Animations;

import Classes.Models.CustomCellModel;
import Classes.CustomGraphicsObjects.CustomGraffics;
import Classes.CustomGraphicsObjects.CustomGraphicsBlackOut;
import Classes.CustomGraphicsObjects.CustomGraphicsSpace;
import Classes.Models.FieldTableModel;
import Classes.Views.FieldTableFrame;

public class MoveAnimationThread extends Thread{
    CustomCellModel fromCell;
    CustomCellModel toCell;
    FieldTableFrame window;
    CustomGraphicsSpace whatIsLeft;
    int direction=0;
    int mode;
    FieldTableModel model;
    int speed=50;
    String monitor="";

    public MoveAnimationThread(CustomCellModel fc, CustomCellModel tc, FieldTableFrame w, int d,
                               CustomGraphicsSpace s, int m, FieldTableModel f) {
        fromCell=fc;
        toCell=tc;
        window=w;
        direction=d;
        whatIsLeft=s;
        mode=m;
        model=f;
        if(model.getGus().isSpeedUp() && mode==1)
            speed=10;
    }
    public void run() {
            switch (direction) {
                case 0:  // moving to right
                    coreAnimationRight();
                    break;
                case 1: //moving to left
                    coreAnimationLeft();
                    break;
                case 2: //moving down
                    coreAnimationDown();
                    fromCell.add(whatIsLeft);
                    break;
                case 3: //moving up
                    coreAnimationUp();
                    break;
            }
            //CustomGraphicsSpace s=new CustomGraphicsSpace();
            fromCell.add(whatIsLeft);
            window.revalidate();
            window.repaint();
            System.out.println("Window " + window.toString() + " re- called");
            this.interrupt();
    }
    void coreAnimationRight() {
        for(int i=1; i<7; i++){
            CustomGraffics goingFG=(mode==1)? new CustomGraffics(): new CustomGraphicsBlackOut(model.getEnemy());
            goingFG.setX(i*6);
            goingFG.setSize(36, 36);
            goingFG.setVisible(true);
            fromCell.add(goingFG);
            CustomGraffics goingTG=(mode==1)? new CustomGraffics(): new CustomGraphicsBlackOut(model.getEnemy());
            goingTG.setX(i*6-36);
            goingTG.setSize(36, 36);
            goingTG.setVisible(true);
            toCell.add(goingTG);
            //window.revalidate();
            window.repaint();
            System.out.println("Window "+window.toString()+" re- called");
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    void coreAnimationLeft() {
        for(int i=1; i<7; i++){
            CustomGraffics goingFG=(mode==1)? new CustomGraffics(): new CustomGraphicsBlackOut(model.getEnemy());
            goingFG.setX(i*(-6));
            goingFG.setSize(36, 36);
            goingFG.setVisible(true);
            fromCell.add(goingFG);
            CustomGraffics goingTG=(mode==1)? new CustomGraffics(): new CustomGraphicsBlackOut(model.getEnemy());
            goingTG.setX(i*(-6)+36);
            goingTG.setSize(36, 36);
            goingTG.setVisible(true);
            toCell.add(goingTG);
            //window.revalidate();
            window.repaint();
            System.out.println("Window "+window.toString()+" re- called");
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    void coreAnimationDown() {
        for(int i=1; i<7; i++){
            CustomGraffics goingFG=(mode==1)? new CustomGraffics(): new CustomGraphicsBlackOut(model.getEnemy());
            goingFG.setY(i*6);
            goingFG.setSize(36, 36);
            goingFG.setVisible(true);
            fromCell.add(goingFG);
            CustomGraffics goingTG=(mode==1)? new CustomGraffics(): new CustomGraphicsBlackOut(model.getEnemy());
            goingTG.setY(i*6-36);
            goingTG.setSize(36, 36);
            goingTG.setVisible(true);
            toCell.add(goingTG);
            //window.revalidate();
            System.out.println("Window "+window.toString()+" re- called");
            window.repaint();
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    void coreAnimationUp() {
        for(int i=1; i<7; i++){
            CustomGraffics goingFG=(mode==1)? new CustomGraffics(): new CustomGraphicsBlackOut(model.getEnemy());
            goingFG.setY(i*(-6) );
            goingFG.setSize(36, 36);
            goingFG.setVisible(true);
            fromCell.add(goingFG);
            CustomGraffics goingTG=(mode==1)? new CustomGraffics(): new CustomGraphicsBlackOut(model.getEnemy());
            goingTG.setY(i*(-6)+36);
            goingTG.setSize(36, 36);
            goingTG.setVisible(true);
            toCell.add(goingTG);
            //window.revalidate();
            window.repaint();
            System.out.println("Window "+window.toString()+" re- called");
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {

    }
}
