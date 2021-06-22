package main.ast;

public class NodoConstante extends NodoExpresion {
    private final int valor;

    public NodoConstante(int valor) {
        super("CTE");
        this.valor = valor;
        this.setID("_" + valor);
    }

    @Override
    public String getDescripcionNodo() {
        return "CTE: " + Integer.toString(valor);
    }

    @Override
    public boolean soyHoja() {
        return true;
    }
}
