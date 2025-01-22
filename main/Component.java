package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import objects.DPolygon;
import objects.PolygonObject;

public class Component extends JPanel {
    // game system
    public static Dimension windowSize = new Dimension(Variables.WIDTH, Variables.HEIGHT); // window size
    
    // game mechanism
    public static double[] cameraPosition = new double[]{10, 10, 10};
    public static double[] lookedPosition = new double[]{5, 0, 0};
    
    // game objects
    public static int numberOfPolygons = 0; // number of polygons
    public static PolygonObject[] drawablePolygons = new PolygonObject[100]; // array of current polygons
    private static PolygonObject polygon1; // 2D polygon object
    private static DPolygon dPolygon1; // 3D polygon object
    private static DPolygon dPolygon2 = new DPolygon(new double[]{2, 4, 2}, new double[]{2, 4, 6}, new double[]{5, 5, 5}, Variables.RED);

    public Component() {
        setPreferredSize(windowSize); // set initial window size
        setBackground(Variables.BLACK); // set initial window background color
        System.out.println("Components are set.");
    }

    public void CreatePolygonObject() {
        polygon1 = new PolygonObject(new double[]{10, 200, 10}, new double[]{5, 5, 5}, Variables.RED);
        
        repaint();
        System.out.println("New 2D polygon is created.");
    }
    
    public void CreateDPolygon() {
        dPolygon1 = new DPolygon(new double[]{2, 4, 2}, new double[]{2, 4, 6}, new double[]{10, 200, 400}, Variables.RED);
    
        repaint();
        System.out.println("New 3D polygon is created.");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // g.setColor(Variables.RED); // set the color for the oval
        // g.fillOval(10, 10, 500, 500); // draw the oval
        // CreatePolygonObject();
        // polygon1.drawPolygonObject(g); // draw 2D polygon objecty
        
        System.out.println("Number of polygons: " + numberOfPolygons);

        for (int i = 0; i < numberOfPolygons; i++) {
            drawablePolygons[i].drawPolygonObject(g);
        }
    }

}