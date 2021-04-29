package main.ast;

public class NodoSalida extends NodoSentencia {
    private final NodoExpresion constante_string;

    public NodoSalida(NodoConstanteString constante_string) {
        super("DISPLAY");
        this.constante_string = constante_string;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                constante_string.graficar(miId);
    }
}