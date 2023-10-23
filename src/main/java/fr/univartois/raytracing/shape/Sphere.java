package fr.univartois.raytracing.shape;

import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Vector;

import static java.lang.Math.cos;
import static java.lang.Math.sqrt;

public class Sphere implements IShape{
    Point point;
    double rayon;

    public Sphere(Point point, double rayon) {
        this.point = point;
        this.rayon = rayon;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public double getRayon() {
        return rayon;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }

    public double intersect (Point o, Vector d) {
        double a = 1;
        double b = ((o.substraction(point)).scalarMultiplication(2)).scalarProduct(d);
        double c = (o.substraction(point)).scalarProduct(o.substraction(point))-(rayon*rayon);

        double delta = (b*b)-(4*a*c);
        if (delta < 0) return -1;
        else if (delta == 0) return ((b*-1)/(2*a));
        else {
            double t = (-b + sqrt(delta))/(2*a);
            if (t>=0) return t;
            else return (-b - sqrt(delta))/(2*a);
        }
    }
}
