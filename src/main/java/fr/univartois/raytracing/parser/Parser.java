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
import java.util.*;

public final class Parser {

    private Scanner scanner;
    private HashMap<String, Integer> expectedParams;
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
    public Parser() {
        expectedParams = new HashMap<String, Integer>();
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
        expectedParams.put("directionnal", 6);
        expectedParams.put("point", 6);
        expectedParams.put("maxverts", 1);

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

    public HashMap<String, Color> getColors() {
        return colors;
    }

    public List<IShape> getShapes() {
        return shapes;
    }

    public Integer[] getDimensions() {
        return dimensions;
    }

    public List<ILight> getLights() {
        return lights;
    }

    public Point[] getPoints() {
        return points;
    }

    public Camera getCamera() {
        return camera;
    }

    public int getShininess() {
        return shininess;
    }

    public int getMaxverts() {
        return maxverts;
    }

    public String getOutput() {
        return output;
    }

    public void openFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        this.scanner = new Scanner(file);
    }
    public void closeFile() {
        this.scanner.close();
    }

    public static boolean toRead(String line) {
        if (line == null || line.isEmpty()) {
            return false;
        }
        return line.charAt(0) != '#';
    }

    public void addCamera(String[] parts) {
        Point lookFrom = new Point(new Triplet(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])));
        Point lookAt = new Point(new Triplet(Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6])));
        Point up = new Point(new Triplet(Integer.parseInt(parts[7]), Integer.parseInt(parts[8]), Integer.parseInt(parts[9])));
        this.camera = new Camera(lookFrom, lookAt, up, Integer.valueOf(parts[10]));
    }

    public void addAmbient(String[] parts) throws Exception {
        boolean good = true;
        Color c = new Color(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
        if (colors.containsKey("diffuse")) {
            if (colors.get("diffuse").getTriplet().getX() + Double.parseDouble(parts[1]) >1 ||
                    colors.get("diffuse").getTriplet().getY() + Double.parseDouble(parts[2]) >1 ||
                    colors.get("diffuse").getTriplet().getZ() + Double.parseDouble(parts[3]) >1)
                good = false;
            if (good) {
                colors.put("ambient", c);
            }
            else
                throw new Exception("Incorrect entry (ambient)");
        }
        else {
            colors.put("ambient", c);
        }
    }

    public void addDiffuse(String[] parts) throws Exception {
        boolean good = true;
        Color c = new Color(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
        if (colors.containsKey("ambient")) {
            if (colors.get("ambiant").getTriplet().getX() + Double.parseDouble(parts[1]) >1 ||
                    colors.get("ambiant").getTriplet().getY() + Double.parseDouble(parts[2]) >1 ||
                    colors.get("ambiant").getTriplet().getZ() + Double.parseDouble(parts[3]) >1)
                good = false;
            if (good) {
                colors.put("diffuse", c);
            }
            else
                throw new Exception("Incorrect entry (diffuse)");
        }
        else {
            colors.put("diffuse", c);
        }
    }

    public void addShininess(String[] parts) throws Exception {
        int i = Integer.parseInt(parts[1]);
        if (i >= 0)
            this.shininess = i;
        else
            throw new Exception("Incorrect entry (shininess)");
    }

    public void addSphere(String[] parts) {
        this.shapes.add(new Sphere(new Point(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]))), Double.parseDouble(parts[4])));
    }

    public void addTri(String[] parts) {
        Point p1 = this.points[(Integer.parseInt(parts[1]))];
        Point p2 = this.points[(Integer.parseInt(parts[2]))];
        Point p3 = this.points[(Integer.parseInt(parts[3]))];
        this.shapes.add(new Tri(p1, p2, p3));
    }

    public void addPlane(String[] parts) {
        this.shapes.add(new Plane(new Point(new Triplet(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]))),
                new Vector(new Triplet(Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Double.parseDouble(parts[6])))));
    }

    public void processFile() throws Exception {
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
                                    addPlane(parts);
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
}
