package Classes.Threads.Counters;

import Classes.Models.CountersTableModel;

import javax.swing.*;

public class TimeCounterThread extends Thread{
    int seconds=00;
    int minutes=00;
    int hours=00;
    CountersTableModel counters;
    JFrame owner;

    public TimeCounterThread(CountersTableModel cm, JFrame o) {
        counters=cm;
        owner=o;
    }

    public void run() {
        while(owner.isVisible()) {
            counters.updateTime(owner, hours+":"+minutes+":"+seconds);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(minutes+1>=60) {
                hours++;
                minutes=0;
            }
            if(seconds+1>=60) {
                minutes++;
                seconds=0;
            }
            else {
                seconds++;
            }
        }
    }
}
