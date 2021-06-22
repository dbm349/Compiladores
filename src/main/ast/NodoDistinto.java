package main.ast;

import main.GeneradorAssembler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NodoDistinto extends NodoComparacionExpresiones {

    private NodoExpresion expresionIzquierda;
    private NodoExpresion expresionDerecha;

    public NodoDistinto(NodoExpresion expresionIzquierda, NodoExpresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha, "<>");
        this.expresionDerecha = expresionDerecha;
        this.expresionIzquierda = expresionIzquierda;
    }


    public boolean generarAssembler(String finEtiqueta) {
        List<String> lista = new ArrayList<>();
        if (todosSusHijosSonHojas()){
            expresionIzquierda.generarAssembler();
            expresionDerecha.generarAssembler();
        }
        lista.add("fcomp");
        lista.add("fstsw ax");
        lista.add("sahf");
        lista.add("jne" + finEtiqueta);
        GeneradorAssembler.escribirASM(lista, null, true);
        return true;
    }

}
