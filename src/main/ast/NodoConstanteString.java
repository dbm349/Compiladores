package main.ast;

public class NodoConstanteString extends NodoExpresion {
    private final String valor;

    public NodoConstanteString(String valor) {
        super("CTE-STR");
        this.valor = valor;
    }

    @Override
    public String getDescripcionNodo() {
        return "CTE-STR: " + valor;
    }
}
