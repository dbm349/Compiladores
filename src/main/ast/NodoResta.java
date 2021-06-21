package main.ast;

import main.GeneradorAssembler;

import java.util.Arrays;

public class NodoResta extends NodoExpresionBinaria {

    private NodoExpresion izquierda;
    private NodoExpresion derecha;

    public NodoResta(NodoExpresion izquierda, NodoExpresion derecha) {

        super("-", izquierda, derecha);
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    @Override
    public boolean generarAssembler() {

        if (todosSusHijosSonHojas()){
            izquierda.generarAssembler();
            derecha.generarAssembler();
        }
        GeneradorAssembler.escribirASM(Arrays.asList("fsub "), null, true);
        return true;
    }

}
