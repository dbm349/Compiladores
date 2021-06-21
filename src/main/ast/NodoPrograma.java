package main.ast;

import main.GeneradorAssembler;

import java.util.Arrays;
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

    public boolean generarAssembler() {



        //TODO: Agregar valores tabla símbolos
        //Tabla.generarASM(null);



        for (NodoSentencia sentencia : sentencias) {
            //assembler.append(sentencia.generarAssembler());
        }

        return true;
    }

    @Override
    public Nodo hallarPrimerSubArbolConTodasHojas() {
        for (NodoSentencia sentencia : sentencias) {

        }
        return null;
    }

    public boolean generarASM() {
        for (NodoSentencia sentencia: sentencias) {
            sentencia.generarASM();
        }

        return true;
    }
}

