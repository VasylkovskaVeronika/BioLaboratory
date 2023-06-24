package Classes.Threads.EnemyThreads;

import Classes.Models.FieldTableModel;
import Classes.Views.FieldTableFrame;

public class EnemyMovingThread extends Thread {
    FieldTableModel model;
    FieldTableFrame owner;

    public EnemyMovingThread(FieldTableModel m, FieldTableFrame o) {
        model = m;
        owner = o;
    }

    @Override
    public void run() {
        while (owner.isVisible()) {
            if (model.isGameLost()) {
                owner.gameLost();
            }
            if (!model.getEnemy().isFrozen()) {
                if (down())
                    System.out.println("Enemy moved down successfully");
                else {
                    if (toRight())
                        System.out.println("Enemy moved to the right successfully");
                    else {
                            toLeft();
                            System.out.println("Enemy moved to the left successfully");
                        //}
                    }
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    boolean down() {
        int downPosition = model.getCurrentEnemyPos().x + 1;
        if (downPosition == model.getRowCount()) {
            downPosition = 0;
        }
        return model.moveEnemyVertically(downPosition, 2, owner);
    }
    boolean toRight(){
        int rightPosition = model.getCurrentEnemyPos().y + 1;
        if (rightPosition == model.getColumnCount()) {
            rightPosition = 0;
        }
        return model.moveEnemyHorizontally(rightPosition, 0, owner);
    }
    boolean toLeft() {
        int leftPosition = model.getCurrentEnemyPos().y - 1;
        if (leftPosition == 0) {
            leftPosition = model.getColumnCount() - 1;
        }
        return model.moveEnemyHorizontally(leftPosition, 1, owner);
    }
}
