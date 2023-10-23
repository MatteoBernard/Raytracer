package fr.univartois.raytracing;

import fr.univartois.raytracing.lumiere.ILight;
import fr.univartois.raytracing.lumiere.PonctualLight;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Triplet;
import fr.univartois.raytracing.numeric.Vector;
import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.scenery.Camera;
import fr.univartois.raytracing.scenery.Scenery;
import fr.univartois.raytracing.shape.IShape;
import fr.univartois.raytracing.shape.Sphere;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SphereRayTracing {

    public void launch (Scenery scene) {
        BufferedImage image = new BufferedImage(scene.getX(),scene.getY(),BufferedImage.TYPE_INT_RGB);
        Point lookFrom = scene.getCamera().getLookFrom();
        Point lookAt = scene.getCamera().getLookAt();
        Vector w = lookFrom.substraction(lookAt);
        Vector dNorm = w.norm();
        Vector d = new Vector(new Triplet(
                w.getTriplet().getX()/dNorm.getTriplet().getX(),
                w.getTriplet().getY()/dNorm.getTriplet().getY(),
                w.getTriplet().getZ()/dNorm.getTriplet().getZ()
        ));

        double min = scene.getX()*scene.getY();
        double t;

        for (int i=0; i <= scene.getX(); i++) {
            for (int j=0; j <= scene.getY(); j++) {
                for (IShape shape : scene.getShapes()) {
                    if (shape instanceof Sphere) {
                        t = shape.intersect(lookFrom,d);
                        if (t != -1 && t < min) min = t;
                    }
                }

                Color col;
                Point ptAc = new Point(new Triplet(i,j,0));
                if (min != scene.getX()*scene.getY()) {
                    Point p = d.addition(scene.getCamera().getLookFrom());
                    p = p.scalarMultiplication(min);
                    col = scene.getLights().get(1).getColor();
                    image.setRGB(i,j,
                            (int)col.getTriplet().getX()+
                            (int)col.getTriplet().getY()+
                            (int)col.getTriplet().getZ()
                    );
                }
                else
                    col = new Color(new Triplet(0,0,0));
                    image.setRGB(i,j,0);
            }
        }
        try {
            // Retrieve image
            File outputfile = new File("output.png");
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            System.err.println("erreur");
        }
    }


    public static void main(String[] args) {
        Camera camera = new Camera(
                new Point(new Triplet(0,0,0)),
                new Point(new Triplet(0,0,0)),
                new Point(new Triplet(0,0,0)),
                1
        );
        Scenery scene = new Scenery(
                camera,
                new ArrayList<ILight>(),
                new ArrayList<IShape>(),
                640,
                480
        );

        SphereRayTracing rt = new SphereRayTracing();
        rt.launch(scene);
    }
}
