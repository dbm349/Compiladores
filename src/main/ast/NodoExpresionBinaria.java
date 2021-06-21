package main.ast;

import main.GeneradorAssembler;

import java.util.ArrayList;
import java.util.List;

public class NodoExpresionBinaria extends NodoExpresion {
    private final NodoExpresion izquierda;
    private final NodoExpresion derecha;

    public NodoExpresionBinaria(String nombre, NodoExpresion izquierda, NodoExpresion derecha) {
        super(nombre);
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                izquierda.graficar(miId) +
                derecha.graficar(miId);
    }

    public void generarASM(String endTag) {
        List<String> lineas = new ArrayList<String>();
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
        //TODO: REVISAR COMO MANEJAR EL TIPO DE COMPARACION EN LA EXPRESION
        lineas.add(Operador.getOperador(getDescripcionNodo()) + " " + endTag);
        GeneradorAssembler.escribirASM(lineas, null, true);
    }
}
