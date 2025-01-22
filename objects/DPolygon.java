package objects;

import java.awt.Color;
import main.Calculator;

public class DPolygon {
    Color color;
    double[] x, y, z;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public DPolygon(double[] x, double[] y, double[] z, Color color) {
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
            newX[i] = 200 * Calculator.CalculateObjectPositionX(main.Component.cameraPosition,
                    main.Component.lookedPosition, x[i], y[i], z[i]);
            newY[i] = 200 * Calculator.CalculateObjectPositionY(main.Component.cameraPosition,
                    main.Component.lookedPosition, x[i], y[i], z[i]);
        }

        // debugging
        System.out.println("3D Points:");
        for (int i = 0; i < x.length; i++) {
            System.out.println("x: " + x[i] + ", y: " + y[i] + ", z: " + z[i]);
        }
        System.out.println("Projected 2D Points:");
        for (int i = 0; i < x.length; i++) {
            System.out.println("newX: " + newX[i] + ", newY: " + newY[i]);
        }

        main.Component.drawablePolygons[main.Component.numberOfPolygons] = new PolygonObject(newX, newY, color);
    }
}
