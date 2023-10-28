package fr.univartois.raytracing.colors;

import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Vector;
import fr.univartois.raytracing.scenery.Scenery;
import fr.univartois.raytracing.shape.IShape;


public interface ICalcul {

    Color colorCalcul(IShape shape, Vector d, double t);

    Scenery getScene();

}
