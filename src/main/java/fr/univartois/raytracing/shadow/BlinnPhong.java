package fr.univartois.raytracing.shadow;

import fr.univartois.raytracing.Colors.ICalcul;
import fr.univartois.raytracing.light.ILight;
import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Vector;
import fr.univartois.raytracing.scenery.Scenery;
import fr.univartois.raytracing.shape.IShape;

public class BlinnPhong implements ICalcul {

    Scenery scene;
    ICalcul calcul;

    @Override
    public Color colorCalcul(IShape shape, Vector d) {
        return this.calculBlinnPhong(shape,d);
    }

    @Override
    public Scenery getScene() {
        return this.scene;
    }

    public BlinnPhong(Scenery scene, ICalcul calcul) {
        this.scene = scene;
        this.calcul = calcul;
    }

    public BlinnPhong(ICalcul calcul) {
        this.scene = calcul.getScene();
        this.calcul = calcul;
    }

    public Color calculBlinnPhong(IShape shape, Vector d) {

        Color sum = this.scene.getColors().get("ambient");

        for (ILight light : this.scene.getLights()) {
            if (light.getVector() != null) {
                Vector eyedir =  d.scalarMultiplication(-1.) ;
                Vector lightdir = light.getVector();
                Vector h = (lightdir.addition(eyedir)).norm();

                Point p = (d.scalarMultiplication(shape.intersect(this.scene.getCamera().getLookFrom(), d))).addition(this.scene.getCamera().getLookFrom());
                double t = shape.intersect(p, lightdir);
                if (t>=0)
                    p = (d.scalarMultiplication(t).addition(this.scene.getCamera().getLookFrom()));
                Vector n = (p.substraction(shape.getCenter())).norm();

                sum.addition(light.getColor().scalarMultiplication(Math.max(n.scalarProduct(lightdir), 0)).schurProduct(this.scene.getColors().get("diffuse")).addition(
                        light.getColor().scalarMultiplication(Math.max(n.scalarProduct(h), 0)).schurProduct(this.scene.getColors().get("specular"))
                ));
            }
        }
        return sum;
    }
}
