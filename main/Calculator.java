package main;

public class Calculator {
    static double drawX = 0, drawY = 0; // for display purposes

    public static double CalculateObjectPositionX(double[] cameraPosition, double[] lookedPosition, double x, double y, double z) {
        return drawX;
    }

    public static double CalculateObjectPositionY(double[] cameraPosition, double[] lookedPosition, double x, double y, double z) {
        setValues(cameraPosition, lookedPosition, x, y, z);

        return drawY;
    }

    public static void setValues(double[] cameraPosition, double[] lookedPosition, double x, double y, double z) {
        Vector viewVector = new Vector(lookedPosition[0] - cameraPosition[0], lookedPosition[1] - cameraPosition[1], lookedPosition[2] - cameraPosition[2]); // vector from the camera to the looked position
        Vector directionVector = new Vector(1, 1, 1); // direction vector: for projecting onto a plane
        Vector viewToPoint = new Vector(x - cameraPosition[0], y - cameraPosition[1], z - cameraPosition[2]); // vector from the camera to the 3D point to project
        Vector planeVector1 = viewVector.crossProduct(directionVector); // first plane vector
        Vector planeVector2 = viewVector.crossProduct(planeVector1); // second plane vector
        
        // computing projections point
        double t = ((viewVector.x * lookedPosition[0] + viewVector.y * lookedPosition[1] + viewVector.z * lookedPosition[2])
                    - (viewVector.x * cameraPosition[0] + viewVector.y * cameraPosition[1] + viewVector.z * cameraPosition[2]))
                    / (viewVector.x * viewToPoint.x + viewVector.y * viewToPoint.y + viewVector.z * viewToPoint.z); // parameter used to compute the intersection point between viewVector and viewToPoint
    
        // transforming 3D point into a 2D point
        x = cameraPosition[0] + viewToPoint.x * t;
        y = cameraPosition[1] + viewToPoint.y * t;
        z = cameraPosition[2] + viewToPoint.z * t;

        /* calculating the 2D coordinates
         * t > 0: the point is in front of the camera
         * t < 0: the point is behind the camera
         */
        if (t >= 0) {
            drawX = planeVector2.x * x + planeVector2.y * y + planeVector2.z * z;
            drawY = planeVector1.x * x + planeVector1.y * y + planeVector1.z * z;
        }
    }
}
