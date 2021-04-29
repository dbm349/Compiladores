package main.ast;

import java.util.List;

public class NodoIfElse extends NodoIfAbstracto {

    private final NodoCondicion condicion;
    private final NodoBloque nodoBloqueIf;
    private final NodoBloque nodoBloqueElse;

    public NodoIfElse(NodoCondicion condicion, List<NodoSentencia> sentencias, List<NodoSentencia> bloqueElse) {
        super("IF");
        this.condicion = condicion;
        this.nodoBloqueIf = new NodoBloque(sentencias, "BLOQUE_IF");
        this.nodoBloqueElse = new NodoBloque(bloqueElse, "ELSE");
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        StringBuilder resultado = new StringBuilder(super.graficar(idPadre) +
                condicion.graficar(miId));

        resultado.append(nodoBloqueIf.graficar(miId));
        resultado.append(nodoBloqueElse.graficar(miId));

        return resultado.toString();
    }
}
