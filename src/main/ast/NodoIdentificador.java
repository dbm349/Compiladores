package main.ast;

public class NodoIdentificador extends NodoExpresion {
    private final String identificador;

    public NodoIdentificador(String identificador) {
        super("ID");
        this.identificador = identificador;
        this.setID("@__" + identificador);
    }

    @Override
    public String getDescripcionNodo() {
        return "ID: " + identificador;
    }

    public String getIdentificador() {
        return identificador;
    }

    @Override
    public boolean soyHoja() {
        return true;
    }
}
