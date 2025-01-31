package main;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import static main.Variables.*;
import objects.DPolygon;
import objects.PolygonObject;

public class Component extends JPanel implements Runnable {
    // program system
    public static Dimension windowSize = new Dimension(Variables.INITIAL_WIDTH, Variables.INITIAL_HEIGHT); // window size
    Thread mainThread; // main game thread

    // devices
    Mouse mouse = new Mouse(); // new mouse instance
    Keyboard keyboard = new Keyboard(); // new keyboard instance

    // program mechanism
    public static double[] cameraPosition = INITIAL_CAMERA_POSITION;
    public static double[] lookedPosition = INITIAL_LOOKED_POSITION;
    public static boolean[] keys = new boolean[10];

    // program objects
    public static int numberOfPolygons = 0, numberOfDPolygons = 0; // number of polygons
    public static PolygonObject[] polygons = new PolygonObject[16777216]; // arrday of current 2D polygons
    public static DPolygon[] DPolygons = new DPolygon[16777216]; // arrday of current 3D polygons
    private static int[] newOrder; // layers of objects with respect to the camera vision

    public Component() {
        setPreferredSize(windowSize);
        setBackground(BLACK);
        System.out.println("Components are set.");

        addMouseMotionListener(mouse);
        addMouseListener(mouse);
        System.out.println("A mouse is detected.");

        addKeyListener(keyboard);
        setFocusable(true);
        requestFocusInWindow();
        System.out.println("A keyboard is detected.");

        // load background image
        try (InputStream input = getClass().getResourceAsStream(wallpaperPath)) {
            if (input == null)
                throw new IllegalArgumentException("Background image not found at " + wallpaperPath);

            wallpaper = ImageIO.read(input);

            System.out.println("Wallpaper is loaded successfully");
        } catch (Exception e) {
            System.err.println("Failed to load background image: " + e.getMessage());
        }

        // test: rectangular prism
        // DPolygons[numberOfDPolygons] = new DPolygon(new double[] { 0, 2, 2, 0 }, new double[] { 0, 0, 3, 3 },
        //         new double[] { 0, 0, 0, 0 }, RED);
        // DPolygons[numberOfDPolygons] = new DPolygon(new double[] { 0, 2, 2, 0 }, new double[] { 0, 0, 3, 3 },
        //         new double[] { 2, 2, 2, 2 }, RED);
        // DPolygons[numberOfDPolygons] = new DPolygon(new double[] { 0, 2, 2, 0 }, new double[] { 0, 0, 0, 0 },
        //         new double[] { 0, 0, 2, 2 }, GREEN);
        // DPolygons[numberOfDPolygons] = new DPolygon(new double[] { 2, 0, 0, 2 }, new double[] { 3, 3, 3, 3 },
        //         new double[] { 0, 0, 2, 2 }, GREEN);
        // DPolygons[numberOfDPolygons] = new DPolygon(new double[] { 0, 0, 0, 0 }, new double[] { 0, 3, 3, 0 },
        //         new double[] { 0, 0, 2, 2 }, BLUE);
        // DPolygons[numberOfDPolygons] = new DPolygon(new double[] { 2, 2, 2, 2 }, new double[] { 0, 3, 3, 0 },
        //         new double[] { 0, 0, 2, 2 }, BLUE);

        // test: cube
        DPolygons[numberOfDPolygons] = new DPolygon(new double[] { 0, 2, 2, 0 }, new double[] { 0, 0, 2, 2 },
                new double[] { 0, 0, 0, 0 }, RED);
        DPolygons[numberOfDPolygons] = new DPolygon(new double[] { 0, 2, 2, 0 }, new double[] { 0, 0, 2, 2 },
                new double[] { 2, 2, 2, 2 }, RED);
        DPolygons[numberOfDPolygons] = new DPolygon(new double[] { 0, 2, 2, 0 }, new double[] { 0, 0, 0, 0 },
                new double[] { 0, 0, 2, 2 }, GREEN);
        DPolygons[numberOfDPolygons] = new DPolygon(new double[] { 2, 0, 0, 2 }, new double[] { 2, 2, 2, 2 },
                new double[] { 0, 0, 2, 2 }, GREEN);
        DPolygons[numberOfDPolygons] = new DPolygon(new double[] { 0, 0, 0, 0 }, new double[] { 0, 2, 2, 0 },
                new double[] { 0, 0, 2, 2 }, BLUE);
        DPolygons[numberOfDPolygons] = new DPolygon(new double[] { 2, 2, 2, 2 }, new double[] { 0, 2, 2, 0 },
                new double[] { 0, 0, 2, 2 }, BLUE);

        // temporary platform
        for (int h = -2; h <= 0; h++)
            for (int i = -6; i < 6; i++) 
                for (int j = -6; j < 6; j++) {
                    DPolygons[numberOfDPolygons] = new DPolygon(new double[] { i, i, i + 1, i + 1 }, new double[] { j, j + 1, j + 1, j },
                    new double[] { h, h, h, h }, KELLY_GREEN); // top side
                    DPolygons[numberOfDPolygons] = new DPolygon(new double[] { i, i, i + 1, i + 1 }, new double[] { j, j + 1, j + 1, j },
                    new double[] { h - 1, h - 1, h - 1, h - 1 }, DARK_BROWN); // bottom side
                    DPolygons[numberOfDPolygons] = new DPolygon(new double[] { i + 1, i + 1, i + 1, i + 1 }, new double[] { j, j + 1, j + 1, j },
                    new double[] { h - 1, h - 1, h, h }, DARK_BROWN); // front side
                    DPolygons[numberOfDPolygons] = new DPolygon(new double[] { i, i, i , i }, new double[] { j, j + 1, j + 1, j },
                    new double[] { h - 1, h - 1, h, h }, DARK_BROWN); // back side
                    DPolygons[numberOfDPolygons] = new DPolygon(new double[] { i, i + 1, i + 1 , i }, new double[] { j + 1, j + 1, j + 1, j + 1 },
                    new double[] { h - 1, h - 1, h, h }, DARK_BROWN); // left side
                    DPolygons[numberOfDPolygons] = new DPolygon(new double[] { i, i + 1, i + 1 , i }, new double[] { j, j, j, j },
                    new double[] { h - 1, h - 1, h, h }, DARK_BROWN); // right side
                }
    }

    @Override
    public void run() {
        // main program loop
        long previousTickTime = System.nanoTime(); // time of the last tick
        long currentTime; // current time

        while (mainThread != null) {
            currentTime = System.nanoTime();

            lastRefresh += (currentTime - previousTickTime) / TICK_INTERVAL;
            previousTickTime = currentTime;

            if (lastRefresh >= 1) {
                repaint();
                lastRefresh--;
            }
        }
    }

    public void launch() {
        mainThread = new Thread(this); // initiate a new thread
        mainThread.start(); // calls the run() method
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
        super.paintComponent(g); // Always call super.paintComponent first

        Keyboard.control();

        drawResizedImage(g, wallpaper);

        DPolygons[0].updateDPolygon();

        // System.out.println("Number of polygons: " + numberOfPolygons);
        
        for (int i = 0; i < numberOfDPolygons; i++) {
            DPolygons[i].updateDPolygon();
        }

        setOrder();
        for (int i = 0; i < numberOfPolygons; i++) {
            polygons[newOrder[i]].drawPolygon(g);
        }
    }   

    private void drawResizedImage(Graphics g, BufferedImage image) {
        // Update screen size to ensure it is current
        setPanelSize();
    
        // Original image size
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        double panelAspect = (double) getPanelWidth() / getPanelHeight();
        double imageAspect = (double) imageWidth / imageHeight;
    
        int newWidth, newHeight;
    
        if (panelAspect > imageAspect) {
            // Panel is wider than the image, so scale the width to match the window's width
            newWidth = (int) (getPanelWidth());
            newHeight = (int) (imageHeight * ((double) newWidth / imageWidth));
        } else {
            // Panel is taller than the image, so scale the height to match the window's height
            newHeight = (int) (getPanelHeight());
            newWidth = (int) (imageWidth * ((double) newHeight / imageHeight));
        }
    
        // cast Graphics to Graphics2D to enable advanced drawing options
        Graphics2D g2d = (Graphics2D) g;
    
        // original composite
        java.awt.Composite originalComposite = g2d.getComposite();
    
        // set the opacity (dim level, 0.0f = fully transparent, 1.0f = fully opaque)
        float opacity = 0.25f;

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2d.drawImage(image, 0, 0, newWidth, newHeight, this);
        g2d.setComposite(originalComposite);
    }
}