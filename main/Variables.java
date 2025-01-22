package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import static main.Main.*;

public class Variables {
    // program system
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static Dimension panelSize = window.getContentPane().getSize();
    public static int WIDTH = 960; // window's width
    public static int HEIGHT = 540; // window's height
    public static final int FPS = 2160; // frames per second
    public static final double TICK_INTERVAL = 1000000000 / FPS; // in nanosecond
    public static double lastRefresh = 0; // difference between the last tick and the current time

    // program mechanisms
    public static final double[] INITIAL_CAMERA_POSITION = new double[] { 10, 10, 10 };
    public static final double[] INITIAL_LOOKED_POSITION = new double[] { 1, 1, 1.5 };
    public static final int SENSITIVITY = FPS/70; // FPS: lowest, 1: highest

    // colors
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);
    public static final Color YELLOW = new Color(255, 255, 0);
    public static final Color CYAN = new Color(0, 255, 255);
    public static final Color PURPLE = new Color(255, 0, 255);

    // wallpaper
    public static String wallpaperPath = "/res/wallpapers/girl_view_earth_from_space.jpg";
    public static BufferedImage wallpaper;

    public static void setScreenSize() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static void setPanelSize() {
        screenSize = window.getContentPane().getSize();
    }

    public static Dimension getScreenSize() {
        return screenSize;
    }

    public static double getScreenWidth() {
        return screenSize.getWidth();
    }

    public static double getScreenHeight() {
        return screenSize.getHeight();
    }

    public static Dimension getPanelSize() {
        return panelSize;
    }    

    public static double getPanelWidth() {
        return panelSize.getWidth();
    }

    public static double getPanelHeight() {
        return panelSize.getHeight();
    }
}
