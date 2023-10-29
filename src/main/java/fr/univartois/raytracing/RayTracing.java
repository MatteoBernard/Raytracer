package fr.univartois.raytracing;


import fr.univartois.raytracing.colors.*;
import fr.univartois.raytracing.antiCrenelage.Grid;
import fr.univartois.raytracing.antiCrenelage.ICrenelage;
import fr.univartois.raytracing.antiCrenelage.Middle;
import fr.univartois.raytracing.antiCrenelage.Random;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Vector;
import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.parser.Parser;
import fr.univartois.raytracing.scenery.Scenery;
import fr.univartois.raytracing.scenery.SceneryBuilder;
import fr.univartois.raytracing.colors.BlinnPhong;
import fr.univartois.raytracing.shape.IShape;
import fr.univartois.raytracing.shape.Plane;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The RayTracing class is responsible for rendering an image using the Ray Tracing technique.
 */
public class RayTracing {

    /**
     * Launches the rendering process for a given scene and saves the image to the specified output file.
     *
     * @param scene   The scenery to render.
     * @param output  The name of the output image file.
     */
    public void launch (Scenery scene, String output) {
        ICalcul calculMethod;
        Checker checker = scene.getChecker();
        calculMethod = initializeCalculMethod(scene);

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

                    ICrenelage crenelage;

                    if (scene.getState()[0] == 0)
                        crenelage = new Middle();
                    else if (scene.getState()[0] == 1)
                        crenelage = new Random(scene.getState()[1]);
                    else
                        crenelage = new Grid(scene.getState()[1]);

                    D = crenelage.caclulVector(realWidth,pixelWidth,realHeight,pixelHeight,i,j,u,v,w);
                    size = D.size();
                    try {
                        t = shape.intersect(lookFrom, D.get(0));
                    }
                    catch (UnsupportedOperationException e){
                        t = -1;
                    }
                    if (t < min && t != -1) {
                        min = t;
                        currentShape=shape;
                    }
                }

                int rgbValue = 0;
                if (min != scene.getX()*scene.getY()) {
                    if (checker != null && currentShape instanceof Plane) {
                        Color col = checker.colorCalcul(currentShape,D.get(0),min);
                        float red = (float) (col.getTriplet().getX());
                        float green = (float) (col.getTriplet().getY());
                        float blue = (float) (col.getTriplet().getZ());
                        java.awt.Color color = new java.awt.Color(red, blue, green);
                        rgbValue = color.getRGB();
                    }
                    else {
                        for (int x = 0; x < size; x++) {
                            Color col = calculMethod.colorCalcul(currentShape, D.get(x), min);
                            float red = (float) (col.getTriplet().getX());
                            float green = (float) (col.getTriplet().getY());
                            float blue = (float) (col.getTriplet().getZ());
                            java.awt.Color color = new java.awt.Color(red, blue, green);

                            rgbValue += color.getRGB() / size;
                        }
                    }

                    image.setRGB(i,j,rgbValue);}
                else {
                    image.setRGB(i,j,0);}
            }
        }
        this.saveImageToOutput(image, output);
    }

    /**
     * Saves the provided image to the specified output file in PNG format.
     *
     * @param image   The image to save.
     * @param output  The name of the output image file.
     */
    private void saveImageToOutput(BufferedImage image, String output) {
        try {
            // Save the image to the output file.
            File outputfile = new File("src/main/resources/images/" + output);
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            System.err.println("Error");
        }
    }

    /**
     * Initializes the calculation method for rendering based on the scene.
     *
     * @param scene  The scene for which rendering calculations will be performed.
     * @return       The initialized calculation method.
     */
    private ICalcul initializeCalculMethod(Scenery scene) {
        return new BlinnPhong(new Lambert(new Normal(scene)));
    }
}
