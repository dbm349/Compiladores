package main.ast;

public class NodoSalida extends NodoSentencia {
    private final NodoConstanteString constante_string;

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

    @Override
    public String generarAssembler() {
        String nombreVariableAssembler = "_" + constante_string.getValor();
        return "displayString " + nombreVariableAssembler + "\n"
                + "newLine 1 \n";
    }
}
