package CoreLightingRT;

import ColorRT.ColorARGB;
import CoreMathRT.MathVector;
import CoreGraphicsRT.ObjectsRT.Object;
import Types.InterSolution;
import Types.PairDouble;
import Types.RealPoint3D;

import java.util.ArrayList;

public class LightingCore {
    public static final double MAX_T_SHADOW = 1;
    public static final double MIN_T_SHADOW = 0.0001;

    public static final double K_SPEC = 0.3; //диапазон [0, 1]
    public static final double K_DIFF = 0.4; //диапазон [0, 1]

    public static final double AMBIENT_INT = 0.2; //фоновое освещение

    //----------------------------------------------------------------------------------------
    public static ColorARGB calculateLighting(ArrayList<Object> objects, ArrayList<Lighting> lightingArray,
                                              RealPoint3D point,
                                              RealPoint3D normal, RealPoint3D toView,
                                              int specular, ColorARGB color) {
        double intAmb = AMBIENT_INT;
        double intDiff = 0;
        double intSpec = 0;
        Lighting lighting;
        for (int i = 0; i < lightingArray.size(); i++) {
            lighting = lightingArray.get(i);
            RealPoint3D toLight;
            if (lighting.type == LightingType.point) {
                toLight = MathVector.subVec(lighting.position, point);
            } else {
                toLight = MathVector.reverseVec(lighting.position);
            }

            InterSolution shadowSolution = detIntersectShadows(objects, point, toLight, MIN_T_SHADOW, MAX_T_SHADOW);
            if (shadowSolution.object != null) {
                continue;
            }

            intDiff += calculateDiffuse(lighting, toLight, normal);
            intSpec += calculateSpecular(lighting, toLight, normal, toView, specular);
        }
        color.multByNumber(intAmb + intDiff + intSpec);
        int r = color.getR(), g = color.getG(), b = color.getB();
        if (color.getR() > 255) {
            r = 255;
        }
        if (color.getG() > 255) {
            g = 255;
        }
        if (color.getB() > 255) {
            b = 255;
        }
        return new ColorARGB(255, r, g, b);
    }
    //----------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------
    private static double calculateDiffuse(Lighting light, RealPoint3D L, RealPoint3D N) {
        L = MathVector.normVec(L);
        N = MathVector.normVec(N);
        double multNL = MathVector.scalarMultVec(L, N);
        if (multNL > 0) {
            return light.intensity * multNL * K_DIFF;
        }
        return 0;
    }

    private static double calculateSpecular(Lighting light, RealPoint3D L, RealPoint3D N, RealPoint3D V, int specular) {
        RealPoint3D R = reflectedRay(L, N);
        R = MathVector.normVec(R);
        V = MathVector.normVec(V);
        double multRV = MathVector.scalarMultVec(R, V);
        if (multRV > 0) {
            return light.intensity * Math.pow(multRV, specular) * K_SPEC;
        }
        return 0;
    }
    //----------------------------------------------------------------------------------------

    public static RealPoint3D reflectedRay(RealPoint3D ray, RealPoint3D normal) {
        double multRN = MathVector.scalarMultVec(ray, normal);
        multRN *= 2;
        RealPoint3D scalingN = MathVector.scalingVec(normal, multRN);
        return MathVector.subVec(scalingN, ray);
    }

    //----------------------------------------------------------------------------------------
    private static InterSolution detIntersectShadows(ArrayList<Object> objects,
                                                       RealPoint3D cameraPos, RealPoint3D direction,
                                                       double MIN_T_SHADOW, double MAX_T_SHADOW) {
        double closestT = MAX_T_SHADOW;
        Object closestObject = null;
        for (int i = 0; i < objects.size(); i++) {
            PairDouble solution = objects.get(i).determineIntersection(cameraPos, direction, MIN_T_SHADOW, MAX_T_SHADOW);
            if (MIN_T_SHADOW < solution.first && solution.first < MAX_T_SHADOW && solution.first < closestT) {
                closestT = solution.first;
                closestObject = objects.get(i);
            }
            if (MIN_T_SHADOW < solution.second && solution.second < MAX_T_SHADOW && solution.second < closestT) {
                closestT = solution.second;
                closestObject = objects.get(i);
            }
        }
        return new InterSolution(closestObject, closestT);
    }
    //----------------------------------------------------------------------------------------
}
