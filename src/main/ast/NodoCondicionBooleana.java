package main.ast;

public class NodoCondicionBooleana extends NodoCondicion {

    protected final NodoCondicion condicionIzquierda, condicionDerecha;

    public NodoCondicionBooleana(NodoCondicion condicionIzquierda, NodoCondicion condicionDerecha, String tipo) {
        super(tipo);
        this.condicionIzquierda = condicionIzquierda;
        this.condicionDerecha = condicionDerecha;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                condicionIzquierda.graficar(miId) +
                condicionDerecha.graficar(miId);
    }
}
