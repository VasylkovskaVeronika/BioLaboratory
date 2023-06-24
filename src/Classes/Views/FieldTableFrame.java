package Classes.Views;

import Classes.*;
//import Classes.LabelTableCellRenderer;
import Classes.CustomGraphicsObjects.CustomGraphicsCounter;
import Classes.Models.CountersTableModel;
import Classes.Models.FieldTableModel;
import Classes.Models.PanelTableCellRenderer;
import Classes.Threads.EnemyThreads.EnemyBoostersThread;
import Classes.Threads.EnemyThreads.EnemyMovingThread;
import Classes.Threads.Counters.TimeCounterThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class FieldTableFrame extends JFrame{
    Dimension size;
    int rowH=36;
    int colW=36;
    //CustomGraffics gusP=new CustomGraffics();
    JTable field;
    JTable counters;
    CustomGraphicsCounter donatesCounter=new CustomGraphicsCounter("Images/donateCounter.png", "0");
    CustomGraphicsCounter livesCounter=new CustomGraphicsCounter("Images/LifesCounter.png", "0");
    CustomGraphicsCounter timeCounter=new CustomGraphicsCounter("Images/timeCounter.png", "00:00:00");
    FieldTableModel model;
    CountersTableModel countModel;
    int cells;
    int cols;
    int rows;
    List<Thread> allActions=new ArrayList<>();

    public FieldTableFrame(int c, int r) {
        //counters
        countModel=new CountersTableModel();
        counters=new JTable(countModel);
        counters.setDefaultRenderer(JPanel.class, new PanelTableCellRenderer());
        counters.setRowHeight(30);
        counters.addKeyListener(new KeyListener() {
            boolean isPressed = false;
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_Q) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)
                        && ((e.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0)) {
                    var frame = new StartMenuFrame();
                    frame.setTitle("Daily lab");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    //How to close this game frame? Maybe:
                    dispose();
                }
                if (!isPressed) {
                    frameKeyPressed(e);
                    revalidate();
                    repaint();
                    isPressed = true;
                }
                //System.out.println("Re- called for "+this.toString());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                isPressed = false;
            }
        });

        System.out.println("Counters panel created");
        //table-field
        this.cols=c;
        this.rows=r;
        model= new FieldTableModel(r, c);
        doWithModel();
        model.setOwner(this);

        int top = getInsets().top;

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                colW=(e.getComponent().getWidth()-counters.getWidth())/cols;
                rowH=(e.getComponent().getHeight()-counters.getHeight()-top)/rows;
                field.setRowHeight(rowH);
                repaint();
            }
        });
        System.out.println("frame created");
    }
    private void doWithModel() {
        field=new JTable(model);
        field.setShowGrid(false);
        //field.setDefaultRenderer(Color.class, new ColorTableCellRenderer());
        field.setDefaultRenderer(JPanel.class, new PanelTableCellRenderer());

        field.addKeyListener(new KeyListener() {
            boolean isPressed = false;
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_Q) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)
                        && ((e.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0)) {
                    var frame = new StartMenuFrame();
                    frame.setTitle("Daily lab");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    //How to close this game frame? Maybe:
                    dispose();
                }
                if (!isPressed) {
                    frameKeyPressed(e);
                    revalidate();
                    repaint();
                    isPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        size=new Dimension(cols*18, rows*18+70); // 35x35(gus size)
        field.setRowHeight(rowH);
        for(int i=0; i<cols; i++)
            field.getColumnModel().getColumn(i).setPreferredWidth(colW);
        field.setVisible(true);
        JLayeredPane pane=new JLayeredPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        //field.setValueAt(gusP, 0, 1);

        pane.add(field);
        //pane.add(gusP);

//        this.setMaximumSize(size);
        add(pane);
        //add(donatesCounter);
        add(counters, BorderLayout.NORTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        TimeCounterThread tmt=new TimeCounterThread(countModel, this);
        allActions.add(tmt);
        tmt.start();
        EnemyMovingThread emt=new EnemyMovingThread(model, this);
        allActions.add(emt);
        emt.start();
        EnemyBoostersThread ebt=new EnemyBoostersThread(model, this);
        allActions.add(ebt);
        ebt.start();
        //this.setBackground(Color.cyan);
        pack();
    }
    public void gameLost() {
        TotalRanking.setNewLevel(model.getGus().getCurrentLevel());
        TotalRanking.addSavedLives(model.getGus().getSavedLives());
        TotalRanking.addTime(countModel.getTime());
        GameOverFrame e=new GameOverFrame(model.getGus().getSavedLives(), model.getGus().getJarOfDonates(),
                countModel.getValue(0, 2));
        e.setVisible(true);
//        allActions.stream().forEach(t-> t.interrupt());
        dispose();
    }
    public void frameKeyPressed(java.awt.event.KeyEvent evt) {
            int key = evt.getKeyCode();
            switch (key) {
//            case KeyEvent.VK_Q+KeyEvent.CTRL_DOWN_MASK+KeyEvent.VK_SHIFT:
//                var frame = new StartMenuFrame();
//                frame.setTitle("Daily lab");
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setVisible(true);
//                break;

                case KeyEvent.VK_UP:
                    //moveGusVertically(-1);
                    if(!model.moveGusVertically(-1)) { //no level up
                        countModel.updateDonates(this, model.getGus().getJarOfDonates());
                        countModel.updateLives(this, model.getGus().getSavedLives());
                    }
                    else {
                        showLevelDialogs();
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    //moveGusVertically(1);
                    if(!model.moveGusVertically(1)) {
                        countModel.updateDonates(this, model.getGus().getJarOfDonates());
                        countModel.updateLives(this, model.getGus().getSavedLives());
                    }
                    else {
                        showLevelDialogs();
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    //moveGusHorizontally(1);
                    if(!model.moveGusHorizontally(1)) {
                        countModel.updateDonates(this, model.getGus().getJarOfDonates());
                        countModel.updateLives(this, model.getGus().getSavedLives());
                    }
                    else {
                        showLevelDialogs();
                    }
                    break;

                case KeyEvent.VK_LEFT:
//                    moveGusHorizontally(-1);
                    if(!model.moveGusHorizontally(-1)) {
                        countModel.updateDonates(this, model.getGus().getJarOfDonates());
                        countModel.updateLives(this, model.getGus().getSavedLives());
                    }
                    else {
                        showLevelDialogs();
                    }
                    break;
            }
        }

    void showLevelDialogs() {
//        if(model.getGus().getCurrentLevel()==5) {
//            allActions.stream().forEach(t-> t.interrupt());
//            dispose();
//        }
//        else {
//            allActions.stream().forEach(t-> t.interrupt());
        TotalRanking.setNewLevel(model.getGus().getCurrentLevel());
        TotalRanking.addSavedLives(model.getGus().getSavedLives());
        TotalRanking.addTime(countModel.getTime());
            LevelUpFrame d = new LevelUpFrame(TotalRanking.getLastLevel(),
                    TotalRanking.getTotalSavedLives(), model.getGus().getJarOfDonates(),
                    TotalRanking.getTotalTime());
            d.setVisible(true);
            dispose();
        //}
//        //repaint field
//        model = new FieldTableModel(rows, cols);
//        doWithModel();
//        model.setOwner(this);
    }

    @Override
    public void repaint() {
        super.repaint();
        System.out.println("Field repainted");
    }
}
