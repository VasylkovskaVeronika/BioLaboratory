package Classes.Threads.Animations;

import Classes.CustomGraphicsObjects.CustomGraffics;
import Classes.CustomGraphicsObjects.CustomGraphicsBlackOut;
import Classes.Models.FieldTableModel;
import Classes.Views.FieldTableFrame;

public class EnemyToLightOutAnimation extends Thread{
    FieldTableFrame window;
    FieldTableModel model;

    public EnemyToLightOutAnimation(FieldTableFrame w, FieldTableModel m) {
        window=w;
        model=m;
    }
    public void run() {
        for(int i=1; i<7; i++){
            model.getEnemy().setHowScaledIs(6*i);
            CustomGraffics e = new CustomGraphicsBlackOut(model.getEnemy());
            e.setSize(36, 36);
            e.setVisible(true);
            model.setValueAt(e, model.getCurrentEnemyPos().x, model.getCurrentEnemyPos().y);
            //window.revalidate();
            window.repaint();
            System.out.println("Window "+window.toString()+" re- called");
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        model.getEnemy().lightOn();
        this.interrupt();
    }
}
