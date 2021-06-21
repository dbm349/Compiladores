package main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Tabla extends JFrame {

    private JPanel contentPane;

    // Columnas de la tabla de simbolos
    public enum Columna {
        NOMBRE(0),  // Nombre del token
        TOKEN(1),   // ID, CTE-STR, CTE-FLOAT o CTE-INT
        TIPO(2),    // INT o FLOAT
        VALOR(3),   // Valor que adquiere
        LEN(4);     // Longitud si es CTE-STR

        private final int numCol;

        Columna(int numCol) {
            this.numCol = numCol;
        }

        public int getNumCol() {
            return numCol;
        }

        @Override
        public String toString() {
            return this.name();
        }
    }

    /**
     * Create the frame.
     */
    private final String ruta = System.getProperties().getProperty("user.dir");

    // tabla de simbolos
    private List<Map<Columna, String>> ts = new ArrayList<Map<Columna, String>>();



    public Tabla() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JList list = new JList();
        list.setFont(new Font("Tahoma", Font.PLAIN, 12));
        contentPane.add(list, BorderLayout.CENTER);
        setBounds(300, 50, 675, 551);
        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setVisible(true);


        File archivo;
        FileReader FileR=null;
        BufferedReader BufferedR;

        try {
            //archivo=new File(this.ruta + "\\ts.txt");           // windows
            archivo=new File(this.ruta + "/ts.txt");    // linux
            FileR=new FileReader(archivo);
            BufferedR= new BufferedReader(FileR);
            String informacion;

            // Levanto la tabla de simbolos a memoria
            if (! levantarTablaEnMemoria("ts.txt")){
                System.exit(-1);
            }

            DefaultListModel lista = new DefaultListModel();

            while((informacion=BufferedR.readLine()) != null) {
                lista.addElement(informacion);
            }

            list.setModel(lista);

            JButton btnCerrar = new JButton("Cerrar");
            btnCerrar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    dispose();
                }
            });
            btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 15));
            contentPane.add(btnCerrar, BorderLayout.SOUTH);
        }
        catch (Exception e) {
            // Do nothing
        }
        finally {
            try {
                if(null != FileR) {
                    FileR.close();
                }
            }
            catch (IOException e2){
                // Do nothing
            }
        }
    }


    private boolean levantarTablaEnMemoria(String nombreArchivo){
        boolean sinErrores = true;

        String linea;

        File archivo;
        FileReader reader   = null;
        BufferedReader buffReader;

        try {
            archivo     = new File(this.ruta.concat("/").concat(nombreArchivo));
            reader      = new FileReader(archivo);
            buffReader  = new BufferedReader(reader);

            while ((linea = buffReader.readLine()) != null) {

                // Parseo cada linea con el delimitador ','(coma)
                String[] splitLine = linea.split("\\,");

                // Diccionario <key>:<value> para cada elemento de la columna
                Map<Columna, String> fila = new HashMap<Columna, String>();
                for (Columna c : Columna.values()) {
                    fila.put(c, splitLine[c.getNumCol()]);
                }
                this.ts.add(fila);
            }
        }
        catch (Exception e) {
            System.out.println("[Tabla.java] - Error parseando tabla de simbolos a memoria");
            sinErrores = false;
        }
        finally {
            try {
                if(reader != null) {
                    reader.close();}
            }
            catch (IOException e2){
                // Do nothing
            }
        }
        return sinErrores;
    }


    public boolean generarASM() {

        ArrayList<String> tsToAssembler = new ArrayList<String>();

        // Almacena el assembler a generar
        tsToAssembler.add(".DATA");

        // Paso cada linea de la ts a assembler
        for (Map<Columna, String> fila : this.ts) {
            //String nombreEscapado = escaparNombre(fila.get(Columna.NOMBRE));

            String nombreEscapado = fila.get(Columna.NOMBRE);

            switch(fila.get(Columna.TOKEN)) {
                case "Numero":
                case "Real":
                    tsToAssembler.add("\t" + nombreEscapado + "\tdd\t" + fila.get(Columna.VALOR));
                    break;
                case "Const_String":
                    tsToAssembler.add("\t" + nombreEscapado + "\tdb\t" + fila.get(Columna.VALOR) + ",'$'");
                    break;
                default:
                    tsToAssembler.add("\t" + nombreEscapado + "\tdd\t?");
                    break;
            }
        }
        GeneradorAssembler.escribirASM(tsToAssembler, null, true);
        return true;
    }


    public static String escaparNombre(String nombre) {
        String nombreEscapado = "";
        for (int i = 0; i < nombre.length(); i++) {
            String a = nombre.substring(i, i+1);
            if (!a.matches("[A-Za-z0-9@_]"))
                a = "%x" + Integer.toHexString((int) a.charAt(0));
            nombreEscapado += a;
        }
        return nombreEscapado;
    }

    public static String escaparValorString(String valorString) {
        if (valorString.contains("\"")) return "'" + valorString + "'";
        return "\"" + valorString + "\"";
    }

    private String leerArchivoLineaPorLinea(String nombreArchivo){
        String linea;
        String output = "";

        File archivo        = null;
        FileReader reader   = null;
        BufferedReader buffReader;

        try {
            archivo     = new File(this.ruta.concat("/").concat(nombreArchivo));
            reader      = new FileReader(archivo);
            buffReader  = new BufferedReader(reader);


            // Leo linea entera, no separo por delimitador ',' => manejar arriba
            while((linea=buffReader.readLine()) != null) {
                output += linea + "\n";
                //System.out.printf("[Tabla.java] - Nueva linea leida: \n (%s)", output);
            }
        }
        catch (Exception e) {
            System.out.printf("[Tabla.java] - Error leyendo tabla de simbolos (%s).\n", archivo);
        }
        finally {
            try {
                if(reader != null) {
                    reader.close();}
            }
            catch (IOException e2){
                // Do nothing
            }
        }
        return output;
    }
}
