package fr.univartois.raytracing.colors;

import fr.univartois.raytracing.light.ILight;
import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Triplet;
import fr.univartois.raytracing.numeric.Vector;
import fr.univartois.raytracing.scenery.Scenery;
import fr.univartois.raytracing.shape.IShape;

/**
 * The BlinnPhong class implements the ICalcul interface for calculating colors using the Blinn-Phong shading model.
 */
public class BlinnPhong implements ICalcul {

    private Scenery scene;
    private final Lambert calcul;

    /**
     * Constructs a BlinnPhong object with the given scene and calculation interface.
     *
     * @param scene  The scene to use for calculations.
     * @param calcul The calculation interface to use.
     */
    public BlinnPhong(Scenery scene, Lambert calcul) {
        this.scene = scene;
        this.calcul = calcul;
    }

    /**
     * Constructs a BlinnPhong object with the given calculation interface, using the scene from the calculation interface.
     *
     * @param calcul The calculation interface to use, providing the scene.
     */
    public BlinnPhong(Lambert calcul) {
        this.scene = calcul.getScene();
        this.calcul = calcul;
    }

    /**
     * Calculate the color for the specified shape and direction vector using the Blinn-Phong shading model.
     *
     * @param shape The shape to calculate shading for.
     * @param d     The direction vector.
     * @return The calculated color.
     */
    @Override
    public Color colorCalcul(IShape shape, Vector d, double t) {
        Color sum = calcul.getCalcul().colorCalcul(shape, d,t);

        Point p = (d.scalarMultiplication(t))
                .addition(this.scene.getCamera().getLookFrom());

        for (ILight light : this.scene.getLights()) {


            sum = sum.addition(
                    ((calcul.lambertCalcul(light, shape, d,t))).addition(
                            this.blinnPhongCalcul( light, shape, d,t)
                    )
            );

            if (sum.getTriplet().getX()==0.5
                    && sum.getTriplet().getY()==0.5
                    && sum.getTriplet().getZ()==0.5f) {
            }
        }
        return sum;
    }

    /**
     * Retrieve the scene used for calculations.
     *
     * @return The scene used for shading calculations.
     */
    @Override
    public Scenery getScene() {
        return this.scene;
    }

    /**
     * Calculate the Blinn-Phong shading model for the specified shape and direction vector.
     *
     * @param shape The shape to calculate shading for.
     * @param d     The direction vector.
     * @return The calculated color.
     */

    public Color blinnPhongCalcul(ILight light, IShape shape, Vector d, double t) {
        Vector eyedir = d.scalarMultiplication(-1);
        Point p = (d.scalarMultiplication(t))
                .addition(this.scene.getCamera().getLookFrom());

        Vector ldir = light.getVector();
        if(ldir == null)ldir = p.substraction(light.getCoord());
        ldir=ldir.norm();

        Vector n = (p.substraction(shape.getCenter())).norm();

        Vector r = ldir.scalarMultiplication(-1).addition(
                n.scalarMultiplication((n.scalarProduct(ldir))*2)
        );

        return (shape.getSpecular()).schurProduct((light.getColor()).scalarMultiplication
                        (Math.pow(Math.max(r.scalarProduct(eyedir), 0), shape.getShininess())));
    }
}
