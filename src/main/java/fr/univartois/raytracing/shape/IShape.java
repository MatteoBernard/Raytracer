package fr.univartois.raytracing.shape;

/**
 * This interface represents a geometric shape in a ray tracing system.
 */
import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Vector;

public interface IShape {
    public double intersect (Point p, Vector d);

    public Color getDiffuse();

    public Color getSpecular();

    public int getShininess();

    public Point getCenter();
}
