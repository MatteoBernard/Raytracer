package fr.univartois.raytracing.antiCrenelage;

import fr.univartois.raytracing.numeric.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a random anti-aliasing method that calculates random vectors within each pixel.
 */
public class Random implements ICrenelage{
    int nb;
    List<Vector> D= new ArrayList<>();


    /**
     * Constructs a Random anti-aliasing method with a given number of samples per pixel.
     *
     * @param nb The number of random samples to generate within each pixel.
     */
    public Random(int nb) {
        this.nb = nb;
    }

    /**
     * Calculates random vectors for anti-aliasing within each pixel.
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
     * @return A list of random vectors calculated for anti-aliasing within the pixel.
     */
    @Override
    public List<Vector> caclulVector(double realWidth, double pixelWidth, double realHeight, double pixelHeight, int i, int j, Vector u, Vector v, Vector w) {
        for(int x =0;x<this.nb;x++){
            double y = Math.random();
            double z = Math.random();
            double a = -(realWidth / 2) + (i + y) * pixelWidth;
            double b = (realHeight/2)-(j+z)*pixelHeight;
            Vector d = ((u.scalarMultiplication(a))
                    .addition(v.scalarMultiplication(b)))
                    .substraction(w);
            d = d.norm();
            this.D.add(d);

        }
        return this.D;
    }
}
