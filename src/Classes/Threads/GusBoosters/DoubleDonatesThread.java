package Classes.Threads.GusBoosters;

import Classes.Models.FieldTableModel;

public class DoubleDonatesThread extends Thread{
    FieldTableModel model;
    public DoubleDonatesThread(FieldTableModel m) {
        model=m;
    }
    @Override
    public void run() { //changes frozen state after booster time's up
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        model.getGus().undoTakingDoubleDonates();
    }
}
