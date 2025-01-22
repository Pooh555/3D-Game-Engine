package main;

public class Calculator {
    static double drawX, drawY; // for testing purposes

    public static double CalculateObjectPositionX(double[] cameraPosition, double[] lookedPosition, double x, double y, double z) {
        return drawX;
    }

    public static double CalculateObjectPositionY(double[] cameraPosition, double[] lookedPosition, double x, double y, double z) {
        setValues(cameraPosition, lookedPosition, x, y, z);

        return drawY;
    }

    public static void setValues(double[] cameraPosition, double[] lookedPosition, double x, double y, double z) {
        Vector viewVector = new Vector(lookedPosition[0] - cameraPosition[0], lookedPosition[1] - cameraPosition[1], lookedPosition[2] - cameraPosition[2]);
        Vector directionVector = new Vector(1, 1, 1); // direction vector
        Vector viewToPoint = new Vector(x - cameraPosition[0], y - cameraPosition[1], z - cameraPosition[2]);
        Vector planeVector1 = viewVector.crossProduct(directionVector); // first plane vector
        Vector planeVector2 = viewVector.crossProduct(planeVector1); // second plane vector
        
        double t = ((viewVector.x * lookedPosition[0] + viewVector.y * lookedPosition[1] + viewVector.z * lookedPosition[2])
                    - (viewVector.x * cameraPosition[0] + viewVector.y * cameraPosition[1] + viewVector.z * cameraPosition[2]))
                    / (viewVector.x * viewToPoint.x + viewVector.y * viewToPoint.y + viewVector.z * viewToPoint.z);
    
        x = cameraPosition[0] + viewToPoint.x * t;
        y = cameraPosition[1] + viewToPoint.y * t;
        z = cameraPosition[2] + viewToPoint.z * t;

        if (t >= 0) {
            drawX = planeVector2.x * x + planeVector2.y * y + planeVector2.z * z;
            drawY = planeVector1.x * x + planeVector1.y * y + planeVector1.z * z;
        }
    }
}
