package main.ast;

public class NodoDistinto extends NodoComparacionExpresiones {
    public NodoDistinto(NodoExpresion expresionIzquierda, NodoExpresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha, "<>");
    }
}
