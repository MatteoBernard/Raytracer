package fr.univartois.raytracing.shadow;

import fr.univartois.raytracing.Colors.ICalcul;
import fr.univartois.raytracing.light.ILight;
import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Vector;
import fr.univartois.raytracing.scenery.Scenery;
import fr.univartois.raytracing.shape.IShape;

/**
 * The BlinnPhong class implements the ICalcul interface for calculating colors using the Blinn-Phong shading model.
 */
public class BlinnPhong implements ICalcul {

    private Scenery scene;
    private final ICalcul calcul;

    /**
     * Constructs a BlinnPhong object with the given scene and calculation interface.
     *
     * @param scene   The scene to use for calculations.
     * @param calcul  The calculation interface to use.
     */
    public BlinnPhong(Scenery scene, ICalcul calcul) {
        this.scene = scene;
        this.calcul = calcul;
    }

    /**
     * Constructs a BlinnPhong object with the given calculation interface, using the scene from the calculation interface.
     *
     * @param calcul  The calculation interface to use, providing the scene.
     */
    public BlinnPhong(ICalcul calcul) {
        this.scene = calcul.getScene();
        this.calcul = calcul;
    }

    /**
     * Calculate the color for the specified shape and direction vector using the Blinn-Phong shading model.
     *
     * @param shape  The shape to calculate shading for.
     * @param d      The direction vector.
     * @return       The calculated color.
     */
    @Override
    public Color colorCalcul(IShape shape, Vector d) {
        return this.calculBlinnPhong(shape, d);
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
     * @param shape  The shape to calculate shading for.
     * @param d      The direction vector.
     * @return       The calculated color.
     */
    public Color calculBlinnPhong(IShape shape, Vector d) {
        Color sum = this.scene.getAmbient();

        for (ILight light : this.scene.getLights()) {
            if (light.getVector() != null) {
                Vector eyedir = d.scalarMultiplication(-1.);
                Vector lightdir = light.getVector();
                Vector h = (lightdir.addition(eyedir)).norm();

                Point p = (d.scalarMultiplication(shape.intersect(this.scene.getCamera().getLookFrom(), d)))
                        .addition(this.scene.getCamera().getLookFrom());
                double t = shape.intersect(p, lightdir);
                if (t >= 0)
                    p = (d.scalarMultiplication(t).addition(this.scene.getCamera().getLookFrom()));
                Vector n = (p.substraction(shape.getCenter())).norm();

                sum.addition(light.getColor().scalarMultiplication(Math.max(n.scalarProduct(lightdir), 0))
                        .schurProduct(shape.getDiffuse()).addition(
                                light.getColor().scalarMultiplication(Math.max(n.scalarProduct(h), 0))
                                        .schurProduct(shape.getSpecular())
                        ));
            }
        }
        return sum;
    }
}
