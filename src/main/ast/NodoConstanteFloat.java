package main.ast;

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
}
