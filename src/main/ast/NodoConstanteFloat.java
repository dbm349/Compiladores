package main.ast;

public class NodoConstanteFloat extends NodoExpresion {
    private final float valor;

    public NodoConstanteFloat(float valor) {
        super("CTE-FLT");
        this.valor = valor;
        this.setID("_" + Float.toString(valor).replace(".","punto").replace(",","coma"));
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
