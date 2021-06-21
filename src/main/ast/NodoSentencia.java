package main.ast;

import main.GeneradorAssembler;

import java.util.ArrayList;
import java.util.Arrays;

public class NodoSentencia extends Nodo {

    public NodoSentencia(String nombre) {
        super(nombre);
    }

    public boolean generarASM(){
        GeneradorAssembler.escribirASM(Arrays.asList(""), null, true);
        return true;
    }
}
