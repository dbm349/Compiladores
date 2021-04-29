package main.ast;

public class NodoMenor extends NodoComparacionExpresiones {
    public NodoMenor(NodoExpresion expresionIzquierda, NodoExpresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha, "<");
    }
}
