package fr.univartois.raytracing.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Parser {

    private Scanner scanner;

    private static HashMap<String, Integer> expectedParams;

    private Parser() {
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
    }

    public void openFile(String fileName) throws FileNotFoundException {
        File file =  new File(fileName);
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

    public void processFile() {
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

                            System.out.println("Attribut : " + attribute);
                            for (int i = 1; i < parts.length; i++) {
                                double arg = Double.parseDouble(parts[i]);
                                System.out.println("Argument " + i + " : " + arg);
                            }
                        } else {
                            System.out.println("Nombre incorrect d'arguments pour l'attribut '" + attribute + "'.");
                        }
                    } else {
                        System.out.println("Attribut non reconnu : " + attribute);
                    }
                }
            }
        }
    }
}
