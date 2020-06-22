package CoreGraphicsRT;

import java.util.ArrayList;

import CoreGraphicsRT.ObjectsRT.*;
import CoreGraphicsRT.ObjectsRT.Object;
import CoreGraphicsRT.ObjectsRT.SceneRT;
import Types.*;
import ColorRT.*;
import CoreLightingRT.*;
import Constants.*;

public class GraphicsCore {

    //----------------------------------------------------------------------------------------
    private final String APP_NAME = "RaytracingSwing";

    private static final int CANVAS_W = Const.APP_WIDTH;
    private static final int CANVAS_H = Const.APP_HEIGHT;

    private static final int VIEWPORT_W = Const.VIEWPORT_W;
    private static final int VIEWPORT_H = Const.VIEWPORT_H;
    private static final int VIEWPORT_D = Const.VIEWPORT_D;

    private static SceneRT sceneRT = new SceneRT();

    private Tracer tracer = new Tracer();
    //----------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------
    public String getAppName() { return APP_NAME; }
    public static int getCanvasW() { return CANVAS_W; }
    public static int getCanvasH() { return CANVAS_H; }

    public static Camera getCamera() { return sceneRT.getCamera(); }
    public static ArrayList<Object> getObjects() { return sceneRT.getObjects(); }
    public static ArrayList<Lighting> getLighting() { return sceneRT.getLighting(); }

    public static void setSceneRT(SceneRT sceneToTrace) { sceneRT = sceneToTrace; }
    //----------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------
    public static void putPixel(int x, int y, ColorARGB color) {
        IntPoint2D point = transformCoordinates(x, y);
        if (0 <= point.x && point.x <= CANVAS_W - 1 && 0 <= point.y && point.y <= CANVAS_H - 1 ) {
            AppSwing.drawInGraphicsBuffer(point.x, point.y, color.getArgb());
        }
    }

    public static RealPoint3D canvasToViewport(double x, double y) {
        return new RealPoint3D(x * VIEWPORT_W / CANVAS_W,y * VIEWPORT_H / CANVAS_H, VIEWPORT_D);
    }

    public static IntPoint2D transformCoordinates(int x, int y) {
        x = (CANVAS_W >> 1) + x;
        y = (CANVAS_H >> 1) - y;
        return new IntPoint2D(x, y);
    }
    //----------------------------------------------------------------------------------------

    public void processing() {
        tracer.trace(sceneRT.getObjects(), sceneRT.getLighting(), sceneRT.getCamera());
    }

    //----------------------------------------------------------------------------------------
    public enum Direction {
        forward, back, right, left, up, down
    }

    public static void moveCamera(Direction dir) {
        switch (dir) {
            case forward:
                sceneRT.getCamera().moveForward();
                break;
            case back:
                sceneRT.getCamera().moveBack();
                break;
            case left:
                sceneRT.getCamera().moveLeft();
                break;
            case right:
                sceneRT.getCamera().moveRight();
                break;
            case up:
                sceneRT.getCamera().moveUp();
                break;
            case down:
                sceneRT.getCamera().moveDown();
                break;
        }
    }
    //----------------------------------------------------------------------------------------
}
