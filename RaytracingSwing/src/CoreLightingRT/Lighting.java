package CoreLightingRT;

import ColorRT.ColorARGB;
import ColorRT.ColorConst;
import CoreGraphicsRT.ObjectsRT.Sphere;
import Types.RealPoint3D;

public class Lighting {
    private static final double RAD_OF_BOUNDING_SPHERE = 5;

    public LightingType type;
    public double intensity; //диапазон [0, 1] (intensity)
    public RealPoint3D position;
    public ColorARGB color;
    private Sphere boundingSphere; //TODO: ограничивающая сфера

    public Lighting(LightingType type, double intensity, RealPoint3D position){
        this.type = type;
        this.intensity = intensity;
        this.position = position;
        this.color = ColorConst.White;
        boundingSphere = new Sphere(position, color, RAD_OF_BOUNDING_SPHERE);
    }

    public ColorARGB getColor() { return color; }
    public Sphere getBoundingSphere() { return boundingSphere; }
}
