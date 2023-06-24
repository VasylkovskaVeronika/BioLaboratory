package Classes;

import Classes.CustomGraphicsObjects.CustomGraphicsIcon;

import javax.swing.*;
import java.awt.*;

public class LevelMessage extends JPanel {
    String phrase;
    String pathToIcon;
    Dimension size;

    public LevelMessage(int level) {
        boolean blackOn=false;
        switch (level) {
            case -1:
                phrase="You have been declassified.\n" +
                        "Things can go wrong sometimes. But, please, do always keep in mind: defeat is our own " +
                        "choice as well as reaching the top at much higher cost. Whatever you choose, you'll be" +
                        " right, however you'll regret in any case.";
                pathToIcon="Images/lostGame.png";
                blackOn=true;
                size=new Dimension(350, 300);
                break;
            case 2:
                phrase="Help me, please, mate! I'm Duke from the neighbouring biolaboratory. You've done your tasks " +
                        "so well, that the supreme goose promoted you to the second level! Congrats! I'm first level" +
                        "now, but waiting for a collaboration with anti-rashist mosquitoes. Anyway, we need your" +
                        "professional assistance at our lab. Would you help?";
              pathToIcon="Images/gusFriend.png";
              size=new Dimension(350, 350);
              break;
            case 3:
                phrase="The letter says: 'If somebody was here to save us... We are here, in the theatre... " +
                        "Building is almost ruined\n You're third level now and " +
                        "the supreme goose decided to give you this case. Would you help?";
                pathToIcon="Images/safeHomeBird.png";
                size=new Dimension(350, 300);
                break;
            case 4:
                phrase="Glory to you, savior! We're almost done with our mission, you've put a lot of " +
                        "effort in our result, thank you! You better know the costs of such a result... " +
                        "Anyway there is one step left. Are you ready?";
                pathToIcon="Images/gloryBird.png";
                size=new Dimension(350, 300);
                break;
            case 5:
                phrase="You're either a total volunteer, or a perfect bioweapon! Thank you, mate!";
                pathToIcon="Images/peaceBird.jpg";
                size=new Dimension(350, 200);
                break;
        }
        this.setSize(size);
        CustomGraphicsIcon icon=new CustomGraphicsIcon(pathToIcon);
        icon.setPreferredSize(new Dimension(90, 90));
        JTextArea textArea = new JTextArea(phrase);
        textArea.setSize(this.getWidth()-100, this.getHeight()-50);
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(10, 10, 10, 10));
        if(blackOn) {
            //FlowLayout experimentLayout = new FlowLayout();
            //this.setLayout(experimentLayout);
            icon.setOpaque(false);
            textArea.setForeground(Color.WHITE);
            textArea.setOpaque(false);
            add(icon, BorderLayout.NORTH);
            add(textArea, BorderLayout.SOUTH);
            //this.setComponentOrientation(ComponentOrientation.TOP);
            this.setBackground(Color.black);
            setVisible(true);
        }
        else {
            FlowLayout experimentLayout = new FlowLayout();
            this.setLayout(experimentLayout);
            add(textArea);
            add(icon);
            this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        }
        setVisible(true);
    }
}
