package fr.univartois.raytracing.colors;

import fr.univartois.raytracing.colors.ICalcul;
import fr.univartois.raytracing.light.DirectionalLight;
import fr.univartois.raytracing.light.ILight;
import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Triplet;
import fr.univartois.raytracing.numeric.Vector;
import fr.univartois.raytracing.scenery.Scenery;
import fr.univartois.raytracing.shape.IShape;
import fr.univartois.raytracing.shape.Sphere;

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
    public Color colorCalcul(IShape shape, Vector d) {
        Color sum = new Color(new Triplet(0, 0, 0));

        for (ILight light : this.scene.getLights()) {
            if (light.getVector() != null) {
                sum.addition(
                        calcul.lambertCalcul((DirectionalLight) light, shape, d).addition(
                                this.blinnPhongCalcul((DirectionalLight) light, shape, d)
                        )
                );
            }
        }
        return calcul.getCalcul().colorCalcul(shape, d).addition(sum);
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

    public Color blinnPhongCalcul(DirectionalLight light, IShape shape, Vector d) {
        Vector eyedir = d.scalarMultiplication(-1.);
        Vector lightdir = light.getVector();
        Vector h = (lightdir.addition(eyedir)).norm();

        Point p = (d.scalarMultiplication(shape.intersect(this.scene.getCamera().getLookFrom(), d)))
                .addition(this.scene.getCamera().getLookFrom());
        double t = shape.intersect(p, lightdir);
        if (t >= 0)
            p = (d.scalarMultiplication(t).addition(this.scene.getCamera().getLookFrom()));
        else return new Color(new Triplet(0,0,0));

        Vector n = (p.substraction(shape.getCenter())).norm();

        return light.getColor().scalarMultiplication
                        (Math.pow(Math.max(n.scalarProduct(h), 0), shape.getShininess()))
                .schurProduct(shape.getSpecular());
    }
}
