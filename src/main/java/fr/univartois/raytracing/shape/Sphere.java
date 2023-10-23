package fr.univartois.raytracing.shape;

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
}
