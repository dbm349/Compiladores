package main.ast;

import main.GeneradorAssembler;

import java.util.ArrayList;
import java.util.Arrays;

public class NodoSalida extends NodoSentencia {
    private final NodoConstanteString constante_string;

    public NodoSalida(NodoConstanteString constante_string) {
        super("DISPLAY");
        this.constante_string = constante_string;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                constante_string.graficar(miId);
    }

    public boolean generarAssembler() {
        String nombreVariableAssembler = "_@" + constante_string.getValor();

        GeneradorAssembler.escribirASM(Arrays.asList(
            "displayString " + nombreVariableAssembler), null, true);
        return true;
    }
}
