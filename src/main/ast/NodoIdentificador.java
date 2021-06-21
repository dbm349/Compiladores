package main.ast;

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
    public String generarAssembler() {
        return "FLD " +"_@"+ identificador;
    }
}
