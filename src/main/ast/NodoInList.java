package main.ast;

import main.assembler.recorrido.ConstantesASM;

import java.util.List;

public class NodoInList extends NodoCondicion {

    private static int count = 0;
    private final int inlistNumber;

    private final NodoIdentificador identificador;
    private final List<? extends NodoExpresion> constantes;
    private final NodoCondicion condicion;

    public NodoInList(NodoIdentificador identificador, List<NodoExpresion> constantes) {
        super("INLIST");
        this.identificador = identificador;
        this.constantes = constantes;
        NodoCondicion condicion = null;
        NodoCondicion condicionAux = null;
        for (NodoExpresion constante : constantes) {
            condicion = new NodoIgual(new NodoIdentificador(identificador.getIdentificador()), constante);
            if (condicionAux != null) {
                condicion = new NodoOr(condicion, condicionAux);
            }
            condicionAux = condicion;
        }
        this.condicion = condicion;
        count++;
        inlistNumber = count;
        this.setID("@__INLIST" + inlistNumber);
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        StringBuilder resultado = new StringBuilder(super.graficar(idPadre) +
                condicion.graficar(miId));
        return resultado.toString();
    }

    @Override
    public String generarAssembler() {
        ConstantesASM.data.append(this.getID()).append(" dd ?\n");
        String result = condicion.generarAssembler();
        result += "MOV EAX, " + condicion.getID() + "\n";
        result += "MOV " + this.getID() + ", EAX\n";
        return result;
    }
}
