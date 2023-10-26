package fr.univartois.raytracing.antiCrenelage;

import fr.univartois.raytracing.numeric.Vector;
import java.util.List;


public interface ICrenelage {

    List<Vector> caclulVector(double realWidth, double pixelWidth, double realHeight, double pixelHeight,int i, int j, Vector u, Vector v, Vector w);
}
