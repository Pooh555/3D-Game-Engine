package main;

public class Calculator {
    private static double drawX = 0, drawY = 0;

    public static double CalculateObjectPositionX(double[] cameraPosition, double[] lookedPosition, double x, double y, double z) {
        setValues(cameraPosition, lookedPosition, x, y, z);
        
        return drawX;
    }

    public static double CalculateObjectPositionY(double[] cameraPosition, double[] lookedPosition, double x, double y, double z) {
        setValues(cameraPosition, lookedPosition, x, y, z);

        return drawY;
    }

    public boolean isOnFrustum(double x, double y, double z) {
        return false;
    }

    public static void setValues(double[] cameraPosition, double[] lookedPosition, double x, double y, double z) {
        Vector viewToPoint = new Vector(x - cameraPosition[0], y - cameraPosition[1], z - cameraPosition[2]); // vector from the camera to the 3D point to project
        Vector viewVector = new Vector(lookedPosition[0] - cameraPosition[0], lookedPosition[1] - cameraPosition[1], lookedPosition[2] - cameraPosition[2]); // vector from the camera to the looked position
        Vector rotationVector = getRotationVector(cameraPosition, lookedPosition); // vector that compensates for the camera's orientation
        Vector abstractVector1 = viewVector.crossProduct(rotationVector); // vector that is perpendicular to both view vector and rotation vector and lies in the screen plane
        Vector abstractVector2 = viewVector.crossProduct(abstractVector1); // perpendicular to viewVector and orthogonal to abstractVector1
        
        // computing projections point
        double t = ((viewVector.x * lookedPosition[0] + viewVector.y * lookedPosition[1] + viewVector.z * lookedPosition[2])
                    - (viewVector.x * cameraPosition[0] + viewVector.y * cameraPosition[1] + viewVector.z * cameraPosition[2]))
                    / (viewVector.x * viewToPoint.x + viewVector.y * viewToPoint.y + viewVector.z * viewToPoint.z); // parameter used to compute the intersection point between viewVector and viewToPoint (how far away the object's plane is to the camera)
    
        // transforming 3D point into a 2D point
        x = cameraPosition[0] + viewToPoint.x * t;
        y = cameraPosition[1] + viewToPoint.y * t;
        z = cameraPosition[2] + viewToPoint.z * t;

        /* calculating the 2D coordinates
         * t > 0: the point is in front of the camera
         * t < 0: the point is behind the camera
         */        
        if (t > 0) {
            // placing places the 3D point in the 2D screen space relative to the camera's perspective
            drawX = abstractVector2.x * x + abstractVector2.y * y + abstractVector2.z * z;
            drawY = abstractVector1.x * x + abstractVector1.y * y + abstractVector1.z * z;
        }
    }

    public static Vector getRotationVector(double[] cameraPosition, double[] lookedPosition) {
        // relative x and y distances (dx and dy) between the camera position and the point the camera is looking at.
        double dx = Math.abs(cameraPosition[0] - lookedPosition[0]);
        double dy = Math.abs(cameraPosition[1] - lookedPosition[1]);
        double xRotation, yRotation;
        xRotation = dy / (dx + dy);
        yRotation = dx / (dx + dy);

        if (cameraPosition[1] > lookedPosition[1])
            xRotation = -xRotation;
        if (cameraPosition[0] < lookedPosition[0])
            yRotation = -yRotation;

        Vector v = new Vector(xRotation, yRotation, 0);

        return v;
    }
}
