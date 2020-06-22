package Types;

import CoreGraphicsRT.ObjectsRT.Object;

public class InterSolution {
    public double closestT;
    public Object object;

    public InterSolution(Object object, double closestT) {
        this.closestT = closestT;
        this.object = object;
    }
}
