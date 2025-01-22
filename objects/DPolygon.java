package objects;

import java.awt.Color;
import main.Calculator;

public class DPolygon {
    Color color;
    double[] x, y, z;
    int polygon = 0;
    int multiplier = 50; // temporary variable

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public DPolygon(double[] x, double[] y, double[] z, Color color) {
        // initialize a 3D polygon object
        main.Component.numberOfDPolygons++;
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
            newX[i] = 800+ multiplier * Calculator.CalculateObjectPositionX(main.Component.cameraPosition,
                    main.Component.lookedPosition, x[i], y[i], z[i]);
            newY[i] = 800+ multiplier * Calculator.CalculateObjectPositionY(main.Component.cameraPosition,
                    main.Component.lookedPosition, x[i], y[i], z[i]);
        }

        polygon = main.Component.numberOfPolygons;
        main.Component.polygons[main.Component.numberOfPolygons] = new PolygonObject(newX, newY, color);
    }

    public void updateDPolygon() {

        double[] newX = new double[x.length];
        double[] newY = new double[y.length];

        // calculate new coordinates of all objects
        for (int i = 0; i < x.length; i++) {
            newX[i] = 800 + multiplier * Calculator.CalculateObjectPositionX(main.Component.cameraPosition,
                    main.Component.lookedPosition, x[i], y[i], z[i]);
            newY[i] = 800 + multiplier * Calculator.CalculateObjectPositionY(main.Component.cameraPosition,
                    main.Component.lookedPosition, x[i], y[i], z[i]);
        }

        main.Component.polygons[polygon] = new PolygonObject(newX, newY, color);
        main.Component.numberOfPolygons--;
    }
}
