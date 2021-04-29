package main.ast;

import java.util.List;

public class NodoIf extends NodoIfAbstracto {
    protected final NodoCondicion condicion;
    protected final List<NodoSentencia> sentencias;

    public NodoIf(NodoCondicion condicion, List<NodoSentencia> sentencias) {
        super("IF");
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
