package main.ast;

import java.util.List;

public class NodoIfElse extends NodoIfAbstracto {

    static int count = 0;
    protected int ifElseNumber;

    private final NodoCondicion condicion;
    private final NodoBloque nodoBloqueIf;
    private final NodoBloque nodoBloqueElse;

    public NodoIfElse(NodoCondicion condicion, List<NodoSentencia> sentencias, List<NodoSentencia> bloqueElse) {
        super("IF");
        this.condicion = condicion;
        this.nodoBloqueIf = new NodoBloque(sentencias, "THEN");
        this.nodoBloqueElse = new NodoBloque(bloqueElse, "ELSE");
        count++;
        ifElseNumber = count;
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

    @Override
    public String generarAssembler() {
        return condicion.generarAssembler()
                + "MOV EAX, 1\n"
                + "MOV EBX, " + condicion.getID()  + "\n"
                + "CMP EAX, EBX \n"
                + "CMP " + condicion.getID() + ", 1\n"
                + "JNE INST_IF_ELSE" + ifElseNumber + "\n"
                + nodoBloqueIf.generarAssembler()
                + "JMP INST_IF_END" + ifElseNumber + "\n"
                + "INST_IF_ELSE" + ifElseNumber + ":\n"
                + nodoBloqueElse.generarAssembler()
                + "INST_IF_END" + ifElseNumber + ":\n";
    }
}
