package CoreGraphicsRT.ObjectsRT;

import CoreLightingRT.Lighting;
import Types.RealPoint3D;

import java.util.ArrayList;

public class SceneRT {
    private ArrayList<Object> objects;
    private ArrayList<Lighting> lighting;
    private Camera camera;

    //----------------------------------------------------------------
    public Camera getCamera() { return camera; }
    public ArrayList<Object> getObjects() { return objects; }
    public ArrayList<Lighting> getLighting() { return lighting; }
    //----------------------------------------------------------------

    public SceneRT() {
        this.objects = new ArrayList<Object>();
        this.lighting = new ArrayList<Lighting>();
        this.camera = new Camera(new RealPoint3D(0, 0, 0));
    }

    public SceneRT(ArrayList<Object> objects, ArrayList<Lighting> lighting, Camera camera) {
        this.objects = objects;
        this.lighting = lighting;
        this.camera = camera;
    }
}
