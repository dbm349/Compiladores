package main.ast;

import main.GeneradorAssembler;

import java.util.Arrays;
import java.util.List;


public class NodoWhile extends NodoSentencia {
    private final NodoCondicion condicion;
    //private final List<NodoSentencia> sentencias;
    protected final NodoBloque bloque;

    public NodoWhile(NodoCondicion condicion, List<NodoSentencia> sentencias) {
        super("WHILE");
        this.condicion = condicion;
        this.bloque = new NodoBloque(sentencias,"Bloque");
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        StringBuilder resultado = new StringBuilder(super.graficar(idPadre) +
                condicion.graficar(miId) + bloque.graficar(miId));
        return resultado.toString();
    }

    public boolean generarASM(){
        String startTag = getDescripcionNodo() + "_START_TAG";
        String endTag = getDescripcionNodo() + "_END_TAG";

        GeneradorAssembler.escribirASM(Arrays.asList(startTag + ":"), null, true);

        condicion.generarASM(endTag);

        for (NodoSentencia s : bloque) {
            s.generarASM();
        }

        GeneradorAssembler.escribirASM(Arrays.asList(
                "jmp " + startTag,
                endTag + ":"
        ), null, true);

        return true;
    }
}
