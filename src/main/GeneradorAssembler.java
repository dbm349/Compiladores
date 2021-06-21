package main;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class GeneradorAssembler{

    private static final String FILENAME = "programa.asm";

    // Recibe una lista de strings a escribir
    public static boolean escribirASM(List<String> lineas, String filename, Boolean append) {

        //String altPath = System.getProperty("user.dir").concat("/").concat(FILENAME); // windows
        String altPath = System.getProperty("user.dir").concat("/").concat(FILENAME);   // linux

        if (filename == null || "".equals(filename)) filename = altPath;
        if (append == null) append = true;
        try (PrintWriter out = new PrintWriter(new FileWriter(filename, append))) {
            for (String linea: lineas) {
                out.println(linea);
            }
            out.println();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
