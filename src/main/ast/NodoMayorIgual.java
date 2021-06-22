package main.ast;

import main.GeneradorAssembler;

import java.util.ArrayList;
import java.util.List;

public class NodoMayorIgual extends NodoComparacionExpresiones{

    private NodoExpresion expresionIzquierda;
    private NodoExpresion expresionDerecha;

    public NodoMayorIgual(NodoExpresion expresionIzquierda, NodoExpresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha, ">=");
        this.expresionDerecha = expresionDerecha;
        this.expresionIzquierda = expresionIzquierda;
    }

    public boolean generarAssembler(String finEtiqueta) {
        List<String> lista = new ArrayList<>();
        if (todosSusHijosSonHojas()){
            expresionDerecha.generarAssembler();
            expresionIzquierda.generarAssembler();

        }
        lista.add("fcomp");
        lista.add("fstsw ax");
        lista.add("sahf");
        lista.add("jae" + finEtiqueta);
        GeneradorAssembler.escribirASM(lista, null, true);
        return true;
    }
}
