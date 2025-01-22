package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Component extends JPanel {
    public static Dimension windowSize = new Dimension(Variables.WIDTH, Variables.HEIGHT); // window size

    public Component() {
        setPreferredSize(windowSize); // set initial window size
        setBackground(Variables.BLACK); // set initial window background color
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Variables.RED); // set the color for the oval
        g.fillOval(10, 10, 500, 500); // draw the oval
    }

}