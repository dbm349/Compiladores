package main.ast;

public class NodoAnd extends NodoCondicionBooleana {
    public NodoAnd(NodoCondicion condicionIzquierda, NodoCondicion condicionDerecha) {
        super(condicionIzquierda, condicionDerecha, "AND");
    }
}
