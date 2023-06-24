package Classes.Threads.EnemyThreads;

import Classes.CustomGraphicsObjects.*;
import Classes.Models.FieldTableModel;
import Classes.Views.FieldTableFrame;

public class EnemyBoostersThread extends Thread{
    //what
    CustomGraphicsSpace bonus;
    //where
    FieldTableModel model;
    FieldTableFrame window;

    public EnemyBoostersThread(FieldTableModel m, FieldTableFrame f) {
        model=m;
        window=f;
    }

    @Override
    public void run() {
        System.out.println(window.isVisible());
        while(window.isVisible()) {
            int rand = (int) (Math.random() * 3);
            if(rand==0) {
                perform();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void perform() {
        if (model.isReplaceEnemyContentFree()) {
            int rand2 = (int) (Math.random() * 4);
            System.out.println("**" + rand2);
            switch (rand2) {
                case 0:
                    bonus = new CustomGraphicsDoubleDonate();
                    break;
                case 1:
                    bonus = new CustomGraphicsNewHome();
                    break;
                case 2:
                    bonus=new CustomGraphicsFreezeUp();
                    break;
                case 3:
                    bonus=new CustomGraphicsSpeedUp();
                    break;
                case 4: //light out booster
                    //visually
                    model.getEnemy().lightOn();
                    //logically
                    LightOutEnemyThread let=new LightOutEnemyThread(model, window);
                    try {
                        let.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;

            }
            model.setReplaceEnemyContent(bonus);
        }
    }
}
