/**
 * ICalcul is an interface define the methode for color calculations.
 * We use it to calculate the colors of objects and get the scenery.
 *
 * @author antoine_crauser
 */
package fr.univartois.raytracing.colors;

import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Vector;
import fr.univartois.raytracing.scenery.Scenery;
import fr.univartois.raytracing.shape.IShape;


public interface ICalcul {

     /**
      * It calculates the color of a shape.
      *
      * @param shape is the shape for which we calculate the color.
      * @param d is the vector that allow to calculate the intersection point with the shape.
      * @param t is the double used for calculations.
      * @return The color of the shape with Lambert method for example.
      */

     Color colorCalcul(IShape shape, Vector d, double t);

     /**
      * Returns the Scenery.
      *
      * @return The scene.
      */

     Scenery getScene();

}
