package CoreGraphicsRT.ObjectsRT;

import ColorRT.ColorARGB;
import Types.PairDouble;
import Types.RealPoint3D;

//абстрактный класс
public abstract class Object {
    public static final int SPEC_DEFAULT = 10;
    public static final double REFLECT_DEFAULT = 1; //TODO: по умолчанию все объекты зеркальные (кроме плоскости)

    protected ColorARGB color;
    protected int specular = SPEC_DEFAULT; //диапазон [1, inf] (specular)
    protected double reflective = REFLECT_DEFAULT ; //диапазон [0, 1] (reflective)

    //----------------------------------------------------------------------------------------
    public int getSpecular() { return specular; }
    public double getReflective() { return reflective; }
    public abstract ColorARGB getColor(RealPoint3D point);
    //----------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------
    public abstract PairDouble determineIntersection(RealPoint3D cameraPos, RealPoint3D direction, double NEAR_T, double INFINITY_T);
    public abstract RealPoint3D calculateNormal(RealPoint3D point);
    //----------------------------------------------------------------------------------------
}
