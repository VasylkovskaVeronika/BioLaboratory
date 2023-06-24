package com.company;

import Classes.Views.StartMenuFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            var frame = new StartMenuFrame();
            frame.setTitle("Daily lab");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
