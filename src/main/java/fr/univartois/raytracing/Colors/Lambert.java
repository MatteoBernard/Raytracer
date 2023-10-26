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
    private ICalcul calcul;
    private Scenery scene;

    public Lambert(ICalcul calcul) {
        this.calcul = calcul;
        this.scene = calcul.getScene();
    }

    public ICalcul getCalcul() {
        return calcul;
    }

    public Scenery getScene() {
        return scene;
    }

    public Color lambertCalcul(Sphere sphere,Vector d) {
        DirectionalLight dlight;
        Vector n;
        Color ld;
        Color somme = new Color(new Triplet(0,0,0));
        Color col;

        Color La=scene.getColors().get("ambient");


        Point o = scene.getCamera().getLookFrom();
        Point p = d.scalarMultiplication(sphere.intersect(o,d)).addition(o);
        n = p.substraction(sphere.getPoint());
        n=n.norm();

        double a;
        for(ILight element : scene.getLights()){
            if (element instanceof DirectionalLight){
                a = n.scalarProduct(element.getVector());
                if (a < 0)
                        a=0;

                ld=element.getColor().scalarMultiplication(a);

                somme.addition(ld.schurProduct(element.getColor()));
            }
        }
        col=(scene.getColors().get("diffuse").schurProduct(somme).addition(La));
        return col;
    }
    @Override
    public Color colorCalcul(IShape shape, Vector d) {
        if (! (shape instanceof Sphere))
            return calcul.colorCalcul(shape,d);
        return lambertCalcul((Sphere) shape,d);
    }
}
