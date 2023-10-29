package fr.univartois.raytracing.colors;

import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Triplet;
import fr.univartois.raytracing.numeric.Vector;
import fr.univartois.raytracing.scenery.Scenery;
import fr.univartois.raytracing.shape.IShape;

/**
 * The Checker class implements the ICalcul interface for calculating colors when intersection point is in the checker (a plane).
 */
public class Checker implements ICalcul{
    private Scenery scene;
    private Color col1;
    private Color col2;

    /**
     * Constructs a Checker object with the given scene and the checker colors.
     *
     * @param scene The scene to use for calculations.
     * @param col1 The first color of the checker, the half of squares will be colored like that.
     * @param col2 The second color of the ckecker, the last half of squares will be colored like that.
     */
    public Checker(Scenery scene, Color col1, Color col2) {
        this.scene = scene;
        this.col1 = col1;
        this.col2 = col2;
    }

    /**
     * Retrieves the first color of the checker
     *
     * @return the first color of the checker
     */
    public Color getCol1() {
        return col1;
    }

    /**
     * Retrieves the first color of the checker
     *
     * @return the second color of the checker
     */
    public Color getCol2() {
        return col2;
    }

    /**
     * Calculate the color of the point for the specified shape and direction vector.
     *
     * @param shape The shape to calculate shading for.
     * @param d     The direction vector.
     * @return The calculated color.
     */
    @Override
    public Color colorCalcul(IShape shape, Vector d, double t) {

        Point p = d.scalarMultiplication(t).addition(this.scene.getCamera().getLookFrom());
        double x = p.getTriplet().getX();
        double z = p.getTriplet().getZ();
        if ((x<0.5 && z<0.5) || (x>0.5 && z>0.5)) return new Color(new Triplet(0,0,0));
        return new Color(new Triplet(1,1,1));
    }

    /**
     * Retrieve the scene used for calculations.
     *
     * @return the scene used for calculations.
     */
    @Override
    public Scenery getScene() {
        return this.scene;
    }

    /**
     * Set the scene used for calculations.
     *
     * @param scene the scene used for calculations.
     */
    public void setScene(Scenery scene) {
        this.scene = scene;
    }
}
