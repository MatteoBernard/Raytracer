package fr.univartois.raytracing;

import fr.univartois.raytracing.parser.Parser;
import fr.univartois.raytracing.scenery.Scenery;
import fr.univartois.raytracing.scenery.SceneryBuilder;

/**
 * The Main class is the entry point of the ray tracing application. It reads input data from a configuration file,
 * constructs the scene using the provided parameters, and then launches the ray tracing process.
 */
public class Main {

    /**
     * The main method of the ray tracing application.
     *
     * @param args An array of command-line arguments (not used in this application).
     * @throws Exception If an error occurs during the execution.
     */
    public static void main(String[] args) throws Exception {
        // Create a Parser instance to read configuration data.
        Parser parser = new Parser();

        // Use the parser to read the configuration file (e.g., "lambert.txt").
        parser.useParser("src/main/resources/generators/lambert.txt");

        // Build the scenery using the parsed data.
        SceneryBuilder build = parser.getSceneryBuilder();
        Scenery scene = new Scenery(build.getCamera(), build.getLights(), build.getShapes(), build.getX(), build.getY(),
                build.getShadowState(), build.getAmbient(), build.getState(), build.getChecker());

        // Create a RayTracing instance and launch the ray tracing process, specifying the output location.
        RayTracing rt = new RayTracing();
        rt.launch(scene, parser.getOutput());
    }
}

