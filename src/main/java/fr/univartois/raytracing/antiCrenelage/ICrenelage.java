package fr.univartois.raytracing.antiCrenelage;

import fr.univartois.raytracing.numeric.Vector;
import java.util.List;


/**
 * Interface for antialiasing methods that calculate vectors to mitigate aliasing in ray tracing.
 */
public interface ICrenelage {


    /**
     * Calculates vectors for antialiasing given the real-world dimensions, pixel dimensions, pixel coordinates, and basis vectors.
     *
     * @param realWidth     The width of the real-world scene.
     * @param pixelWidth    The width of a pixel.
     * @param realHeight    The height of the real-world scene.
     * @param pixelHeight   The height of a pixel.
     * @param i             The horizontal pixel index.
     * @param j             The vertical pixel index.
     * @param u             The u-axis vector.
     * @param v             The v-axis vector.
     * @param w             The w-axis vector.
     * @return A list of vectors calculated for anti-aliasing.
     */
    List<Vector> caclulVector(double realWidth, double pixelWidth, double realHeight, double pixelHeight,int i, int j, Vector u, Vector v, Vector w);
}
