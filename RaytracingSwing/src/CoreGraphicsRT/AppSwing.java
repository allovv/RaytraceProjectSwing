package CoreGraphicsRT;

import GUI.GuiApp;
import TimeRT.FPS;
import TimeRT.Timer;

import java.awt.image.BufferedImage;

public class AppSwing {
    //----------------------------------------------------------------------------------------
    protected static GuiApp GUI; //графический интерфейс
    protected static GraphicsCore graphCore = new GraphicsCore(); //графическое ядро
    protected static BufferedImage graphicBuffer = new BufferedImage(graphCore.getCanvasW(), graphCore.getCanvasH(), BufferedImage.TYPE_INT_ARGB); //графический буфер
    //----------------------------------------------------------------------------------------

    public static void main(String[] args) {
        GUI = new GuiApp(graphicBuffer);
        GUI.draw(graphicBuffer);
    }

    //----------------------------------------------------------------------------------------
    public static void drawInGraphicsBuffer(int x, int y, int colorARGB) {
        graphicBuffer.setRGB(x, y, colorARGB); //рисуем пиксель в графическом буфере
    }

    //----------------------------------------------------------------------------------------
    public static void reProcessing() {
        TimeRT.Timer.startTimer();
        graphCore.processing();
        TimeRT.Timer.stopTimer();
        String fps = FPS.calculateFPS(Timer.getTimeSeconds());
        setFPS(fps);

        GUI.draw(graphicBuffer);
    }

    //----------------------------------------------------------------------------------------
    public static void setFPS(String str) {
        GuiApp.setFPS(str);
    }
    public static GraphicsCore getGraphCore() {
        return graphCore;
    }
}
