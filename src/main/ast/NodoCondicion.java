package main.ast;

import main.GeneradorAssembler;
import main.ast.Nodo;

import java.util.ArrayList;
import java.util.List;

public class NodoCondicion extends Nodo {

    public NodoCondicion(String nombre) {
        super(nombre);
    }

    public void generarASM(String endTag) {
        List<String> lineas = new ArrayList<String>();
        //TODO: REVISAR COMO MANEJAR EL TIPO DE COMPARACION EN LA EXPRESION
        if (izquierda.soyHoja()) {
            derecha.generarASM();
            izquierda.generarASM();
        } else {
            izquierda.generarASM();
            derecha.generarASM();
            lineas.add("fxch");
        }
        lineas.add("fcomp");
        lineas.add("fstsw ax");
        lineas.add("sahf");
        lineas.add(Operador.getOperador(getDescripcionNodo()) + " " + endTag);
        GeneradorAssembler.escribirASM(lineas, null, true);
    }
}
