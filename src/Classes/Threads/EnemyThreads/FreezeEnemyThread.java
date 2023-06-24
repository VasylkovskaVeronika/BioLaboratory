package Classes.Threads.EnemyThreads;

import Classes.Models.FieldTableModel;

public class FreezeEnemyThread extends Thread{
    FieldTableModel model;
    public FreezeEnemyThread(FieldTableModel m) {
        model=m;
    }
    @Override
    public void run() { //changes frozen state after booster time's up
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            model.getEnemy().unfreeze();
    }
}
