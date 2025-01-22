package main;

import javax.swing.JFrame;

public class Main {
    static JFrame window = new JFrame("3D engine");
    
    public static void main(String[] args) {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close the program when the window is closed
        window.setResizable(true); // window is resizable

        Component content = new Component(); // initialize a new Component

        window.add(content); // add the Component to the window
        window.pack(); // adjust the window size to fit the content
        window.setLocationRelativeTo(null); // center the window on the screen
        window.setVisible(true); // make the window visible
    }
}
