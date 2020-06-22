package TimeRT;

public class FPS {
    static private int fps = 0;
    static private String fpsStr;

    public static String calculateFPS(double deltaTime) {
        fps = (int)(1 / (deltaTime));
        fpsStr = "FPS: " + fps;
        return fpsStr;
    }
}
