package main.ast;

import java.util.List;

public class NodoWhile extends NodoSentencia {
    static int count = 0;
    protected int whileCount;

    private final NodoCondicion condicion;
    //private final List<NodoSentencia> sentencias;
    protected final NodoBloque bloque;

    public NodoWhile(NodoCondicion condicion, List<NodoSentencia> sentencias) {
        super("WHILE");
        this.condicion = condicion;
        this.bloque = new NodoBloque(sentencias,"Bloque");
        count++;
        this.whileCount = count;
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
        return  "\n\nINST_WHILE_BEGIN" + this.whileCount + ":\n"
                + "\t" + this.condicion.generarAssembler()
                + "\tMOV AX, 1\n"
                + "\tMOV BX, " + this.condicion.getID()  + "\n"
                + "\tCMP AX, BX \n"
                + "\tCMP " + this.condicion.getID() + ", 1\n"
                + "JNE INST_WHILE_END" + this.whileCount + "\n"
                + "\t" + this.bloque.generarAssembler()
                + "JMP INST_WHILE_BEGIN" + this.whileCount + "\n"
                + "INST_WHILE_END" + this.whileCount + ":\n\n";
    }
}
