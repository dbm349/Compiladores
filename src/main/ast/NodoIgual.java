package main.ast;

public class NodoIgual extends NodoComparacionExpresiones {
    public NodoIgual(NodoExpresion expresionIzquierda, NodoExpresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha, "=");
    }
}
