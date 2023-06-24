package Classes.Views;

import Classes.CustomSerializeDeserialize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameDialog extends JDialog {
    private static final int DEFAULT_WIDTH=250;
    private static final int DEFAULT_HEIGHT=200;
    JButton save=new JButton("Save");
    JTextField name=new JTextField("Your name");

    public EndGameDialog(int l, int d, String t) {
        super((Frame) null, "The end", true);

        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setDefaultCloseOperation(exitToStart());

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomSerializeDeserialize.reloadFile(new CustomSerializeDeserialize.PlayerStat(name.getText(), l, d, t));
                //exit to the start menu
                exitToStart();
            }
        });


        JPanel mainPanel=new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        JLabel text=new JLabel("Enter your name to save in rating:");
        text.setLabelFor(name);
        mainPanel.add(text);
        mainPanel.add(name);
        mainPanel.add(save);
        add(mainPanel);
    }
    int exitToStart() {
        var frame = new StartMenuFrame();
        frame.setTitle("Daily lab");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        dispose();
        return 0;
    }
}
