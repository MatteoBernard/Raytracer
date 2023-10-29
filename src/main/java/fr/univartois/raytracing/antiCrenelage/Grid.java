package fr.univartois.raytracing.antiCrenelage;

import fr.univartois.raytracing.numeric.Vector;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a grid used for anti-aliasing in ray tracing.
 */
public class Grid implements ICrenelage{

    int nb;
    List<Vector> D= new ArrayList<>();

    /**
     * Constructs a Grid with a given number of subdivisions.
     *
     * @param nb The number of subdivisions for the grid.
     */
    public Grid(int nb) {
        this.nb = nb;
    }

    /**
     * Calculates vectors for anti-aliasing in a grid pattern.
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
     * @return A list of calculated vectors for anti-aliasing.
     */
    @Override
    public List<Vector> caclulVector(double realWidth, double pixelWidth, double realHeight, double pixelHeight, int i, int j, Vector u, Vector v, Vector w) {
        double valx = 1/(this.nb*2);
        for(int x =0;x<this.nb;x++){
            double valy = 1/(this.nb*2);
            for(int y = 0;y<this.nb;y++) {
                double a = -(realWidth / 2) + (i + valx) * pixelWidth;
                double b = (realHeight / 2) - (j + valy) * pixelHeight;
                Vector d = ((u.scalarMultiplication(a))
                        .addition(v.scalarMultiplication(b)))
                        .substraction(w);
                d = d.norm();
                this.D.add(d);
                valy+=1/this.nb;

            }
            valx+=1/this.nb;
        }
        return this.D;
    }
}
