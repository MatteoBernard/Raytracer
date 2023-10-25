package fr.univartois.raytracing;


import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Triplet;
import fr.univartois.raytracing.numeric.Vector;
import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.parser.Parser;
import fr.univartois.raytracing.scenery.Scenery;
import fr.univartois.raytracing.scenery.SceneryBuilder;
import fr.univartois.raytracing.shape.IShape;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RayTracing {

    public void launch (Scenery scene,String output) {
        BufferedImage image = new BufferedImage(scene.getX(),scene.getY(),BufferedImage.TYPE_INT_RGB);
        Point lookFrom = scene.getCamera().getLookFrom();

        double min,t,fovr,realHeight,realWidth,pixelHeight,pixelWidth,a,b;
        Vector w,up,u,v,d;

        w = (scene.getCamera().getLookFrom()).substraction(scene.getCamera().getLookAt());
        w = w.norm();

        up = scene.getCamera().getUp();
        u = up.vectorProduct(w);
        u = u.norm();

        v = w.vectorProduct(u);
        v = v.norm();

        fovr = (scene.getCamera().getFov()*Math.PI)/180;
        realHeight = 2* Math.tan(fovr/2);

        for (int i=0; i < scene.getX(); i++) {
            for (int j=0; j < scene.getY(); j++) {
                min = scene.getX()*scene.getY();
                for (IShape shape : scene.getShapes()) {

                    pixelHeight = realHeight/scene.getY();
                    realWidth = scene.getX() * pixelHeight;
                    pixelWidth = realWidth/scene.getX();

                    a = -(realWidth/2)+(i+0.5)*pixelWidth;
                    b = (realHeight/2)-(j+0.5)*pixelHeight;


                    d = ((u.scalarMultiplication(a)).addition(v.scalarMultiplication(b))).substraction(w);
                    d = d.norm();


                    t = shape.intersect(lookFrom,d);
                    if (t < min && t != -1) {
                        min = t;
                    }
                }

                Color col;
                if (min != scene.getX()*scene.getY()) {
                    col = scene.getColors().get("ambient");
                    image.setRGB(i,j,
                            ((int)col.getTriplet().getX()*255)*65536+
                                    ((int)col.getTriplet().getY()*255)*256+
                                    ((int)col.getTriplet().getZ()*255)
                    );
                }
                else {
                    image.setRGB(i,j,0);
                }
            }
        }
        try {
            // Retrieve image
            File outputfile = new File("src/main/resources/images/"+output);
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            System.err.println("erreur");
        }
    }


    public static void main(String[] args) throws Exception {
        Parser p = new Parser();
        p.useParser("src/main/resources/generators/tri.txt");
        SceneryBuilder build = p.getSceneryBuilder();

        Scenery scene = new Scenery(build.getCamera(),build.getLights(),build.getShapes(),build.getColors(),build.getX(),build.getY());

        RayTracing rt = new RayTracing();
        rt.launch(scene,p.getOutput());
    }
}
