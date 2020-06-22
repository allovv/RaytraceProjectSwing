package CoreMathRT;

import ErrorRT.ErrorManager;
import Types.RealPoint3D;

public class MathVector {
    public static double scalarMultVec(RealPoint3D vec1, RealPoint3D vec2) {
        return (vec1.x * vec2.x) + (vec1.y * vec2.y) + (vec1.z * vec2.z);
    }

    public static RealPoint3D vectorMultVec(RealPoint3D a, RealPoint3D b) {
        return new RealPoint3D(a.y * b.z - a.z * b.y, -(a.x * b.z - a.z * b.x), a.x * b.y - a.y * b.x);
    }

    //-------------------------------------------------------------------------------------

    // vec / |vec|
    public static RealPoint3D normVec(RealPoint3D vector) {
        double vectorLen = lengthVec(vector);
        RealPoint3D vec = new RealPoint3D();
        try {
            vec.x = vector.x / vectorLen;
            vec.y = vector.y / vectorLen;
            vec.z = vector.z / vectorLen;
        } catch (Exception e) {
            ErrorManager.printError("ERROR: деление на ноль");
        }
        return vec;
    }

    // |vec|
    public static double lengthVec(RealPoint3D vec) {
        return Math.sqrt(vec.x * vec.x + vec.y * vec.y + vec.z * vec.z);
    }

    // vec * -1
    public static RealPoint3D reverseVec(RealPoint3D vec) {
        return new RealPoint3D(-vec.x, -vec.y, -vec.z);
    }

    //-------------------------------------------------------------------------------------

    // vec * scalar
    public static RealPoint3D scalingVec(RealPoint3D vec, double scalar) {
        return new RealPoint3D(vec.x * scalar, vec.y * scalar, vec.z * scalar);
    }

    // vec1 + vec2
    public static RealPoint3D addVec(RealPoint3D vec1, RealPoint3D vec2) {
        return new RealPoint3D(vec1.x + vec2.x, vec1.y + vec2.y, vec1.z + vec2.z);
    }

    // vec1 - vec2
    public static RealPoint3D subVec(RealPoint3D vec1, RealPoint3D vec2) {
        return new RealPoint3D(vec1.x - vec2.x, vec1.y - vec2.y, vec1.z - vec2.z);
    }
}
