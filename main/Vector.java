package main;

public class Vector {
    double x = 0, y = 0, z = 0;

    public Vector(double x, double y, double z) {
        double length = Math.sqrt(x * x + y * y + z * z); // find vector length

        // prevent division by 0
        if (length > 0) {
            this.x = x / length;
            this.y = y / length;
            this.z = z / length;
        }
    }

    public Vector crossProduct(Vector v) {
        return new Vector(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
    }

    public double dotProduct(Vector v) {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }    

    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }
}
