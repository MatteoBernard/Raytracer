package fr.univartois.raytracing.shape;

/**
 * This interface represents a geometric shape in a ray tracing system.
 */
import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Vector;

/**
 * The `IShape` interface defines the common methods that should be implemented
 * by any class representing a geometric shape in a ray tracing system.
 */
public interface IShape {
    /**
     * Computes the intersection of a ray with this geometric shape.
     *
     * @param p The starting point of the ray.
     * @param d The direction of the ray.
     * @return The parameter t at which the ray intersects the shape, or -1 if there is no intersection.
     */
    public double intersect(Point p, Vector d);

    /**
     * Retrieves the diffuse color of the shape, which represents its surface color.
     *
     * @return The diffuse color of the shape.
     */
    public Color getDiffuse();

    /**
     * Retrieves the specular color of the shape, which represents its highlight color.
     *
     * @return The specular color of the shape.
     */
    public Color getSpecular();

    /**
     * Retrieves the shininess of the shape's surface, affecting the intensity of specular highlights.
     *
     * @return The shininess of the shape.
     */
    public int getShininess();

    /**
     * Retrieves the center point of the geometric shape.
     *
     * @return The center point of the shape.
     */
    public Point getCenter();
}
