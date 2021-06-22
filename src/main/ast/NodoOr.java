package main.ast;

public class NodoOr extends NodoCondicionBooleana {
    public NodoOr(NodoCondicion condicionIzquierda, NodoCondicion condicionDerecha) {
        super(condicionIzquierda, condicionDerecha, "OR");
    }

    @Override
    public String generarAssembler() {
        StringBuilder stringBuilder = new StringBuilder();
        if (! condicionIzquierda.soyHoja()) {
            stringBuilder.append(condicionIzquierda.podar());
        }
        if (! condicionDerecha.soyHoja()) {
            stringBuilder.append(condicionDerecha.podar());
        }
        //TODO: ASM Condición
        String comparar = "CMP " + condicionIzquierda.getID() + ", " + condicionDerecha.getID();
        stringBuilder.append(comparar);
        return stringBuilder.toString();
    }
}
