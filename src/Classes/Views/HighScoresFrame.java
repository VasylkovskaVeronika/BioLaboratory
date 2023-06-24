package Classes.Views;

import Classes.CustomSerializeDeserialize;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HighScoresFrame extends JFrame {
JList scoresList=new JList();
HighScoresFrame() {
        CustomSerializeDeserialize.PlayerStat[] ps = CustomSerializeDeserialize.deserialize();
        if (ps.length > 0) {
            CustomSerializeDeserialize.PlayerStat sel=ps[ps.length-1];
            List<CustomSerializeDeserialize.PlayerStat> lp= Arrays.stream(ps).
                    sorted(Comparator.comparingInt(p -> -p.getSavedLives())).collect(Collectors.toList());
            scoresList = new JList(lp.toArray());
            scoresList.setSelectedValue(sel, true);
            System.out.println("File is read successfully");
        }
    this.setSize(500, 300);
  this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    JScrollPane sp=new JScrollPane(scoresList);
    add(sp, BorderLayout.CENTER);
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
