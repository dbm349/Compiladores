package main.ast;

import main.GeneradorAssembler;

import java.util.Arrays;

public class NodoConstanteFloat extends NodoExpresion {
    private final float valor;

    public NodoConstanteFloat(float valor) {
        super("CTE-FLT");
        this.valor = valor;
    }

    @Override
    public String getDescripcionNodo() {
        return "CTE-FLT: " + Float.toString(valor);
    }

    @Override
    public boolean soyHoja() {
        return true;
    }

    @Override
    public boolean generarAssembler() {
        GeneradorAssembler.escribirASM(Arrays.asList("fld " + valor), null, true);
        return true;
    }

}
