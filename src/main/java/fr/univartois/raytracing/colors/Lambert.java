package fr.univartois.raytracing.colors;

import fr.univartois.raytracing.light.ILight;
import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Triplet;
import fr.univartois.raytracing.numeric.Vector;
import fr.univartois.raytracing.scenery.Scenery;
import fr.univartois.raytracing.shape.IShape;

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

    public Color lambertCalcul(ILight dlight,IShape shape,Vector d, double t) {
        Vector n;
        Color ld;
        Point o = scene.getCamera().getLookFrom();
        Point p = (d.scalarMultiplication(t)).addition(o);
        n = p.substraction(shape.getCenter());
        n=n.norm();

        double a;
        Vector ldir = dlight.getVector();
        if(ldir == null) {
                ldir = dlight.getCoord().substraction(p);
        }
        ldir = ldir.norm();

        a = n.scalarProduct(ldir);
        if (a < 0)
            a=0;

        ld=((dlight.getColor()).scalarMultiplication(a)).schurProduct(shape.getDiffuse());

        return ld;
    }
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
