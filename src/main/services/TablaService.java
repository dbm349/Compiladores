package main.services;

import java.io.*;

public class TablaService {

    private String tableFile;

    public TablaService(String tableFile) {
        this.tableFile = tableFile;
    }

    public String generarASM () throws IOException {
        File file = new File(tableFile);
        FileInputStream inputStream = new FileInputStream(file);
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replace("\"", "");
                String[] data = line.split(",");
                String value = "";
                if (data.length < 4) {
                    value  = " DW ?";
                } else {
                    value = data[3];
                    switch (data[1]) {
                        case "Const_String":
                            value = " db \"" + value + "\"" + ", " + value.length() + ", " + value.length() + ", '$'";
                            break;
                        case "Numero":
                            value = " dw " + value;
                            break;
                        default:
                            value=" dw ?";
                            break;
                    }
                }
                data[0] = data[0].replace(" ", "");
                data[0] = data[0].replace(".", "punto");
                resultStringBuilder.append(data[0]).append(value).append("\n");
            }
        }
        inputStream.close();
        return resultStringBuilder.toString();
    }
}
