package CoreGraphicsRT.ObjectsRT;

import Constants.Const;
import Types.RealPoint3D;

public class Camera {
    private static final double PI = 3.1415;

    private RealPoint3D cameraPos;
    private double cameraAngleX = Const.CAMERA_ANGLE_X;
    private double cameraSpeed = Const.CAMERA_SPEED;
    private double cameraAngleSpeed = Const.CAMERA_ANGLE_SPEED;

    public Camera(RealPoint3D cameraPos) {
        this.cameraPos = cameraPos;
    }

    //----------------------------------------------------------------------------------------
    public double getCameraAngleX() { return cameraAngleX; }
    public RealPoint3D getCameraPos() { return cameraPos; }
    //----------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------
    public void incCameraAngleX() {
        if (cameraAngleX < - PI) {
            cameraAngleX += 2*PI;
        }
        if (cameraAngleX > PI) {
            cameraAngleX -= 2*PI;
        }
        cameraAngleX += cameraAngleSpeed;
    }
    public void decCameraAngleX() {
        if (cameraAngleX < - PI) {
            cameraAngleX += 2*PI;
        }
        if (cameraAngleX > PI) {
            cameraAngleX -= 2*PI;
        }
        cameraAngleX -= cameraAngleSpeed;
    }
    //----------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------
    public void moveLeft() {
        cameraPos.z -= Math.sin(cameraAngleX) * cameraSpeed;
        cameraPos.x -= Math.cos(cameraAngleX) * cameraSpeed;
    }
    public void moveRight() {
        cameraPos.z += Math.sin(cameraAngleX) * cameraSpeed;
        cameraPos.x += Math.cos(cameraAngleX) * cameraSpeed;
    }
    public void moveForward() {
        cameraPos.z += Math.cos(cameraAngleX) * cameraSpeed;
        cameraPos.x -= Math.sin(cameraAngleX) * cameraSpeed;
    }
    public void moveBack(){
        cameraPos.z -= Math.cos(cameraAngleX) * cameraSpeed;
        cameraPos.x += Math.sin(cameraAngleX) * cameraSpeed;
    }
    public void moveUp() {
        cameraPos.y += cameraSpeed;
    }
    public void moveDown() {
        cameraPos.y -= cameraSpeed;
    }
    //----------------------------------------------------------------------------------------
}
