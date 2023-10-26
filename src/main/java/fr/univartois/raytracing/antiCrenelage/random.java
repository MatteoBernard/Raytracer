package fr.univartois.raytracing.antiCrenelage;

import fr.univartois.raytracing.numeric.Vector;

import java.util.ArrayList;
import java.util.List;

public class random implements ICrenelage{
    int nb;
    List<Vector> D= new ArrayList<>();


    public random(int nb) {
        this.nb = nb;
    }

    @Override
    public List<Vector> caclulVector(double realWidth, double pixelWidth, double realHeight, double pixelHeight, int i, int j, Vector u, Vector v, Vector w) {
        for(int x =0;x<this.nb;x++){
            double y = Math.random();
            double z = Math.random();
            double a = -(realWidth / 2) + (i + y) * pixelWidth;
            double b = (realHeight/2)-(j+z)*pixelHeight;
            Vector d = ((u.scalarMultiplication(a))
                    .addition(v.scalarMultiplication(b)))
                    .substraction(w);
            d = d.norm();
            this.D.add(d);

        }
        return this.D;
    }
}
