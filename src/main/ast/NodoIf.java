package main.ast;

import java.util.List;

public class NodoIf extends NodoIfAbstracto {
    static int count = 0;
    protected int ifCount;
    protected final NodoCondicion condicion;
    protected final NodoBloque bloqueThen;
    //protected final List<NodoSentencia> sentencias;

    public NodoIf(NodoCondicion condicion, List<NodoSentencia> instrucciones) {
        super("IF");
        this.condicion = condicion;
        this.bloqueThen = new NodoBloque(instrucciones,"THEN");
        count++;
        ifCount = count;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        StringBuilder resultado = new StringBuilder(super.graficar(idPadre) +
                condicion.graficar(miId) + bloqueThen.graficar(miId));
        return resultado.toString();
    }

    @Override
    public String generarAssembler() {
        return condicion.generarAssembler()
                + "MOV AX, 1\n"
                + "MOV BX, " + condicion.getID()  + "\n"
                + "CMP AX, BX \n"
                + "CMP " + condicion.getID() + ", 1\n"
                + "JNE INST_IF" + ifCount + "\n"
                + bloqueThen.generarAssembler()
                + "INST_IF" + ifCount + ":\n";
    }

    @Override
    public boolean todosSusHijosSonHojas() {
        return false;
    }
}
