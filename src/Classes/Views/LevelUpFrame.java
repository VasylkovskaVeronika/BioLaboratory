package Classes.Views;

import Classes.LevelMessage;
import Classes.Views.BoardSizeDialog;
import Classes.Views.EndGameDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelUpFrame extends JFrame {
    private static final int DEFAULT_WIDTH=400;
    private static final int DEFAULT_HEIGHT=500;
    JButton nextLevel=new JButton("Go Ahead!");
    JButton end=new JButton("End the game");
    LevelMessage messagePanel;

    public LevelUpFrame(int level, int l, int d, String t) {
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

        nextLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoardSizeDialog d = new BoardSizeDialog();
                d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                d.setVisible(true);
                dispose();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        messagePanel = new LevelMessage(level);
        mainPanel.add(messagePanel);
        JPanel buttons = new JPanel();
        end.setBackground(Color.RED);
        nextLevel.setBackground(Color.GREEN);
        buttons.add(end);
        buttons.add(nextLevel);
        buttons.setBackground(messagePanel.getBackground());
        mainPanel.add(buttons);
        add(mainPanel);
    }
}
