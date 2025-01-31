package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static main.Component.*;
import static main.Variables.SENSITIVITY;

public class Keyboard implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        // handle key typing (not commonly used for real-time input)
    }

    public static void control() {
        Vector viewVector = new Vector(lookedPosition[0] - cameraPosition[0], lookedPosition[1] - cameraPosition[1],
                lookedPosition[2] - cameraPosition[2]); // vector from the camera to the looked position
        Vector verticalVector = new Vector(0, 0, 1);
        Vector sideVector = viewVector.crossProduct(verticalVector);

        if (keys[0])
            cameraPosition[1] -= (double)(1) / SENSITIVITY;
        if (keys[1])
            cameraPosition[1] += (double)(1) / SENSITIVITY;
        if (keys[2])
            cameraPosition[0] -= (double)(1) / SENSITIVITY;
        if (keys[3])
            cameraPosition[0] += (double)(1) / SENSITIVITY;
        if (keys[4]) {
            cameraPosition[0] += viewVector.x / SENSITIVITY;
            cameraPosition[1] += viewVector.y / SENSITIVITY;
            cameraPosition[2] += viewVector.z / SENSITIVITY;
            lookedPosition[0] += viewVector.x / SENSITIVITY;
            lookedPosition[1] += viewVector.y / SENSITIVITY;
            lookedPosition[2] += viewVector.z / SENSITIVITY;
        }
        if (keys[5]) {
            cameraPosition[0] -= viewVector.x / SENSITIVITY;
            cameraPosition[1] -= viewVector.y / SENSITIVITY;
            cameraPosition[2] -= viewVector.z / SENSITIVITY;
            lookedPosition[0] -= viewVector.x / SENSITIVITY;
            lookedPosition[1] -= viewVector.y / SENSITIVITY;
            lookedPosition[2] -= viewVector.z / SENSITIVITY;
        }
        if (keys[6]) {
            cameraPosition[0] += sideVector.x / SENSITIVITY;
            cameraPosition[1] += sideVector.y / SENSITIVITY;
            cameraPosition[2] += sideVector.z / SENSITIVITY;
            lookedPosition[0] += sideVector.x / SENSITIVITY;
            lookedPosition[1] += sideVector.y / SENSITIVITY;
            lookedPosition[2] += sideVector.z / SENSITIVITY;
        }
        if (keys[7]) {
            cameraPosition[0] -= sideVector.x / SENSITIVITY;
            cameraPosition[1] -= sideVector.y / SENSITIVITY;
            cameraPosition[2] -= sideVector.z / SENSITIVITY;
            lookedPosition[0] -= sideVector.x / SENSITIVITY;
            lookedPosition[1] -= sideVector.y / SENSITIVITY;
            lookedPosition[2] -= sideVector.z / SENSITIVITY;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        // System.out.println("Key pressed: " + KeyEvent.getKeyText(keyCode));

        switch (keyCode) {
            case KeyEvent.VK_UP -> keys[0] = true; // Move up
            case KeyEvent.VK_DOWN -> keys[1] = true; // Move down
            case KeyEvent.VK_LEFT -> keys[2] = true; // Move left
            case KeyEvent.VK_RIGHT -> keys[3] = true; // Move right
            case KeyEvent.VK_W -> keys[4] = true; // Move the camera forward
            case KeyEvent.VK_S -> keys[5] = true; // Move the camera backward
            case KeyEvent.VK_A -> keys[6] = true; // Move the camera left
            case KeyEvent.VK_D -> keys[7] = true; // Move the camera right
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        // System.out.println("Key released: " + KeyEvent.getKeyText(keyCode));

        switch (keyCode) {
            case KeyEvent.VK_UP -> keys[0] = false; // Move up
            case KeyEvent.VK_DOWN -> keys[1] = false; // Move down
            case KeyEvent.VK_LEFT -> keys[2] = false; // Move left
            case KeyEvent.VK_RIGHT -> keys[3] = false; // Move right
            case KeyEvent.VK_W -> keys[4] = false; // Move up
            case KeyEvent.VK_S -> keys[5] = false; // Move down
            case KeyEvent.VK_A -> keys[6] = false; // Move left
            case KeyEvent.VK_D -> keys[7] = false; // Move right
        }
    }
}
