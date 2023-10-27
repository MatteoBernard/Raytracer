package fr.univartois.raytracing.Colors;

import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Triplet;
import fr.univartois.raytracing.numeric.Vector;
import fr.univartois.raytracing.scenery.Scenery;
import fr.univartois.raytracing.shape.IShape;

public class Checker implements ICalcul{
    private Scenery scene;
    private Color col1;
    private Color col2;

    public Checker(Scenery scene, Color col1, Color col2) {
        this.scene = scene;
        this.col1 = col1;
        this.col2 = col2;
    }

    public Color getCol1() {
        return col1;
    }

    public Color getCol2() {
        return col2;
    }

    @Override
    public Color colorCalcul(IShape shape, Vector d, double t) {

        Point p = d.scalarMultiplication(t).addition(this.scene.getCamera().getLookFrom());
        double x = p.getTriplet().getX();
        double z = p.getTriplet().getZ();
        if ((x > 0.0 && z > 0.0 && x < 1.0 && z < 1.0) &&
                ((x<0.5 && z<0.5) || (x>0.5 && z>0.5))
        ) return this.getCol1();
        return this.getCol2();
    }

    @Override
    public Scenery getScene() {
        return this.scene;
    }

    public void setScene(Scenery scene) {
        this.scene = scene;
    }
}
