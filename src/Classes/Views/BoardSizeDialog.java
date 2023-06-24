package Classes.Views;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardSizeDialog extends JDialog {
    private static final int DEFAULT_WIDTH=250;
    private static final int DEFAULT_HEIGHT=200;
    JSlider rowSlider =new JSlider(9, 97, 9);
    JSlider columnSlider=new JSlider(9, 97, 9);
    JTextField totalSize=new JTextField(rowSlider.getValue()+"x"+columnSlider.getValue(), 20);
    JButton startGame=new JButton("Go Ahead!");
    JButton cancel=new JButton("Cancel");

    //JFrame owner?
    public BoardSizeDialog() {
        super((Frame) null, "Choose Board Size First", true);

        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        rowSlider.setPaintLabels(true);
        rowSlider.setMajorTickSpacing(8);
        rowSlider.setSnapToTicks(true);
        rowSlider.setName("rows");
        rowSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //JSlider source=(JSlider) e.getSource();
                totalSize.setText(rowSlider.getValue()+"x"+columnSlider.getValue());
            }
        });

        columnSlider.setPaintLabels(true);
        columnSlider.setMajorTickSpacing(8);
        columnSlider.setSnapToTicks(true);
        columnSlider.setName("columns");
        columnSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //JSlider source=(JSlider) e.getSource();
                totalSize.setText(rowSlider.getValue()+"x"+columnSlider.getValue());
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var frame = new StartMenuFrame();
                frame.setTitle("Daily lab");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                dispose();
            }
        });

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var frame = new FieldTableFrame(rowSlider.getValue(), columnSlider.getValue());
                frame.setTitle("Daily lab");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                dispose();
            }
        });
        JPanel mainPanel=new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        JPanel sliderR=new JPanel();
        JPanel sliderC=new JPanel();
        sliderR.add(rowSlider);
        sliderC.add(columnSlider);
        mainPanel.add(sliderR);
        mainPanel.add(sliderC);
        JPanel text=new JPanel();
        text.add(totalSize);
        mainPanel.add(text);
        JPanel buttons=new JPanel();
        buttons.add(cancel);
        buttons.add(startGame);
        mainPanel.add(buttons);
        add(mainPanel);
    }
}
