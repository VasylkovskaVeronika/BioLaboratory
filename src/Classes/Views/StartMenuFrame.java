package Classes.Views;

import Classes.CustomGraphicsObjects.CustomGraphicsIcon;
import Classes.Views.BoardSizeDialog;
import Classes.Views.HighScoresFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenuFrame extends JFrame {
    private static final int DEFAULT_WIDTH=500;
    private static final int DEFAULT_HEIGHT=300;

    public StartMenuFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        var menuBar=new JMenuBar();
        var newGameItem=new JMenuItem("New Game");
        newGameItem.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));
        newGameItem.setForeground(Color.GREEN);
        newGameItem.setBackground(Color.BLACK);
        newGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoardSizeDialog d=new BoardSizeDialog();
                d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                d.setVisible(true);
                dispose();
            }
        });
        var highScoresItem=new JMenuItem("High Scores");
        highScoresItem.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));
        highScoresItem.setForeground(Color.GREEN);
        highScoresItem.setBackground(Color.BLACK);
        highScoresItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var frame = new HighScoresFrame();
                frame.setTitle("Best bio-weapons in the laboratory");
                frame.setVisible(true);
                //dispose();
            }
        });

        var exitItem=new JMenuItem("Exit");
        exitItem.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));
        exitItem.setForeground(Color.GREEN);
        exitItem.setBackground(Color.BLACK);
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuBar.add(newGameItem);
        menuBar.add(highScoresItem);
        menuBar.add(exitItem);
//        menuBar.setFont(new java.awt.Font("Arial", Font.ITALIC, 16));
//        menuBar.setForeground(Color.GREEN);
        setJMenuBar(menuBar);
        //setPreferredSize(new Dimension(300, 300));
        var icon=new CustomGraphicsIcon("Images/welcomeGoose.png");
//        icon.setSize(50, 50);

//        icon.setMinimumSize(new Dimension(50, 50));
        var text = new JTextArea("Welcome to our lab, mate! For sure you have heard about our most famous members " +
                "such as radioactive nomad cats, infectious mosquitoes, suspicious pigs and, of course, geese " +
                " that infect rashists with flu.\nWe are not about to stop, we'll help anyhow we can. Would you" +
                " accept the challenge?");
        text.setFont(new java.awt.Font("Arial", Font.ITALIC, 16));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        //text.setSize(250, 200);
        text.setEditable(false);
        text.setRows(10);
        text.setColumns(18);
        icon.setOpaque(false);
        icon.setPreferredSize(new Dimension(this.getWidth()-300, 180));
        text.setForeground(Color.WHITE);
        text.setMargin(new Insets(10, 10, 10, 10));
        text.setOpaque(false);
        FlowLayout experimentLayout = new FlowLayout();
        getContentPane().setLayout(experimentLayout);
        add(text);
        add(icon);
        getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        getContentPane().setBackground(Color.black);
        setVisible(true);
    }
}
