package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class PolygonObject {
    public Polygon P = new Polygon();
    public Color color;
    public double averageDistance = 0;

    public PolygonObject(double[] x, double[] y, Color color) {
        // initialize a polygon object
        for (int i = 0; i < x.length; i++) 
            P.addPoint((int)(x[i]), (int)(y[i]));
        
        this.color = color;

        main.Component.numberOfPolygons++;
    }

    public void drawPolygon(Graphics g) {
        g.setColor(color); // set the polygon color
        g.fillPolygon(P);
        g.setColor(main.Variables.WHITE);
        g.drawPolygon(P); // draw the polygon object
    }
}
