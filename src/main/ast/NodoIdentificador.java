package main.ast;

import main.GeneradorAssembler;

import java.util.Arrays;

public class NodoIdentificador extends NodoExpresion {
    private final String identificador;

    public NodoIdentificador(String identificador) {
        super("ID");
        this.identificador = identificador;
    }

    @Override
    public String getDescripcionNodo() {
        return "ID: " + identificador;
    }

    public String getIdentificador() {
        return this.identificador;
    }

    @Override
    public boolean soyHoja() {
        return true;
    }

    @Override
    public boolean generarAssembler() {
        GeneradorAssembler.escribirASM(Arrays.asList("fld " + identificador), null, true);
        return true;
    }
}
