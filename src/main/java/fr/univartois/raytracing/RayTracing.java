package fr.univartois.raytracing;


import fr.univartois.raytracing.Colors.ICalcul;
import fr.univartois.raytracing.Colors.Lambert;
import fr.univartois.raytracing.Colors.Normal;
import fr.univartois.raytracing.antiCrenelage.Grid;
import fr.univartois.raytracing.antiCrenelage.ICrenelage;
import fr.univartois.raytracing.antiCrenelage.Middle;
import fr.univartois.raytracing.antiCrenelage.random;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Triplet;
import fr.univartois.raytracing.numeric.Vector;
import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.parser.Parser;
import fr.univartois.raytracing.scenery.Scenery;
import fr.univartois.raytracing.scenery.SceneryBuilder;
import fr.univartois.raytracing.Colors.BlinnPhong;
import fr.univartois.raytracing.shape.IShape;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RayTracing {

    public void launch (Scenery scene,String output) {
        ICalcul calculMethod;
        calculMethod = new BlinnPhong(new Lambert(new Normal(scene)));
        Vector d = null; 

        BufferedImage image = new BufferedImage(scene.getX(),scene.getY(),BufferedImage.TYPE_INT_RGB);
        Point lookFrom = scene.getCamera().getLookFrom();
        IShape currentShape = null;

        double min,t,fovr,realHeight,realWidth,pixelHeight,pixelWidth,a,b;
        Vector w,up,u,v;

        w = (scene.getCamera().getLookFrom()).substraction(scene.getCamera().getLookAt());
        w = w.norm();

        up = scene.getCamera().getUp();
        u = up.vectorProduct(w);
        u = u.norm();

        v = w.vectorProduct(u);
        v = v.norm();
        int size = 0;
        List<Vector> D = new ArrayList<>();

        fovr = (scene.getCamera().getFov()*Math.PI)/180;
        realHeight = 2* Math.tan(fovr/2);

        for (int i=0; i < scene.getX(); i++) {
            for (int j=0; j < scene.getY(); j++) {
                min = scene.getX()*scene.getY();
                for (IShape shape : scene.getShapes()) {

                    pixelHeight = realHeight/scene.getY();
                    realWidth = scene.getX() * pixelHeight;
                    pixelWidth = realWidth/scene.getX();
                    ICrenelage crenelage = new Grid(6);
                    D = crenelage.caclulVector(realWidth,pixelWidth,realHeight,pixelHeight,i,j,u,v,w);
                    size = D.size();


                    t = shape.intersect(lookFrom,D.get(0));
                    if (t < min && t != -1) {
                        min = t;
                        currentShape=shape;
                    }
                }

                int rgbValue = 0;
                if (min != scene.getX()*scene.getY()) {
                    for(int x =0;x<size;x++){
                        Color col = calculMethod.colorCalcul(currentShape,D.get(x));
                        float red = (float) (col.getTriplet().getX());
                        float green = (float) (col.getTriplet().getY());
                        float blue = (float) (col.getTriplet().getZ());
                        java.awt.Color color = new java.awt.Color(red,blue,green);

                        rgbValue += color.getRGB()/size;


                    }

                    image.setRGB(i,j,rgbValue);}
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
        p.useParser("src/main/resources/generators/1st3dtest.txt");
        SceneryBuilder build = p.getSceneryBuilder();

        Scenery scene = new Scenery(build.getCamera(),build.getLights(),build.getShapes(),build.getX(),build.getY(),build.getShadowState(), build.getAmbient());

        RayTracing rt = new RayTracing();
        rt.launch(scene,p.getOutput());
    }
}
