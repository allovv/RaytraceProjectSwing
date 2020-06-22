package CoreGraphicsRT.Rotator;

import Types.RealPoint3D;

public class Rotator {
    //Матрциа вращения в плоскости XY
    private static double [][] rotatorXY = {{1,0,0}, {0,1,0}, {0,0,1}};
    //Матрица вращения в плоскости YZ
    private static double [][] rotatorYZ = {{1,0,0}, {0,1,0}, {0,0,1}};
    //Матрица вращения в плоскости XZ
    private static double [][] rotatorXZ = {{1,0,0}, {0,1,0}, {0,0,1}};

    //----------------------------------------------------------------------------------------
    public static void updateRotatorXY(double angleXY) {
        //по строкам
        rotatorXY[0][0] = Math.cos(angleXY);
        rotatorXY[0][1] = -Math.sin(angleXY);
        rotatorXY[0][2] = 0;

        rotatorXY[1][0] = Math.sin(angleXY);
        rotatorXY[1][1] = Math.cos(angleXY);
        rotatorXY[1][2] = 0;

        rotatorXY[2][0] = 0;
        rotatorXY[2][1] = 0;
        rotatorXY[2][2] = 1;
    }

    public static void updateRotatorYZ(double angleYZ) {
        //по строкам
        rotatorYZ[0][0] = 1;
        rotatorYZ[0][1] = 0;
        rotatorYZ[0][2] = 0;

        rotatorYZ[1][0] = 0;
        rotatorYZ[1][1] = Math.cos(angleYZ);
        rotatorYZ[1][2] = Math.sin(angleYZ);

        rotatorYZ[2][0] = 0;
        rotatorYZ[2][1] = -Math.sin(angleYZ);
        rotatorYZ[2][2] = Math.cos(angleYZ);
    }

    public static void updateRotatorXZ(double angleXZ) {
        //по строкам
        rotatorXZ[0][0] = Math.cos(angleXZ);
        rotatorXZ[0][1] = 0;
        rotatorXZ[0][2] = -Math.sin(angleXZ);

        rotatorXZ[1][0] = 0;
        rotatorXZ[1][1] = 1;
        rotatorXZ[1][2] = 0;

        rotatorXZ[2][0] = Math.sin(angleXZ);
        rotatorXZ[2][1] = 0;
        rotatorXZ[2][2] = Math.cos(angleXZ);
    }
    //----------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------
    public static RealPoint3D rotatePointXY(RealPoint3D point3D) {
        RealPoint3D point = new RealPoint3D();

        double [] vec = {point3D.x, point3D.y, point3D.z};
        double [] result = {0,0,0};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i] += vec[j] * rotatorXY[i][j];
            }
        }
        point.x = result[0];
        point.y = result[1];
        point.z = result[2];

        return point;
    }

    public static RealPoint3D rotatePointYZ(RealPoint3D point3D) {
        RealPoint3D point = new RealPoint3D();

        double [] vec = {point3D.x, point3D.y, point3D.z};
        double [] result = {0,0,0};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i] += vec[j] * rotatorYZ[i][j];
            }
        }
        point.x = result[0];
        point.y = result[1];
        point.z = result[2];

        return point;
    }

    public static RealPoint3D rotatePointXZ(RealPoint3D point3D) {
        RealPoint3D point = new RealPoint3D();

        double [] vec = {point3D.x, point3D.y, point3D.z};
        double [] result = {0,0,0};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i] += vec[j] * rotatorXZ[i][j];
            }
        }
        point.x = result[0];
        point.y = result[1];
        point.z = result[2];

        return point;
    }
    //----------------------------------------------------------------------------------------
}
