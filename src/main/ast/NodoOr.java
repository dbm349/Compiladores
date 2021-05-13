package main.ast;

public class NodoOr extends NodoCondicionBooleana {
    public NodoOr(NodoCondicion condicionIzquierda, NodoCondicion condicionDerecha) {
        super(condicionIzquierda, condicionDerecha, "OR");
    }
}
