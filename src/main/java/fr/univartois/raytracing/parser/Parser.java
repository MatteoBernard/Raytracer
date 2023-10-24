package fr.univartois.raytracing.parser;

import fr.univartois.raytracing.lumiere.DirectionalLight;
import fr.univartois.raytracing.lumiere.ILight;
import fr.univartois.raytracing.lumiere.PonctualLight;
import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Triplet;
import fr.univartois.raytracing.numeric.Vector;
import fr.univartois.raytracing.scenery.Camera;
import fr.univartois.raytracing.shape.IShape;
import fr.univartois.raytracing.shape.Plane;
import fr.univartois.raytracing.shape.Sphere;
import fr.univartois.raytracing.shape.Tri;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public final class Parser {

    // Static attribute

    private static final HashMap<String, Integer> expectedParams = new HashMap<String, Integer>() {{
        put("sphere", 4);
        put("vertex", 3);
        put("tri", 3);
        put("plane", 6);
        put("size", 2);
        put("output", 1);
        put("camera", 10);
        put("ambient", 3);
        put("diffuse", 3);
        put("specular", 3);
        put("shininess", 1);
        put("directionnal", 6);
        put("point", 6);
        put("maxverts", 1);
    }};

    // Instance attributes

    private Scanner scanner;
    private HashMap<String, Color> colors;
    private List<IShape> shapes;
    private Integer[] dimensions;
    private List<ILight> lights;
    private Point[] points;
    private int nbPoints;
    private Camera camera;
    private int shininess;
    private int maxverts;
    private String output;

    // Constructor

    /**
     * Initializes a new Parser object.
     * - shapes: A list to store objects.
     * - maxverts: Maximum number of points.
     * - shininess: Shininess integer.
     * - lights: A list to store light.
     * - output: Output file name.
     * - dimensions: An array to store the image dimensions (width, height).
     * - camera: Camera object.
     * - nbPoints: Number of points.
     * - colors: A hashmap of colors(Color objects).
     */
    public Parser() {
        this.shapes = new ArrayList<IShape>();
        this.maxverts = -1;
        this.shininess = -1;
        this.lights = new ArrayList<ILight>();
        this.output = "";
        this.dimensions = new Integer[]{-1, -1};
        this.camera = null;
        this.nbPoints = -1;
        this.colors = new HashMap<String, Color>();
    }

    // Getters

    /**
     * Get the mapping of color names to Color objects.
     * @return A HashMap containing color names and their corresponding Color objects.
     */
    public HashMap<String, Color> getColors() {
        return colors;
    }

    /**
     * Get the list of shape objects.
     * @return A List of IShape objects.
     */
    public List<IShape> getShapes() {
        return shapes;
    }

    /**
     * Get the dimensions of the image (width and height).
     * @return An array of two Integers representing the image dimensions [width, height].
     */
    public Integer[] getDimensions() {
        return dimensions;
    }

    /**
     * Get the list of light objects.
     * @return A List of ILight objects.
     */
    public List<ILight> getLights() {
        return lights;
    }

    /**
     * Get the array of Point objects.
     * @return An array of Point objects.
     */
    public Point[] getPoints() {
        return points;
    }

    /**
     * Get the camera object.
     * @return A Camera object representing the camera properties.
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * Get the shininess coefficient.
     * @return An integer representing the shininess coefficient.
     */
    public int getShininess() {
        return shininess;
    }

    /**
     * Get the maximum number of vertices.
     * @return An integer representing the maximum number of vertices.
     */
    public int getMaxverts() {
        return maxverts;
    }

    /**
     * Get the output file path.
     * @return A string containing the output file path.
     */
    public String getOutput() {
        return output;
    }

    /**
     * Opens a text file for reading.
     *
     * @param fileName The name of the file to be opened.
     *
     * @throws FileNotFoundException If the file specified by 'fileName' is not found.
     */
    private final void openFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        this.scanner = new Scanner(file);
    }

    /**
     * Closes the currently open text file.
     */
    private final void closeFile() {
        this.scanner.close();
    }

    /**
     * Determines whether a line should be read or skipped.
     *
     * @param line The input line to be evaluated.
     *
     * @return 'true' if the line should be read (not empty and does not start with '#'), 'false' otherwise.
     */
    public static boolean toRead(String line) {
        if (line == null || line.isEmpty()) {
            return false;
        }
        return line.charAt(0) != '#';
    }

    /**
     * Adds a camera object based on the input 'parts' array.
     *
     * @param parts An array of string parts containing camera properties.
     */
    private final void addCamera(String[] parts) {
        Point lookFrom = new Point(new Triplet(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])));
        Point lookAt = new Point(new Triplet(Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6])));
        Point up = new Point(new Triplet(Integer.parseInt(parts[7]), Integer.parseInt(parts[8]), Integer.parseInt(parts[9])));
        this.camera = new Camera(lookFrom, lookAt, up, Integer.valueOf(parts[10]));
    }

    /**
     * Adds an ambient color to the 'colors' mapping based on the input 'parts' array.
     *
     * @param parts An array of string parts containing ambient color properties.
     *
     * @throws Exception If the entry is incorrect (e.g., color values exceeding 1).
     */
    private final void addAmbient(String[] parts) throws Exception {
        boolean good = true;
        Color c = new Color(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
        if (colors.containsKey("diffuse")) {
            if (colors.get("diffuse").getTriplet().getX() + Double.parseDouble(parts[1]) > 1 ||
                    colors.get("diffuse").getTriplet().getY() + Double.parseDouble(parts[2]) > 1 ||
                    colors.get("diffuse").getTriplet().getZ() + Double.parseDouble(parts[3]) > 1)
                good = false;
            if (good) {
                colors.put("ambient", c);
            } else {
                throw new Exception("Incorrect entry (ambient)");
            }
        } else {
            colors.put("ambient", c);
        }
    }

    /**
     * Adds a diffuse color to the 'colors' mapping based on the input 'parts' array.
     *
     * @param parts An array of string parts containing diffuse color properties.
     *
     * @throws Exception If the entry is incorrect (e.g., color values exceeding 1).
     */
    private final void addDiffuse(String[] parts) throws Exception {
        boolean good = true;
        Color c = new Color(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
        if (colors.containsKey("ambient")) {
            if (colors.get("ambient").getTriplet().getX() + Double.parseDouble(parts[1]) > 1 ||
                    colors.get("ambient").getTriplet().getY() + Double.parseDouble(parts[2]) > 1 ||
                    colors.get("ambient").getTriplet().getZ() + Double.parseDouble(parts[3]) > 1)
                good = false;
            if (good) {
                colors.put("diffuse", c);
            } else {
                throw new Exception("Incorrect entry (diffuse)");
            }
        } else {
            colors.put("diffuse", c);
        }
    }

    /**
     * Adds a shininess coefficient based on the input 'parts' array.
     *
     * @param parts An array of string parts containing shininess coefficient.
     *
     * @throws Exception If the entry is incorrect (e.g., negative value).
     */
    private final void addShininess(String[] parts) throws Exception {
        int i = Integer.parseInt(parts[1]);
        if (i >= 0)
            this.shininess = i;
        else
            throw new Exception("Incorrect entry (shininess)");
    }

    /**
     * Adds a sphere object based on the input 'parts' array.
     *
     * @param parts An array of string parts containing sphere properties.
     */
    private final void addSphere(String[] parts) {
        this.shapes.add(new Sphere(new Point(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]))), Double.parseDouble(parts[4])));
    }

    /**
     * Adds a triangle (Tri) object based on the input 'parts' array.
     *
     * @param parts An array of string parts containing triangle points.
     */
    private final void addTri(String[] parts) {
        Point p1 = this.points[(Integer.parseInt(parts[1]))];
        Point p2 = this.points[(Integer.parseInt(parts[2]))];
        Point p3 = this.points[(Integer.parseInt(parts[3]))];
        this.shapes.add(new Tri(p1, p2, p3));
    }

    /**
     * Adds a plane object based on the input 'parts' array.
     *
     * @param parts An array of string parts containing plane properties.
     */
    private final void addPlane(String[] parts) {
        this.shapes.add(new Plane(new Point(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]))),
                new Vector(new Triplet(Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Double.parseDouble(parts[6])))));
    }

    /**
     * Processes the content of the input file, parsing and handling different attributes and their corresponding parameters.
     *
     * @throws Exception If there is an issue with the input file, such as incorrect attributes, incorrect argument counts,
     * or unrecognized attributes.
     */
    private final void processFile() throws Exception {
        String line = null;

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();

            if (toRead(line)) {
                String[] parts = line.split("\\s+");
                if (parts.length > 0) {
                    String attribute = parts[0];

                    if (expectedParams.containsKey(attribute)) {
                        int expectedParamCount = expectedParams.get(attribute);

                        if (parts.length - 1 == expectedParamCount) {

                            switch (attribute) {

                                case "size" : {
                                    this.dimensions = new Integer[]{Integer.valueOf(parts[1]), Integer.valueOf(parts[2])};
                                    break;
                                }
                                case "output" : {
                                    this.output = parts[1];
                                    break;
                                }
                                case "camera" : {
                                    addCamera(parts);
                                    break;
                                }
                                case "ambient" : {
                                    addAmbient(parts);
                                    break;
                                }
                                case "diffuse" : {
                                    addDiffuse(parts);
                                    break;
                                }
                                case "specular" : {
                                    colors.put("specular", new Color(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]))));
                                    break;
                                }
                                case "shininess" : {
                                    addShininess(parts);
                                    break;
                                }
                                case "directional" : {
                                    lights.add(new DirectionalLight(new Color(new Triplet(Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Double.parseDouble(parts[6]))),
                                        new Vector(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])))));
                                    break;
                                }
                                case "point" : {
                                    lights.add(new PonctualLight(new Color(new Triplet(Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Double.parseDouble(parts[6]))),
                                            new Point(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])))));
                                    break;
                                }
                                case "maxverts" : {
                                    this.maxverts = Integer.parseInt(parts[1]);
                                    this.points = new Point[this.maxverts];
                                    this.nbPoints = 0;
                                    break;
                                }
                                case "vertex" : {
                                    this.points[nbPoints] = (new Point(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]))));
                                    this.nbPoints ++;
                                    break;
                                }
                                case "sphere" : {
                                    this.addSphere(parts);
                                    break;
                                }
                                case "tri" : {
                                    this.addTri(parts);
                                    break;
                                }
                                case "plane" : {
                                    this.addPlane(parts);
                                    break;
                                }
                                default : {
                                    throw new Exception("Incorrect entry");
                                }
                            }
                        } else {
                            throw new Exception("Incorrect number of arguments for attribute " + attribute + "'.");
                        }
                    } else {
                        throw new Exception("Unrecognized attribut : " + attribute);
                    }
                }
            }
        }
    }

    /**
     * Opens, processes, and closes a text file using the parser.
     *
     * @param fileName The name of the text file to be processed.
     *
     * @throws Exception If there is an issue with the input file, parsing errors, or file handling errors.
     */
    public void useParser(String fileName) throws Exception {
        this.openFile(fileName);
        this.processFile();
        this.closeFile();
    }
}
