package objects;

import java.awt.Color;
import static main.Calculator.*;
import static main.Component.*;
import static main.Variables.*;

public class DPolygon {
    Color color;
    double[] x, y, z;
    int polygon = 0;
    int scaleMultiplier = 50; // temporary variable

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public DPolygon(double[] x, double[] y, double[] z, Color color) {
        // initialize a 3D polygon object
        numberOfDPolygons++;
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;

        createDPolygon();
    }

    public void createDPolygon() {
        polygon = numberOfPolygons;
        polygons[numberOfPolygons] = new PolygonObject(new double[] {}, new double[] {},
                color);
        polygons[polygon].averageDistance = getDistance();

        updateDPolygon();
    }

    public void updateDPolygon() {
        double dx = - scaleMultiplier * CalculateObjectPositionX(cameraPosition, lookedPosition, lookedPosition[0],
                lookedPosition[1], lookedPosition[2]);
        double dy = - scaleMultiplier * CalculateObjectPositionY(cameraPosition, lookedPosition, lookedPosition[0],
                lookedPosition[1], lookedPosition[2]);
        double[] newX = new double[x.length];
        double[] newY = new double[y.length];

        // calculate new coordinates of all objects (fix the center of rotation)
        for (int i = 0; i < x.length; i++) {
            newX[i] = dx + (int) (getPanelWidth() / 2)
                    + scaleMultiplier * CalculateObjectPositionX(cameraPosition,
                            lookedPosition, x[i], y[i], z[i]);
            newY[i] = dy + (int) (getPanelHeight() / 2)
                    + scaleMultiplier * CalculateObjectPositionY(cameraPosition,
                            lookedPosition, x[i], y[i], z[i]);
        }

        // the total number of polygons remain unchanged
        polygons[polygon] = new PolygonObject(newX, newY, color);
        polygons[polygon].averageDistance = getDistance();
        numberOfPolygons--;
    }

    public double getDistance() {
        double total = 0;

        for (int i = 0; i < x.length; i++) {
            total += getDistanceToPoint(i);
        }

        return total / x.length;
    }

    private double getDistanceToPoint(int i) {
        return Math.sqrt((cameraPosition[0] - x[i]) * (cameraPosition[0] - x[i])
                + (cameraPosition[1] - y[i]) * (cameraPosition[1] - y[i])
                + (cameraPosition[2] - z[i]) * (cameraPosition[2] - z[i]));
    }
}
