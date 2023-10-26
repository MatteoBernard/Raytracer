package fr.univartois.raytracing.antiCrenelage;

import fr.univartois.raytracing.numeric.Vector;

import java.util.ArrayList;
import java.util.List;

public class Grid implements ICrenelage{

    int nb;
    List<Vector> D= new ArrayList<>();


    public Grid(int nb) {
        this.nb = nb;
    }
    @Override
    public List<Vector> caclulVector(double realWidth, double pixelWidth, double realHeight, double pixelHeight, int i, int j, Vector u, Vector v, Vector w) {
        double valx = 1/(this.nb*2);
        for(int x =0;x<this.nb;x++){
            double valy = 1/(this.nb*2);
            for(int y = 0;y<this.nb;y++) {
                double a = -(realWidth / 2) + (i + valx) * pixelWidth;
                double b = (realHeight / 2) - (j + valy) * pixelHeight;
                Vector d = ((u.scalarMultiplication(a))
                        .addition(v.scalarMultiplication(b)))
                        .substraction(w);
                d = d.norm();
                this.D.add(d);
                valy+=1/this.nb;

            }
            valx+=1/this.nb;
        }
        return this.D;
    }
}
