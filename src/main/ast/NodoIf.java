package main.ast;

import java.util.List;

public class NodoIf extends NodoIfAbstracto {
    protected final NodoCondicion condicion;
    protected final NodoBloque bloqueThen;
    //protected final List<NodoSentencia> sentencias;

    public NodoIf(NodoCondicion condicion, List<NodoSentencia> instrucciones) {
        super("IF");
        this.condicion = condicion;
        this.bloqueThen = new NodoBloque(instrucciones,"THEN");
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        StringBuilder resultado = new StringBuilder(super.graficar(idPadre) +
                condicion.graficar(miId) + bloqueThen.graficar(miId));
        return resultado.toString();
    }

    public boolean generarAssembler() {

        return true;
    }

    @Override
    public boolean todosSusHijosSonHojas() {
        return false;
    }
}
