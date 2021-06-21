package main.ast;

import main.GeneradorAssembler;

import java.util.Arrays;

public class NodoAsignacion extends NodoSentencia {
    private final NodoIdentificador identificador;
    private final NodoExpresion expresion;

    public NodoAsignacion(NodoIdentificador identificador, NodoExpresion expresion) {
        super(":=");
        this.identificador = identificador;
        this.expresion = expresion;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                identificador.graficar(miId) +
                expresion.graficar(miId);
    }

    public boolean generarAssembler() {
        //TODO: actualizar tabla de simbolos con su valor
        //TODO: generar el assembler correspondiente

        GeneradorAssembler.escribirASM(Arrays.asList(
                "fstp" + identificador.getIdentificador()), null, true);
        return true;
    }
}
