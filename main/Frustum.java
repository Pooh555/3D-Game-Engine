package main;

public class Frustum {
    private double[] cameraPosition;
    private double[] lookedPosition;

    public Frustum(double[] cameraPosition, double[] lookedPosition) {
        this.cameraPosition = cameraPosition;
        this.lookedPosition = lookedPosition;
    }

    public double[] getCameraPosition() {
        return cameraPosition;
    }

    public double[] lookedPosition() {
        return lookedPosition;
    }
}