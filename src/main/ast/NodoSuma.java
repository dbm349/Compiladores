package main.ast;

import main.GeneradorAssembler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NodoSuma extends NodoExpresionBinaria {

    private NodoExpresion izquierda;
    private NodoExpresion derecha;

    public NodoSuma(NodoExpresion izquierda, NodoExpresion derecha) {
        super("+", izquierda, derecha);
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    //Si nodo izquierda es hoja y nodo derecho tambien
    @Override
    public boolean generarAssembler() {

        if (todosSusHijosSonHojas()){
            izquierda.generarAssembler();
            derecha.generarAssembler();
        }
        GeneradorAssembler.escribirASM(Arrays.asList("fadd "), null, true);
        return true;
    }
}