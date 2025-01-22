package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        // handle key typing (not commonly used for real-time input)
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("Key pressed: " + KeyEvent.getKeyText(keyCode));

        switch (keyCode) {
            case KeyEvent.VK_UP -> Component.cameraPosition[1]++; // Move up
            case KeyEvent.VK_DOWN -> Component.cameraPosition[1]--; // Move down
            case KeyEvent.VK_LEFT -> Component.cameraPosition[0]--; // Move left
            case KeyEvent.VK_RIGHT -> Component.cameraPosition[0]++; // Move right
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("Key released: " + KeyEvent.getKeyText(keyCode));
    }
}
