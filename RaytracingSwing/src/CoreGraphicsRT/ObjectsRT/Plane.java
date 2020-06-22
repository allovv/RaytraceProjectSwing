package CoreGraphicsRT.ObjectsRT;

import ColorRT.ColorARGB;
import CoreMathRT.MathVector;
import CoreGraphicsRT.Texturing.Texture;
import Types.PairDouble;
import Types.RealPoint3D;

public class Plane extends Object {
    private static final double EPS = 0.1;
    private RealPoint3D minPoint, maxPoint;
    private RealPoint3D vec1, vec2;
    private RealPoint3D normal;
    private double d;

    private Texture texture;
    private PlaneColorState colorState;

    public enum PlaneColorState {
        textured, colored //состояние полигона
    }

    public Plane(RealPoint3D p1, RealPoint3D p2, ColorARGB color) {
        colorState = PlaneColorState.colored;
        this.color = color;
        this.reflective = 0; //TODO: по умолчанию не зеркальный

        defineNormal(p1, p2);
        d = (p1.x * normal.x + p1.y * normal.y + p1.z * normal.z);
        defineBoundingCube(p1, p2, EPS);
    }

    public Plane(RealPoint3D p1, RealPoint3D p2, String sourcePath) {
        colorState = PlaneColorState.textured;
        texture = new Texture(sourcePath, p1, p2);
        this.reflective = 0; //TODO: по умолчанию не зеркальный

        defineNormal(p1, p2);
        d = (p1.x * normal.x + p1.y * normal.y + p1.z * normal.z);
        defineBoundingCube(p1, p2, EPS);
    }

    //----------------------------------------------------------------------------------------
    @Override
    public PairDouble determineIntersection(RealPoint3D cameraPos, RealPoint3D direction, double INFINITY_T, double NEAR_T) {
        double multDN = MathVector.scalarMultVec(direction, normal);
        if (multDN == 0) {
            return new PairDouble(INFINITY_T, INFINITY_T); //луч паралелен плоскости
        }

        double t = (d - MathVector.scalarMultVec(cameraPos, normal)) / multDN;
        if (t < 0) {
            return new PairDouble(INFINITY_T, INFINITY_T);
        } else {
            RealPoint3D point = MathVector.addVec(cameraPos, MathVector.scalingVec(direction, t)); //точка на плоскости
            if ((minPoint.x <= point.x && point.x <= maxPoint.x) &&
                    (minPoint.y <= point.y && point.y <= maxPoint.y) &&
                    (minPoint.z <= point.z && point.z <= maxPoint.z)) {
                return new PairDouble(t, t);
            } else {
                return new PairDouble(INFINITY_T, INFINITY_T);
            }
        }
    }

    @Override
    public RealPoint3D calculateNormal(RealPoint3D point) {
        return new RealPoint3D(normal.x, normal.y, normal.z);
    }

    @Override
    public ColorARGB getColor(RealPoint3D point) {
        if (colorState == PlaneColorState.colored) {
            return color;
        } else {
            return texture.getColor(point);
        }
    }
    //----------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------
    private void defineBoundingCube(RealPoint3D p1, RealPoint3D p2, double EPS) {
        //ограничивающий куб
        minPoint = new RealPoint3D();
        maxPoint = new RealPoint3D();

        if (p1.x > p2.x) {
            maxPoint.x = p1.x + EPS;
            minPoint.x = p2.x - EPS;
        } else {
            maxPoint.x = p2.x + EPS;
            minPoint.x = p1.x - EPS;
        }
        if (p1.y > p2.y) {
            maxPoint.y = p1.y + EPS;
            minPoint.y = p2.y - EPS;
        } else {
            maxPoint.y = p2.y + EPS;
            minPoint.y = p1.y - EPS;
        }
        if (p1.z > p2.z) {
            maxPoint.z = p1.z + EPS;
            minPoint.z = p2.z - EPS;
        } else {
            maxPoint.z = p2.z + EPS;
            minPoint.z = p1.z - EPS;
        }
    }

    private void defineNormal(RealPoint3D p1, RealPoint3D p2) {
        //определить нормаль к плоскости
        //два вектора
        if (p1.x != p2.x) {
            //плоскость вдоль OX
            vec1 = new RealPoint3D(p2.x - p1.x, 0, 0);
            vec2 = new RealPoint3D(p2.x - p1.x, p2.y - p1.y, p2.z - p1.z);
        } else {
            //плоскость вдоль OZ
            vec1 = new RealPoint3D(0, 0, p2.z - p1.z);
            vec2 = new RealPoint3D(p2.x - p1.x, p2.y - p1.y, p2.z - p1.z);
        }
        //нормаль к плоскости
        normal = MathVector.vectorMultVec(vec2, vec1);
        normal = MathVector.normVec(normal);
    }
    //----------------------------------------------------------------------------------------
}
