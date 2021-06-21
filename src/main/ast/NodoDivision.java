package main.ast;

import main.GeneradorAssembler;

import java.util.Arrays;

public class NodoDivision extends NodoExpresionBinaria {

    private NodoExpresion izquierda;
    private NodoExpresion derecha;

    public NodoDivision(NodoExpresion izquierda, NodoExpresion derecha) {

        super("/", izquierda, derecha);
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    @Override
    public boolean generarAssembler() {

        if (todosSusHijosSonHojas()){
            derecha.generarAssembler();
            izquierda.generarAssembler();
        }
        GeneradorAssembler.escribirASM(Arrays.asList("fdiv"), null, true);
        return true;
    }
}
