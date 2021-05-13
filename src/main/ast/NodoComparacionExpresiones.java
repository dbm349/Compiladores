package main.ast;

public class NodoComparacionExpresiones extends NodoCondicion {

    private final NodoExpresion expresionIzquierda, expresionDerecha;

    public NodoComparacionExpresiones(NodoExpresion expresionIzquierda, NodoExpresion expresionDerecha, String tipo) {
        super(tipo);
        this.expresionIzquierda = expresionIzquierda;
        this.expresionDerecha = expresionDerecha;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                expresionIzquierda.graficar(miId) +
                expresionDerecha.graficar(miId);
    }
}
