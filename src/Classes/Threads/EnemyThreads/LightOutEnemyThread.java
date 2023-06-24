package Classes.Threads.EnemyThreads;

import Classes.Models.FieldTableModel;
import Classes.Views.FieldTableFrame;

public class LightOutEnemyThread extends EnemyBoostersThread {

    public LightOutEnemyThread(FieldTableModel m, FieldTableFrame f) {
        super(m, f);
    }

    @Override
    public void run() { //always leaves bonuses in empty cells
        int timeLeft=10000;
        while(timeLeft>0) {
            perform(); //main logic from parent class
            timeLeft-=1000;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //turning off light out booster
        model.getEnemy().lightOff();
        this.interrupt();
    }
}
