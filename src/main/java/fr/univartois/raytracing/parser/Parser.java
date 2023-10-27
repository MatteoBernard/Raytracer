package fr.univartois.raytracing.parser;

import fr.univartois.raytracing.Colors.Checker;
import fr.univartois.raytracing.light.DirectionalLight;
import fr.univartois.raytracing.light.PonctualLight;
import fr.univartois.raytracing.numeric.Color;
import fr.univartois.raytracing.numeric.Point;
import fr.univartois.raytracing.numeric.Triplet;
import fr.univartois.raytracing.numeric.Vector;
import fr.univartois.raytracing.scenery.Camera;
import fr.univartois.raytracing.scenery.SceneryBuilder;
import fr.univartois.raytracing.shadow.ShadowOFF;
import fr.univartois.raytracing.shadow.ShadowON;
import fr.univartois.raytracing.shape.Plane;
import fr.univartois.raytracing.shape.Sphere;
import fr.univartois.raytracing.shape.Tri;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class parses a text file containing scene description and populates a SceneryBuilder
 * with the specified scene elements.
 */
public final class Parser {

    // Static attributes, and its fill

    private static final HashMap<String, Integer> expectedParams = new HashMap<>();

    static {
        expectedParams.put("sphere", 4);
        expectedParams.put("vertex", 3);
        expectedParams.put("tri", 3);
        expectedParams.put("plane", 6);
        expectedParams.put("size", 2);
        expectedParams.put("output", 1);
        expectedParams.put("camera", 10);
        expectedParams.put("ambient", 3);
        expectedParams.put("diffuse", 3);
        expectedParams.put("specular", 3);
        expectedParams.put("shininess", 1);
        expectedParams.put("directional", 6);
        expectedParams.put("point", 6);
        expectedParams.put("maxverts", 1);
    }

    // Instance attributes

    private Scanner scanner;
    private Color ambient;
    private Color diffuse;
    private Color specular;
    private Point[] points;
    private int nbPoints;
    private int shininess;
    private int maxverts;
    private String output;
    private SceneryBuilder sceneryBuilder;

    // Constructor

    /**
     * Initializes a new Parser object.
     * - maxverts: Maximum number of points.
     * - shininess: Shininess integer.
     * - output: Output file name.
     * - nbPoints: Number of points.
     * - colors: A hashmap of colors(Color objects).
     */
    public Parser() {
        this.ambient = new Color(new Triplet(0, 0, 0));
        this.diffuse = new Color(new Triplet(0, 0, 0));
        this.specular = new Color(new Triplet(0, 0, 0));
        this.shininess = 45;
        this.output = "output.png";
    }

    // Getters

    /**
     * Get the shininess coefficient.
     * @return An integer representing the shininess coefficient.
     */
    public int getShininess() {
        return shininess;
    }

    /**
     * Get the output file path.
     * @return A string containing the output file path.
     */
    public String getOutput() {
        return output;
    }

    /**
     * Get the SceneryBuilder object.
     * @return A SceneryBuilder containing all datas needed to build the Scenery.
     */
    public SceneryBuilder getSceneryBuilder() {
        return sceneryBuilder;
    }

    // Process file methods

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
    private final void addChecker(String[] parts) {
        Color col1 = new Color(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
        Color col2 = new Color(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
        this.sceneryBuilder.setChecker(new Checker(null,col1,col2));
    }

    private final void addCamera(String[] parts) {
        Point lookFrom = new Point(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
        Point lookAt = new Point(new Triplet(Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Double.parseDouble(parts[6])));
        Vector up = new Vector(new Triplet(Double.parseDouble(parts[7]), Double.parseDouble(parts[8]), Double.parseDouble(parts[9])));
        this.sceneryBuilder.setCamera(new Camera(lookFrom, lookAt, up, Integer.valueOf(parts[10])));
    }

    /**
     * Adds an ambient color to the 'colors' mapping based on the input 'parts' array.
     *
     * @param parts An array of string parts containing ambient color properties.
     *
     * @throws ParserException If the entry is incorrect (e.g., color values exceeding 1).
     */
    private final void addAmbient(String[] parts) throws ParserException {
        if (ambient == null) {
            this.ambient = new Color(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
        }
        else throw new ParserException("Incorrect entry (ambient already defined)");
    }

    /**
     * Adds a diffuse color to the 'colors' mapping based on the input 'parts' array.
     *
     * @param parts An array of string parts containing diffuse color properties.
     *
     * @throws ParserException If the entry is incorrect (e.g., color values exceeding 1).
     */
    private final void addDiffuse(String[] parts) throws ParserException {
        boolean good = true;
        Color c = new Color(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
        if (ambient != null) {
            if (ambient.getTriplet().getX() + Double.parseDouble(parts[1]) > 1 ||
                   ambient.getTriplet().getY() + Double.parseDouble(parts[2]) > 1 ||
                    ambient.getTriplet().getZ() + Double.parseDouble(parts[3]) > 1)
                good = false;
            if (good) {
                diffuse = c;
            } else
                throw new ParserException("Incorrect entry (diffuse)");
        } else {
            diffuse = c;
        }
    }

    /**
     * Adds a shininess coefficient based on the input 'parts' array.
     *
     * @param parts An array of string parts containing shininess coefficient.
     *
     * @throws ParserException If the entry is incorrect (e.g., negative value).
     */
    private final void addShininess(String[] parts) throws ParserException {
        int i = Integer.parseInt(parts[1]);
        if (i >= 0)
            this.shininess = i;
        else
            throw new ParserException("Incorrect entry (shininess)");
    }

    /**
     * Adds a new light to the scene. Ensures that the sum of the R, G, and B components
     * of the light's color and the colors of existing lights in the scene do not exceed 1.
     *
     * @param parts An array containing information about the new light, including its type, color,
     *              and direction or position.
     *
     * @throws ParserException If the sum of the R, G, or B components of the new light and existing lights
     *                   exceeds 1, indicating an incorrect entry.
     */
    private final void addLight(String[] parts) throws ParserException {

        double r = Double.parseDouble(parts[4]);
        double g = Double.parseDouble(parts[5]);
        double b = Double.parseDouble(parts[6]);

        for (int i = 0 ; i < this.sceneryBuilder.getLights().size() ; i++) {
            r += this.sceneryBuilder.getLights().get(i).getColor().getTriplet().getX();
            g += this.sceneryBuilder.getLights().get(i).getColor().getTriplet().getY();
            b += this.sceneryBuilder.getLights().get(i).getColor().getTriplet().getZ();
        }

        if (r > 1 || g > 1 || b > 1)
            throw new ParserException("Incorrect entry (light values > 1)");
        else

            if (parts[0].equals("directional"))
                this.sceneryBuilder.addLight(new DirectionalLight(new Color(new Triplet(Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Double.parseDouble(parts[6]))),
                        new Vector(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])))));
            else
                this.sceneryBuilder.addLight(new PonctualLight(new Color(new Triplet(Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Double.parseDouble(parts[6]))),
                        new Point(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])))));
    }

    /**
     * Adds a sphere object based on the input 'parts' array.
     *
     * @param parts An array of string parts containing sphere properties.
     */
    private final void addSphere(String[] parts) {
        this.sceneryBuilder.addShape(new Sphere(new Point(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]))), Double.parseDouble(parts[4]), diffuse, specular, shininess));
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
        this.sceneryBuilder.addShape(new Tri(p1, p2, p3, diffuse, specular, shininess));
    }

    /**
     * Adds a plane object based on the input 'parts' array.
     *
     * @param parts An array of string parts containing plane properties.
     */
    private final void addPlane(String[] parts) {
        this.sceneryBuilder.addShape(new Plane(new Point(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]))),
                new Vector(new Triplet(Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Double.parseDouble(parts[6]))), diffuse, specular, shininess));
    }

    /**
     * Sets the value of the sceneryBuilder.stateShadow attribute based on the input 'parts' array.
     *
     * @param parts An array of string parts containing shadow property.
     *
     * @throws ParserException If the entry is incorrect (others values than true or false).
     */
    private final void setActiveShadow(String[] parts) throws ParserException {
        if (parts[1].equals("true"))
            this.sceneryBuilder.setShadowState(ShadowON.getInstance());
        else if (parts[1].equals("false"))
            this.sceneryBuilder.setShadowState(ShadowOFF.getInstance());
        else
            throw new ParserException("Incorrect entry (shadow)");
    }

    /**
     * Processes the entire file, reading lines and handling attribute assignments.
     *
     * @throws ParserException If there are errors in the parsing process.
     */
    private final void processFile() throws ParserException {
        String line = null;

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();

            if (toRead(line)) {
                processLine(line);
            }
        }
    }

    /**
     * Processes a single line from the input file and assigns attributes.
     *
     * @param line The line to be processed.
     *
     * @throws ParserException If there are errors in the parsing process.
     */
    private void processLine(String line) throws ParserException {
        String[] parts = line.split("\\s+");
        if (parts.length > 0) {
            String attribute = parts[0];

            if (expectedParams.containsKey(attribute)) {
                int expectedParamCount = expectedParams.get(attribute);

                if (parts.length - 1 == expectedParamCount) {
                    processAttribute(attribute, parts);
                } else {
                    throw new ParserException("Incorrect number of arguments for attribute '" + attribute + "'.");
                }
            } else {
                throw new ParserException("Unrecognized attribute: " + attribute);
            }
        }
    }

    /**
     * Processes a specific attribute and assigns its value based on the attribute type.
     *
     * @param attribute The attribute identifier.
     * @param parts     The array of attribute components.
     *
     * @throws ParserException If there are errors in the parsing process.
     */
    private void processAttribute(String attribute, String[] parts) throws ParserException {
        switch (attribute) {
            case "size":
                this.sceneryBuilder = new SceneryBuilder(Integer.valueOf(parts[1]), Integer.valueOf(parts[2]));
                break;
            case "output":
                this.output = parts[1];
                break;
            case "camera":
                addCamera(parts);
                break;
            case "ambient":
                addAmbient(parts);
                break;
            case "diffuse":
                addDiffuse(parts);
                break;
            case "specular":
                this.specular = new Color(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
                break;
            case "shininess":
                addShininess(parts);
                break;
            case "directional", "point":
                this.addLight(parts);
                break;
            case "maxverts":
                this.maxverts = Integer.parseInt(parts[1]);
                this.points = new Point[this.maxverts];
                this.nbPoints = 0;
                break;
            case "vertex":
                if (this.nbPoints < this.maxverts) {
                    this.points[nbPoints] = (new Point(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]))));
                    this.nbPoints++;
                } else {
                    throw new ParserException("Incorrect entry (too many points than expected)");
                }
                break;
            case "sphere":
                this.addSphere(parts);
                break;
            case "tri":
                this.addTri(parts);
                break;
            case "plane":
                this.addPlane(parts);
                break;
            case "shadow":
                this.setActiveShadow(parts);
                break;
            default:
                throw new ParserException("Incorrect entry");
        }
    }

    /**
     * Opens, processes, and closes a text file using the parser.
     *
     * @param fileName The name of the text file to be processed.
     *
     * @throws FileNotFoundException If there is an issue with the input file, parsing errors, or file handling errors.
     */
    public void useParser(String fileName) throws FileNotFoundException {
        this.openFile(fileName);
        this.processFile();
        this.sceneryBuilder.setAmbient(ambient);
        this.closeFile();
    }
}
