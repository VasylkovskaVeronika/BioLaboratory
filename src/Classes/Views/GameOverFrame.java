package Classes.Views;

import Classes.LevelMessage;

import javax.swing.*;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverFrame extends JFrame {
    private static final int DEFAULT_WIDTH=400;
    private static final int DEFAULT_HEIGHT=300;

    JButton exit=new JButton("Exit to the menu");
    JButton end=new JButton("Save my score");
    LevelMessage messagePanel;

    //JFrame owner?
    public GameOverFrame(int l, int d, String t) {
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var dialog = new EndGameDialog(l, d, t);
                //dialog.setTitle("Daily lab");
                //dialog.setDefaultCloseOperation();
                dialog.setVisible(true);
                dispose();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var frame = new StartMenuFrame();
                frame.setTitle("Daily lab");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                dispose();
            }
        });

        JPanel mainPanel=new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        messagePanel=new LevelMessage(-1);
        mainPanel.add(messagePanel);
        JPanel buttons=new JPanel();
        exit.setOpaque(false);
        end.setOpaque(false);
        exit.setForeground(Color.white);
        exit.setBackground(Color.BLACK);
        end.setForeground(Color.white);
        end.setBackground(Color.BLACK);
        buttons.add(exit);
        buttons.add(end);
        buttons.setOpaque(true);
        buttons.setBackground(Color.BLACK);
        mainPanel.add(buttons);
        add(mainPanel);
        setVisible(true);
    }
    public static void main(String[] args) {
        GameOverFrame e=new GameOverFrame(9, 6,
                "countModel.getValue(0, 2)");
        e.setVisible(true);
    }
}
