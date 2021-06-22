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
        String nombreVariableAssembler = "_" + constante_string.getValor().replace("\"", "").replace(".", "punto").replace(" ", "");
        return "\n;ini muestra string\n"
                + "displayString " + nombreVariableAssembler + "\n"
                + "newLine 1 \n"
                + ";fin muestra string\n\n";
/*        return "\n;ini muestra string\n" +
        "mov dx, OFFSET " + nombreVariableAssembler + "\n" +
        "mov ah, 9\n" +
        "int 21h\n" +
        "mov ah, 2\n" +
        "mov dl, 13\n" +
        "int 21h\n" +
        "mov dl, 10\n" +
        "int 21h\n" +
        ";fin muestra string\n\n";*/
    }
}
