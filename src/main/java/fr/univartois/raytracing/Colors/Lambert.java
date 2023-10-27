package fr.univartois.raytracing.Colors;

import fr.univartois.raytracing.light.DirectionalLight;
import fr.univartois.raytracing.light.ILight;
import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Triplet;
import fr.univartois.raytracing.numeric.Vector;
import fr.univartois.raytracing.scenery.Scenery;
import fr.univartois.raytracing.shape.IShape;
import fr.univartois.raytracing.shape.Sphere;

public class Lambert implements ICalcul {
    private Normal calcul;
    private Scenery scene;

    public Lambert(Normal calcul) {
        this.calcul = calcul;
        this.scene = calcul.getScene();
    }

    public Normal getCalcul() {
        return calcul;
    }

    public Scenery getScene() {
        return scene;
    }

    public Color lambertCalcul(DirectionalLight dlight,IShape shape,Vector d, double t) {
        Vector n;
        Color ld;
        Point o = scene.getCamera().getLookFrom();
        Point p = d.scalarMultiplication(t).addition(o);
        n = p.substraction(shape.getCenter());
        n=n.norm();

        double a;

        a = n.scalarProduct(dlight.getVector().norm());
        if (a < 0)
            a=0;

        ld=dlight.getColor().scalarMultiplication(a);

        return ld;
    }
    @Override
    public Color colorCalcul(IShape shape, Vector d, double t) {
        Color sum = new Color(new Triplet(0,0,0));
        for (ILight light : scene.getLights()) {
            if (light instanceof DirectionalLight) sum.addition(
                    lambertCalcul((DirectionalLight) light,shape,d,t)
            );
        }
        return this.calcul.colorCalcul(shape,d).addition(sum.schurProduct(shape.getDiffuse()));
    }
}
