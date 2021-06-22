package main.services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TablaService {

    private String tableFile;
    private List<String> claves;

    public TablaService(String tableFile) {
        this.tableFile = tableFile;
    }

    public String generarASM () throws IOException {
        File file = new File(tableFile);
        claves = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(file);
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replace("\"", "");
                String[] data = line.split(",");
                if (! claves.contains(data[0])) {
                    claves.add(data[0]);
                    String value = "";
                    if (data.length < 4) {
                        value  = " DW ?";
                        data[0] = "@__" + data[0];
                        data[0] = data[0].replace("<","@menor").replace(">","@mayor");
                    } else {
                        value = data[3];
                        switch (data[1]) {
                            case "Const_String":
                                data[0] = data[0].replace("<","@menor").replace(">","@mayor");
                                value = " db \"" + value + "\"" + ", " + value.length() + ", " + value.length() + ", '$'";
                                break;
                            case "Numero":
                                value = " dw " + value;
                                break;
                            case "Real":
                                value = " dw " + value.replace(".", ",");
                                break;
                            default:
                                value=" dw ?";
                                break;
                        }
                    }
                    data[0] = data[0].replace(" ", "");
                    data[0] = data[0].replace(".", "punto");
                    data[0] = data[0].replace(",","coma");
                    resultStringBuilder.append(data[0]).append(value).append("\n");
                }
            }
        }
        inputStream.close();
        return resultStringBuilder.toString();
    }
}
