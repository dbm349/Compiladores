package main.ast;

public class NodoMenorIgual extends NodoComparacionExpresiones {
    public NodoMenorIgual(NodoExpresion expresionIzquierda, NodoExpresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha, "<=");
    }
}
