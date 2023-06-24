package Classes.Models;

import Classes.CustomGraphicsObjects.CustomGraphicsCounter;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class CountersTableModel extends AbstractTableModel {
    CustomGraphicsCounter[][] cells;

    public CountersTableModel() {
        cells= new CustomGraphicsCounter[1][3];
//        Arrays.stream(cells).forEach(cs-> {
//            for(int i=0; i<3; i++)
//                cs[i]=new CustomGraphicsCounter();
//        });
        CustomGraphicsCounter donatesCounter=new CustomGraphicsCounter("Images/donateCounter.png", "0");
        donatesCounter.setSize(30, 30);
        donatesCounter.setVisible(true);
        cells[0][0]=donatesCounter;
        CustomGraphicsCounter livesCounter=new CustomGraphicsCounter("Images/LifesCounter.png", "0");
        livesCounter.setSize(30, 30);
        livesCounter.setVisible(true);
        cells[0][1]=livesCounter;
        CustomGraphicsCounter timeCounter=new CustomGraphicsCounter("Images/timeCounter.png", "00:00:00");
        timeCounter.setSize(30, 30);
        timeCounter.setVisible(true);
        cells[0][2]=timeCounter;
        System.out.println("");
    }
//    @Override
//    public void paint(Graphics g) {
//        //super.paintComponent(g);
////        JFrame ctestFrame=new JFrame();
//        donatesCounter=new CustomGraphicsCounter("Images/donateCounter.png", "0");
//        donatesCounter.setSize(30, 30);
//        donatesCounter.setVisible(true);
//        livesCounter=new CustomGraphicsCounter("Images/LifesCounter.png", "0");
//        livesCounter.setSize(30, 30);
//        livesCounter.setVisible(true);
//        timeCounter=new CustomGraphicsCounter("Images/timeCounter.png", "00:00:00");
//        timeCounter.setSize(30, 30);
//        timeCounter.setVisible(true);
//        add(donatesCounter);
//        add(livesCounter);
//        add(timeCounter);
//        this.setSize(200, 30);
//        this.setVisible(true);
//        System.out.println("Counters panel painted");
//    }
    public Class<?> getColumnClass(int columnIndex) {
    return JPanel.class;
}
    public void updateDonates(JFrame updatingOwner, int newValue) {
        cells[0][0].updateText(String.valueOf(newValue), updatingOwner);
    }
    public void updateLives(JFrame updatingOwner, int newValue) {
        cells[0][1].updateText(String.valueOf(newValue), updatingOwner);
    }
    public void updateTime(JFrame updatingOwner, String newValue) {
        cells[0][2].updateText(newValue, updatingOwner);
    }
    public String getTime() {
        return cells[0][2].getText();
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return cells[rowIndex][columnIndex];
    }
    public String getValue(int r, int c) {
        return cells[r][c].getText();
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        cells[rowIndex][columnIndex].add((Component) aValue);
    }
}
