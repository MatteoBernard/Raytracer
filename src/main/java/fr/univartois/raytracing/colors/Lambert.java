/**
 * The Lambert class implements the Lambert method to calculate the color of surfaces.
 * The class calculates the light reflection color using Lambert method.
 *
 * @author antoine_crauser
 */

package fr.univartois.raytracing.colors;

import fr.univartois.raytracing.light.ILight;
import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Vector;
import fr.univartois.raytracing.scenery.Scenery;
import fr.univartois.raytracing.shape.IShape;

public class Lambert implements ICalcul {
    private Normal calcul;
    private Scenery scene;

    /**
     * This constructor constructs an object of Lambert with the Normal calculator.
     *
     * @param calcul The Normal calculator for the calculations.
     */

    public Lambert(Normal calcul) {
        this.calcul = calcul;
        this.scene = calcul.getScene();
    }
    /**
     * Returns the Calcul of the method.
     *
     * @return The calcul.
     */

    public Normal getCalcul() {
        return calcul;
    }
    /**
     * Returns the Scene of the method.
     *
     * @return The scene.
     */


    public Scenery getScene() {
        return scene;
    }

    /**
     * Use the Lambert method to calculate shading of colors
     *
     * @param dlight is the direct source of light for the shading calculation.
     * @param shape is the shape on which we apply the shading.
     * @param d is the vector that allow to calculate the intersection point with the shape.
     * @param t is the double used for calculations.
     * @return The color of the shading.
     */

    public Color lambertCalcul(ILight dlight,IShape shape,Vector d, double t) {
        Vector n;
        Color ld;
        Point o = scene.getCamera().getLookFrom();
        Point p = (d.scalarMultiplication(t)).addition(o);
        n = p.substraction(shape.getCenter());
        n=n.norm();

        double a;
        Vector ldir = dlight.getVector();
        if(ldir == null)
                ldir = (dlight.getCoord()).substraction(p);

        ldir = ldir.norm();

        a = n.scalarProduct(ldir);
        if (a < 0)
            a=0;

        ld=((dlight.getColor()).scalarMultiplication(a)).schurProduct(shape.getDiffuse());

        return ld;
    }

    /**
     * It calculates the color of the shape with the Lambert method
     *
     * @param shape is the shape for which we calculate the color.
     * @param d is the vector that allow to calculate the intersection point with the shape.
     * @param t is the double used for calculations.
     * @return The color of the shape with the Lambert method.
     */
    @Override
    public Color colorCalcul(IShape shape, Vector d, double t) {
        Color sum = calcul.colorCalcul(shape,d,t);
        for (ILight light : scene.getLights()) {
            sum = sum.addition(
                    lambertCalcul(light,shape,d,t)
            );
        }
        return sum;
    }
}
