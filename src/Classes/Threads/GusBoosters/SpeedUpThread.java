package Classes.Threads.GusBoosters;

import Classes.Models.FieldTableModel;

public class SpeedUpThread extends Thread{
    FieldTableModel model;
    public SpeedUpThread(FieldTableModel m) {
        model=m;
    }
    @Override
    public void run() { //changes frozen state after booster time's up
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            model.getGus().undoSpeedUp();
    }
}
