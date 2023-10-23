package fr.univartois.raytracing.shape;

import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Vector;

public interface IShape {
    public double intersect (Point p, Vector d);
}
