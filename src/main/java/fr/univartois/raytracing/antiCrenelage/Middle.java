package fr.univartois.raytracing.antiCrenelage;

import fr.univartois.raytracing.numeric.Vector;

import java.util.ArrayList;
import java.util.List;

public class Middle implements ICrenelage{
    List<Vector> D= new ArrayList<>();
    @Override
    public List<Vector> caclulVector(double realWidth, double pixelWidth, double realHeight, double pixelHeight,int i, int j,Vector u, Vector v, Vector w) {
        double a = -(realWidth / 2) + (i + 0.5) * pixelWidth;
        double b = (realHeight/2)-(j+0.5)*pixelHeight;
        Vector d = ((u.scalarMultiplication(a))
                .addition(v.scalarMultiplication(b)))
                .substraction(w);
        d = d.norm();
        this.D.add(d);
        return this.D;
    }
}
