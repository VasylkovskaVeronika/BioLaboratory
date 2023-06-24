package Classes.Models;

import javax.swing.*;
import java.awt.*;

public class CustomCellModel extends CellRendererPane {
    JPanel content =new JPanel();

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
    }


    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }


    @Override
    public Component add(Component comp) {
        this.content=(JPanel)comp;
        return this.content;
    }


    public JPanel getContent() {
        return content;
    }
}
