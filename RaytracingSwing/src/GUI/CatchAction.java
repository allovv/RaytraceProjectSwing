package GUI;

import CoreGraphicsRT.AppSwing;
import CoreGraphicsRT.GraphicsCore;
import CoreGraphicsRT.Rotator.Rotator;

import java.awt.event.KeyEvent;

public class CatchAction {
    public static void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                AppSwing.getGraphCore().moveCamera(GraphicsCore.Direction.forward);
                break;
            case KeyEvent.VK_DOWN:
                AppSwing.getGraphCore().moveCamera(GraphicsCore.Direction.back);
                break;
            case KeyEvent.VK_LEFT:
                AppSwing.getGraphCore().moveCamera(GraphicsCore.Direction.left);
                break;
            case KeyEvent.VK_RIGHT:
                AppSwing.getGraphCore().moveCamera(GraphicsCore.Direction.right);
                break;
            case KeyEvent.VK_A:
                AppSwing.getGraphCore().getCamera().incCameraAngleX();
                Rotator.updateRotatorXZ(AppSwing.getGraphCore().getCamera().getCameraAngleX());
                break;
            case KeyEvent.VK_D:
                AppSwing.getGraphCore().getCamera().decCameraAngleX();
                Rotator.updateRotatorXZ(AppSwing.getGraphCore().getCamera().getCameraAngleX());
                break;
            default:
                //для сочетания клавиш
                if (e.getKeyCode() == KeyEvent.VK_Z && e.isShiftDown()) {
                    AppSwing.getGraphCore().moveCamera(GraphicsCore.Direction.down);
                } else if (e.getKeyCode() == KeyEvent.VK_X && e.isShiftDown()) {
                    AppSwing.getGraphCore().moveCamera(GraphicsCore.Direction.up);
                }
                break;
        }
    }
    public static void reProcessing() {
        AppSwing.reProcessing();
    }
}
