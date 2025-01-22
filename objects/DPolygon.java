package objects;

import java.awt.Color;
import java.awt.Polygon;
import main.Calculator;

public class DPolygon {
    Color color;
    double[] x, y, z;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public DPolygon(double[] x, double[] y, double[] z, Color color) {
        Polygon P = new Polygon();
        // initialize a 3D polygon object       
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;

        createDPolygon();
    }

    public void createDPolygon() {
        double[] newX = new double[x.length]; 
        double[] newY = new double[y.length]; 

        // calculate new coordinates of all objects
        for (int i = 0; i < x.length; i++) {
            newX[i] = 200 * Calculator.CalculateObjectPositionX(main.Component.cameraPosition, main.Component.lookedPosition, x[i], y[i], z[i]);
            newY[i] = 200 * Calculator.CalculateObjectPositionX(main.Component.cameraPosition, main.Component.lookedPosition, x[i], y[i], z[i]);
        }

        main.Component.drawablePolygons[main.Component.numberOfPolygons] = new PolygonObject(newX, newY, color);
    }
}
