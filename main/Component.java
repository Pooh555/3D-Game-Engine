package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Arrays;
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
    public static int numberOfPolygons = 0, numberOfDPolygons = 0; // number of polygons
    public static PolygonObject[] polygons = new PolygonObject[100]; // arrday of current 2D polygons
    public static DPolygon[] DPolygons = new DPolygon[100]; // arrday of current 3D polygons
    private static PolygonObject polygon1; // 2D polygon object
    private static DPolygon DPolygon1; // 3D polygon object
    private static int[] newOrder; // layers of objects with respect to the camera vision

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

        // test: cube
        DPolygons[numberOfDPolygons] = new DPolygon(new double[] { 0, 2, 2, 0 }, new double[] { 0, 0, 2, 2 },
                new double[] { 0, 0, 0, 0 }, Variables.RED);
        DPolygons[numberOfDPolygons] = new DPolygon(new double[] { 0, 2, 2, 0 }, new double[] { 0, 0, 2, 2 },
                new double[] { 2, 2, 2, 2 }, Variables.RED);
        DPolygons[numberOfDPolygons] = new DPolygon(new double[] { 0, 2, 2, 0 }, new double[] { 0, 0, 0, 0 },
                new double[] { 0, 0, 2, 2 }, Variables.GREEN);
        DPolygons[numberOfDPolygons] = new DPolygon(new double[] { 2, 0, 0, 2 }, new double[] { 2, 2, 2, 2 },
                new double[] { 0, 0, 2, 2 }, Variables.GREEN);
        DPolygons[numberOfDPolygons] = new DPolygon(new double[] { 0, 0, 0, 0 }, new double[] { 0, 2, 2, 0 },
                new double[] { 0, 0, 2, 2 }, Variables.BLUE);
        DPolygons[numberOfDPolygons] = new DPolygon(new double[] { 2, 2, 2, 2 }, new double[] { 0, 2, 2, 0 },
                new double[] { 0, 0, 2, 2 }, Variables.BLUE);
    }

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

    public void CreatePolygon() {
        polygon1 = new PolygonObject(new double[] { 10, 200, 10 }, new double[] { 5, 5, 5 }, Variables.RED);

        repaint();
        System.out.println("New 2D polygon is created.");
    }

    public void CreateDPolygon() {
        DPolygon1 = new DPolygon(new double[] { 2, 4, 2 }, new double[] { 2, 4, 6 }, new double[] { 10, 200, 400 },
                Variables.RED);

        repaint();
        System.out.println("New 3D polygon is created.");
    }

    private void setOrder() {
        double[] k = new double[numberOfPolygons];
        newOrder = new int[numberOfPolygons];
        Integer[] indices = new Integer[numberOfPolygons];

        // fill k and newOrder arrays
        for (int i = 0; i < numberOfPolygons; i++) {
            k[i] = polygons[i].averageDistance;
            newOrder[i] = i;
        }

        // create an array of indices and sort it based on k values
        for (int i = 0; i < numberOfPolygons; i++) {
            indices[i] = i;
        }

        // sort indices based on k values using a lambda comparator
        Arrays.sort(indices, (a, b) -> Double.compare(k[b], k[a]));

        // update newOrder with sorted indices
        for (int i = 0; i < numberOfPolygons; i++) {
            newOrder[i] = indices[i];
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        DPolygons[0].updateDPolygon();
        g.setColor(Variables.GREEN);
        g.drawString(System.nanoTime() + "", 20, 20);

        // System.out.println("Number of polygons: " + numberOfPolygons);

        for (int i = 0; i < numberOfDPolygons; i++) {
            DPolygons[i].updateDPolygon();
        }

        setOrder();
        
        for (int i = 0; i < numberOfPolygons; i++) {
            polygons[newOrder[i]].drawPolygon(g);
        }
    }

}