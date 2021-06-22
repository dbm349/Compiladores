package main.ast;

import java.util.List;

public class NodoInList extends NodoCondicion {

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
        //TODO: verificar si funciona
        return condicion.generarAssembler();
    }
}
