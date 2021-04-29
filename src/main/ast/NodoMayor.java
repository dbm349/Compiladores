package main.ast;

public class NodoMayor extends NodoComparacionExpresiones {
    public NodoMayor(NodoExpresion expresionIzquierda, NodoExpresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha, ">");
    }
}
