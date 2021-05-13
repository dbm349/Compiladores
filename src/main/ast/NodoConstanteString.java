package main.ast;

import java.nio.charset.StandardCharsets;

public class NodoConstanteString extends NodoExpresion {
    private final String valor;

    public NodoConstanteString(String valor) {
        super("CTE-STR");
        this.valor = valor;
    }

    @Override
    public String getDescripcionNodo() {
        return "CTE-STR: " + normalize(valor);
    }

    private String normalize(String denormalizedString) {
        StringBuilder normalizedString = new StringBuilder();
        int i = 0;
        int stringSize = denormalizedString.length();
        while (i < stringSize) {
            if (denormalizedString.charAt(i) != '"') {
                normalizedString.append(denormalizedString.charAt(i));
            }
            i++;
        }
        return normalizedString.toString();
    }
}
