package main.ast;

import main.services.TablaService;

import java.io.IOException;
import java.util.List;

public class NodoPrograma extends Nodo {
    private final List<NodoSentencia> sentencias;

    public NodoPrograma(List<NodoSentencia> sentencias) {
        super("PGM");
        this.sentencias = sentencias;
    }

    public String graficar() {
        // Acá se dispara la invocación a los métodos graficar() de los nodos.
        // Como un NodoPrograma no tiene padre, se inicia pasando null.
        return this.graficar(null);
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = "nodo_programa";

        StringBuilder resultado = new StringBuilder();
        resultado.append("graph G {");

        resultado.append(miId + " [label=\"Programa\"]\n");
        for (NodoSentencia sentencia : this.sentencias) {
            resultado.append(sentencia.graficar(miId));
        }

        resultado.append("}");

        return resultado.toString();
    }

    public String generarAssembler() {
        StringBuilder assembler = new StringBuilder();
        assembler.append("include macros2.asm\n" +
                "include number.asm\n" +
                "\n" +
                ".MODEL  SMALL\n" +
                ".386\n" +
                ".STACK 200h\n" +
                ".DATA\n\n");
        //TODO: Agregar valores tabla símbolos
        TablaService tablaService = new TablaService("ts.txt");
        try {
            String tabla = tablaService.generarASM();
            assembler.append(tabla);
        } catch (IOException e) {
            System.out.println("No generó las variables");
        }
        assembler.append("\n" +
                ".CODE\n" +
                "\n" +
                "MOV AX,@DATA\n" +
                "MOV DS,AX\n" +
                "FINIT; Inicializa el coprocesador\n");
        for (NodoSentencia sentencia : sentencias) {
            assembler.append(sentencia.generarAssembler());
        }
        assembler.append("FINAL:\n" +
                "   mov ah, 1 ; pausa, espera que oprima una tecla\n" + //Quizás hay que quitarlo
                "   int 21h ; AH=1 es el servicio de lectura\n" + //Quizás hay que quitarlo
                "   MOV AX, 4C00h ; Sale del Dos\n\n" +
                "END ; final del archivo.\n");
        return assembler.toString();
    }

    @Override
    public Nodo hallarPrimerSubArbolConTodasHojas() {
        for (NodoSentencia sentencia : sentencias) {
            //
        }
        return null;
    }
}

