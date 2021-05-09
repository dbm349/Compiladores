package main.ast;

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
}
