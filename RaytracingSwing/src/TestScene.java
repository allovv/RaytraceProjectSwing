/*
package CoreGraphicsRT;

import ColorRT.ColorConst;
import CoreLightingRT.Lighting;
import CoreLightingRT.LightingType;
import CoreGraphicsRT.ObjectsRT.Camera;
import CoreGraphicsRT.ObjectsRT.Plane;
import CoreGraphicsRT.ObjectsRT.SceneRT;
import CoreGraphicsRT.ObjectsRT.Sphere;
import Types.RealPoint3D;
import CoreGraphicsRT.ObjectsRT.Object;

import java.util.ArrayList;

public class TestScene {
    //класс для тестирования

    private static ArrayList<Object> objects = new ArrayList<Object>();
    private static ArrayList<Lighting> lighting = new ArrayList<Lighting>();
    private static Camera camera;

    public static SceneRT getTestScene1() {
        //шары на зеркальной доске
        camera = new Camera(new RealPoint3D(0, 30, -180));

        objects.add(new Sphere(new RealPoint3D(-40, 10, -40), ColorConst.Silver, 10));
        objects.add(new Sphere(new RealPoint3D(40, 10, -40), ColorConst.Silver, 10));
        objects.add(new Sphere(new RealPoint3D(-40, 10, 40), ColorConst.Silver, 10));
        objects.add(new Sphere(new RealPoint3D(40, 10, 40), ColorConst.Silver, 10));

        objects.add(new Plane(new RealPoint3D(-500, 0, -200), new RealPoint3D(500, 0, 1000), "/img/tex3.png"));

        lighting.add(new Lighting(LightingType.directional, 0.9, new RealPoint3D(-3, -8, -8)));
        //lighting.add(new Lighting(LightingType.point, 0.7, new RealPoint3D(0, 30, 0)));
        lighting.add(new Lighting(LightingType.point, 0.7, new RealPoint3D(0, 10, 0)));

        return new SceneRT(objects, lighting, camera);
    }

    public static SceneRT getTestScene2() {
        //планеты
        camera = new Camera(new RealPoint3D(0, 0, -100));

        objects.add(new Sphere(new RealPoint3D(0, 0, 600), ColorConst.Sun, 40, 50));
        objects.add(new Sphere(new RealPoint3D(60, 0, 600), ColorConst.Mercury, 3, 50));
        objects.add(new Sphere(new RealPoint3D(75, 0, 600), ColorConst.Venus, 4, 50));
        objects.add(new Sphere(new RealPoint3D(90, 0, 600), ColorConst.Earth, 5, 50));
        objects.add(new Sphere(new RealPoint3D(105, 0, 600), ColorConst.Mars, 4, 50));
        objects.add(new Sphere(new RealPoint3D(140, 0, 600), ColorConst.Jupiter, 10, 50));
        objects.add(new Sphere(new RealPoint3D(170, 0, 600), ColorConst.Saturn, 8, 50));
        objects.add(new Sphere(new RealPoint3D(200, 0, 600), ColorConst.Uranus, 7, 50));
        objects.add(new Sphere(new RealPoint3D(230, 0, 600), ColorConst.Neptune, 6,50));

        objects.add(new Plane(new RealPoint3D(-800, -400, 1200), new RealPoint3D(800, 400, 1200), ColorConst.Silver));

        lighting.add(new Lighting(LightingType.point, 0.4, new RealPoint3D(0, 50, 100)));
        lighting.add(new Lighting(LightingType.point, 0.4, new RealPoint3D(100, 50, 100)));
        lighting.add(new Lighting(LightingType.point, 0.4, new RealPoint3D(-100, 50, 100)));

        return new SceneRT(objects, lighting, camera);
    }

    public static SceneRT getTestScene3() {
        //плоскости под углами
        camera = new Camera(new RealPoint3D(0, 0, -30));

        objects.add(new Sphere(new RealPoint3D(0, 0, -2), ColorConst.Blue, 0.8, 10, 0));

        objects.add(new Plane(new RealPoint3D(-5, -5, 10), new RealPoint3D(5, 5, 10), ColorConst.Silver));
        objects.add(new Plane(new RealPoint3D(-5, -10, 5), new RealPoint3D(5, -5, 10), ColorConst.Silver));
        objects.add(new Plane(new RealPoint3D(-5, 5, 10), new RealPoint3D(5, 10, 5), ColorConst.Silver));

        lighting.add(new Lighting(LightingType.point, 0.3, new RealPoint3D(0, 0, -5)));
        lighting.add(new Lighting(LightingType.point, 0.3, new RealPoint3D(0, 2, -5)));
        lighting.add(new Lighting(LightingType.point, 0.3, new RealPoint3D(0, -2, -5)));

        return new SceneRT(objects, lighting, camera);
    }
}
*/