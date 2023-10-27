package fr.univartois.raytracing.antiCrenelage;

import fr.univartois.raytracing.numeric.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a method for anti-aliasing that calculates a single vector for the middle of a pixel.
 */
public class Middle implements ICrenelage{
    List<Vector> D= new ArrayList<>();

    /**
     * Calculates a single vector for the middle of a pixel for anti-aliasing.
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
     * @return A list containing a single vector calculated for anti-aliasing at the center of the pixel.
     */
    @Override
    public List<Vector> caclulVector(double realWidth, double pixelWidth, double realHeight, double pixelHeight,int i, int j,Vector u, Vector v, Vector w) {
        double a = -(realWidth / 2) + (i + 0.5) * pixelWidth;
        double b = (realHeight/2)-(j+0.5)*pixelHeight;
        Vector d = ((u.scalarMultiplication(a))
                .addition(v.scalarMultiplication(b)))
                .substraction(w);
        d = d.norm();
        System.out.println(d.toString());
        this.D.add(d);
        System.out.println(D.get(0).toString());
        return this.D;
    }
}
