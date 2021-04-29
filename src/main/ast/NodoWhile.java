package main.ast;

import java.util.List;

public class NodoWhile extends NodoSentencia {
    private final NodoCondicion condicion;
    private final List<NodoSentencia> sentencias;

    public NodoWhile(NodoCondicion condicion, List<NodoSentencia> sentencias) {
        super("WHILE");
        this.condicion = condicion;
        this.sentencias = sentencias;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        StringBuilder resultado = new StringBuilder(super.graficar(idPadre) +
                condicion.graficar(miId));

        for (NodoSentencia sentencia : this.sentencias) {
            resultado.append(sentencia.graficar(miId));
        }
        return resultado.toString();
    }
}
