package main.ast;

import java.util.List;

public class NodoWhile extends NodoSentencia {
    static int count = 0;
    protected int ifCount;

    private final NodoCondicion condicion;
    //private final List<NodoSentencia> sentencias;
    protected final NodoBloque bloque;

    public NodoWhile(NodoCondicion condicion, List<NodoSentencia> sentencias) {
        super("WHILE");
        this.condicion = condicion;
        this.bloque = new NodoBloque(sentencias,"Bloque");
        count++;
        ifCount = count;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        StringBuilder resultado = new StringBuilder(super.graficar(idPadre) +
                condicion.graficar(miId) + bloque.graficar(miId));
        return resultado.toString();
    }

    @Override
    public String generarAssembler() {
        return  "INST_WHILE_BEGIN" + ifCount + ":\n"
                + condicion.generarAssembler()
                + "MOV AX, 1\n"
                + "MOV BX, " + condicion.getID()  + "\n"
                + "CMP AX, BX \n"
                + "CMP " + condicion.getID() + ", 1\n"
                + "JNE INST_WHILE_END" + ifCount + "\n"
                + bloque.generarAssembler()
                + "JMP INST_WHILE_BEGIN" + ifCount + "\n"
                + "INST_WHILE_END" + ifCount + ":\n";
    }
}
