package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import objects.DPolygon;
import objects.PolygonObject;

public class Component extends JPanel implements Runnable {
    // program system
    public static Dimension windowSize = new Dimension(Variables.WIDTH, Variables.HEIGHT); // window size
    Thread mainThread; // main game thread

    // devices
    Mouse mouse = new Mouse(); // new mouse instance
    Keyboard keyboard = new Keyboard(); // new keyboard instance

    // program mechanism
    public static double[] cameraPosition = Variables.INITIAL_CAMERA_POSITION;
    public static double[] lookedPosition = Variables.INITIAL_LOOKED_POSITION;

    // program objects
    public static int numberOfPolygons = 0; // number of polygons
    public static PolygonObject[] drawablePolygons = new PolygonObject[100]; // arrday of current polygons
    private static PolygonObject polygon1; // 2D polygon object
    private static DPolygon dPolygon1; // 3D polygon object
    private static DPolygon dPolygon2 = new DPolygon(new double[] { 2, 4, 2 }, new double[] { 2, 4, 6 },
            new double[] { 5, 5, 5 }, Variables.RED);

    @Override
    public void run() {
        // main program loop
        long previousTickTime = System.nanoTime(); // time of the last tick
        long currentTime; // current time

        while (mainThread != null) {
            currentTime = System.nanoTime();

            Variables.lastRefresh += (currentTime - previousTickTime) / Variables.TICK_INTERVAL;
            previousTickTime = currentTime;

            if (Variables.lastRefresh >= 1) {
                repaint();
                Variables.lastRefresh--;
            }
        }
    }

    public void launch() {
        mainThread = new Thread(this); // initiate a new thread
        mainThread.start(); // calls the run() method
    }

    public Component() {
        setPreferredSize(windowSize); 
        setBackground(Variables.BLACK);
        System.out.println("Components are set.");

        addMouseMotionListener(mouse);
        addMouseListener(mouse);
        System.out.println("A mouse is detected.");

        addKeyListener(keyboard);
        setFocusable(true); 
        requestFocusInWindow(); 
        System.out.println("A keyboard is detected.");
    }

    public void CreatePolygonObject() {
        polygon1 = new PolygonObject(new double[] { 10, 200, 10 }, new double[] { 5, 5, 5 }, Variables.RED);

        repaint();
        System.out.println("New 2D polygon is created.");
    }

    public void CreateDPolygon() {
        dPolygon1 = new DPolygon(new double[] { 2, 4, 2 }, new double[] { 2, 4, 6 }, new double[] { 10, 200, 400 },
                Variables.RED);

        repaint();
        System.out.println("New 3D polygon is created.");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dPolygon2.updateDPolygon();
        g.setColor(Variables.GREEN);
        g.drawString(System.nanoTime() + "", 20, 20);

        // System.out.println("Number of polygons: " + numberOfPolygons);

        for (int i = 0; i < numberOfPolygons; i++) {
            drawablePolygons[i].drawPolygonObject(g);
        }
    }

}