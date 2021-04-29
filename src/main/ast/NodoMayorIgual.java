package main.ast;

public class NodoMayorIgual extends NodoComparacionExpresiones{
    public NodoMayorIgual(NodoExpresion expresionIzquierda, NodoExpresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha, ">=");
    }
}
