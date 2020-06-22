package CoreGraphicsRT;

import java.util.ArrayList;

import ColorRT.*;
import Constants.Const;
import CoreGraphicsRT.ObjectsRT.Camera;
import CoreGraphicsRT.ObjectsRT.Object;
import CoreGraphicsRT.Rotator.Rotator;
import Types.*;
import CoreMathRT.*;
import CoreLightingRT.*;

public class Tracer {
    //----------------------------------------------------------------------------------------
    public static final double INFINITY_T = 1e30;
    public static final double NEAR_T = 1;
    public static final double MIN_T_EPS = 0.01;
    public static final int RECURSION_DEPTH = Const.RECURSION_DEPTH;
    //----------------------------------------------------------------------------------------

    public void trace(ArrayList<Object> objects, ArrayList<Lighting> lighting, Camera camera) {
        for (int x = -GraphicsCore.getCanvasW() / 2; x < GraphicsCore.getCanvasW() / 2; x++) {
            for (int y = -GraphicsCore.getCanvasH() / 2; y < GraphicsCore.getCanvasH() / 2; y++) {
                RealPoint3D direction = GraphicsCore.canvasToViewport((double) x, (double) y);
                direction = MathVector.normVec(direction);
                direction = Rotator.rotatePointXZ(direction);
                ColorARGB color = rayTrace(objects, lighting, camera.getCameraPos(), direction, RECURSION_DEPTH, NEAR_T, INFINITY_T);
                GraphicsCore.putPixel(x, y, color);
            }
        }
    }

    //----------------------------------------------------------------------------------------
    private static ColorARGB rayTrace(ArrayList<Object> objects, ArrayList<Lighting> lighting,
                                      RealPoint3D cameraPos, RealPoint3D direction,
                                      int recursion_depth, double MIN_T, double MAX_T) {
        InterSolution interSolution = determineIntersection(objects, cameraPos, direction, MIN_T, MAX_T);
        double closestT = interSolution.closestT;
        Object closestObject = interSolution.object;

        if (closestObject == null) {
            return new ColorARGB(ColorConst.backgroundColor);
        }

        RealPoint3D point = MathVector.addVec(cameraPos, MathVector.scalingVec(direction, closestT)); //cameraPos + t * dir
        ColorARGB color = new ColorARGB(closestObject.getColor(point));
        RealPoint3D normal = closestObject.calculateNormal(point);
        color = LightingCore.calculateLighting(objects, lighting, point, normal, MathVector.reverseVec(direction), closestObject.getSpecular(), color);

        if (recursion_depth <= 0 || closestObject.getReflective() <= 0) {
            return new ColorARGB(color);
        }

        RealPoint3D reflectedRay = LightingCore.reflectedRay(MathVector.reverseVec(direction), normal);

        if (closestObject.getReflective() >= 1) {
            // блики от источника света для зеркального освещения
            for (int i = 0; i < lighting.size(); i ++) {
                if (lighting.get(i).type == LightingType.point) {
                    InterSolution interLighting = determineIntersection(lighting.get(i).getBoundingSphere(), point, reflectedRay, MIN_T, MAX_T);
                    Object closestLighting = interLighting.object;
                    if (closestLighting != null) {
                        return new ColorARGB(lighting.get(i).color);
                    }
                }
            }
        }

        ColorARGB reflectedColor = rayTrace(objects, lighting, point, reflectedRay, recursion_depth - 1, MIN_T_EPS, MAX_T);

        color.multByNumber(1 - closestObject.getReflective());
        reflectedColor.multByNumber(closestObject.getReflective());

        ColorARGB colorFinal = new ColorARGB(color);
        colorFinal.addColor(reflectedColor);
        return colorFinal;
    }

    private static InterSolution determineIntersection(ArrayList<Object> objects,
                                                       RealPoint3D cameraPos, RealPoint3D direction,
                                                       double MIN_T, double MAX_T) {
        //для списка объектов
        double closestT = MAX_T;
        Object closestObject = null;
        for (int i = 0; i < objects.size(); i++) {
            PairDouble solution = objects.get(i).determineIntersection(cameraPos, direction, MIN_T, MAX_T);
            if (MIN_T < solution.first && solution.first < MAX_T && solution.first < closestT) {
                closestT = solution.first;
                closestObject = objects.get(i);
            }
            if (MIN_T < solution.second && solution.second < MAX_T && solution.second < closestT) {
                closestT = solution.second;
                closestObject = objects.get(i);
            }
        }
        return new InterSolution(closestObject, closestT);
    }
    private static InterSolution determineIntersection(Object objects,
                                                       RealPoint3D cameraPos, RealPoint3D direction,
                                                       double MIN_T, double MAX_T) {
        //для одиночного объекта
        double closestT = MAX_T;
        Object closestObject = null;
            PairDouble solution = objects.determineIntersection(cameraPos, direction, MIN_T, MAX_T);
            if (MIN_T < solution.first && solution.first < MAX_T && solution.first < closestT) {
                closestT = solution.first;
                closestObject = objects;
            }
            if (MIN_T < solution.second && solution.second < MAX_T && solution.second < closestT) {
                closestT = solution.second;
                closestObject = objects;
            }
        return new InterSolution(closestObject, closestT);
    }
    //----------------------------------------------------------------------------------------
}