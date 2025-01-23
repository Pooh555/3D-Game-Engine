package main;

public class Frustum {
    private double[] cameraPosition;
    private double[] lookedPosition;
    private double[][] nearPlaneCorners;
    private double[][] farPlaneCorners;

    public Frustum(double[] cameraPosition, double[] lookedPosition) {
        this.cameraPosition = cameraPosition;
        this.lookedPosition = lookedPosition;
    }

    public double[][] getNearPlaneCorners() {
        return nearPlaneCorners;
    }

    public double[][] getFarPlaneCorners() {
        return farPlaneCorners;
    }

    public double[] getCameraPosition() {
        return cameraPosition;
    }

    public double[] getLookedPosition() {
        return lookedPosition;
    }
}
