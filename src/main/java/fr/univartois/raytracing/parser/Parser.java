package fr.univartois.raytracing.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Parser {

    private Scanner scanner;
    public void openFile(String fileName) throws FileNotFoundException {
        File file =  new File(fileName);
        this.scanner = new Scanner(file);
    }

    public void closeFile() {
        this.scanner.close();
    }

}
