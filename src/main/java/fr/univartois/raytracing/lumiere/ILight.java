package fr.univartois.raytracing.lumiere;

import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;

public interface ILight {
    Point getCoord();

    Color getColor();
}
