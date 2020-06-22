package CoreGraphicsRT.ObjectsRT;

import ColorRT.ColorARGB;
import CoreMathRT.MathVector;
import Types.PairDouble;
import Types.RealPoint3D;

public class Sphere extends Object {
    RealPoint3D center;
    private double radius;
    private double radSqr;

    //----------------------------------------------------------------------------------------
    public Sphere(RealPoint3D center, ColorARGB color, double radius) {
        this.center = center;
        this.color = color;
        this.radius = radius;
        radSqr = radius * radius;
    }

    public Sphere(RealPoint3D center, ColorARGB color, double radius, int specular) {
        this.center = center;
        this.color = color;
        this.radius = radius;
        this.specular = specular;
        this.reflective = 1;
        radSqr = radius * radius;
    }
    public Sphere(RealPoint3D center, ColorARGB color, double radius, double reflective) {
        this.center = center;
        this.color = color;
        this.radius = radius;
        this.reflective = reflective;
        radSqr = radius * radius;
    }

    public Sphere(RealPoint3D center, ColorARGB color, double radius, int specular, double reflective) {
        this.center = center;
        this.color = color;
        this.radius = radius;
        this.specular = specular;
        this.reflective = reflective;
        radSqr = radius * radius;
    }
    //----------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------
    @Override
    public PairDouble determineIntersection(RealPoint3D cameraPos, RealPoint3D direction, double INFINITY_T, double NEAR_T) {
        double t1;
        double t2;

        RealPoint3D cameraToSphere = MathVector.subVec(cameraPos, center);

        double k1 = MathVector.scalarMultVec(direction, direction);
        double k2 = 2 * MathVector.scalarMultVec(cameraToSphere, direction);
        double k3 = MathVector.scalarMultVec(cameraToSphere, cameraToSphere) - radSqr;

        double discriminant = k2 * k2 - 4 * k1 * k3;
        if (discriminant < 0) {
            return new PairDouble(INFINITY_T, INFINITY_T);
        }

        t1 = (-k2 + Math.sqrt(discriminant)) / (2 * k1);
        t2 = (-k2 - Math.sqrt(discriminant)) / (2 * k1);

        return new PairDouble(t1, t2);
    }

    @Override
    public RealPoint3D calculateNormal(RealPoint3D point) {
        RealPoint3D normalVec = MathVector.subVec(point, center);
        normalVec = MathVector.normVec(normalVec);
        return normalVec;
    }

    @Override
    public ColorARGB getColor(RealPoint3D point) {
        return color;
    }
    //----------------------------------------------------------------------------------------
}
