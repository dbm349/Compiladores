package main.ast;

public class NodoConstanteString extends NodoExpresion {
    private final String valor;

    public NodoConstanteString(String valor) {
        super("CTE-STR");
        this.valor = valor;
        this.setID("_" + valor.replace("\"", "").replace(" ", "").replace(".","punto").replace(",","coma")
                .replace("<","@menor").replace(">","@mayor"));
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

    public String getValor() {
        return valor;
    }

    @Override
    public boolean soyHoja() {
        return true;
    }
}
