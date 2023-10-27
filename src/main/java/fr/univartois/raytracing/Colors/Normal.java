package fr.univartois.raytracing.Colors;

import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Vector;
import fr.univartois.raytracing.scenery.Scenery;
import fr.univartois.raytracing.shape.IShape;

public class Normal implements ICalcul {
    private final Color amb;
    private Scenery scene;

    public Color getAmb() {
        return this.amb;
    }

    public Normal(Scenery scene) {
        this.scene=scene;
        this.amb = scene.getAmbient();
    }

    @Override
    public Color colorCalcul(IShape shape, Vector d, double t) {
        return this.getAmb();
    }

    @Override
    public Scenery getScene() {
        return this.scene;
    }
}