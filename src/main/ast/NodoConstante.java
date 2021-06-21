package main.ast;

import main.GeneradorAssembler;

import java.util.Arrays;

public class NodoConstante extends NodoExpresion {
    private final int valor;

    public NodoConstante(int valor) {
        super("CTE");
        this.valor = valor;
    }

    @Override
    public String getDescripcionNodo() {
        return "CTE: " + Integer.toString(valor);
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
