package fr.univartois.raytracing.lumiere;

import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Vector;

public interface ILight {
    Vector getVector();

    Color getColor();
}
