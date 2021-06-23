package main.ast;

public class NodoAsignacion extends NodoSentencia {
    private final NodoIdentificador identificador;
    private final NodoExpresion expresion;

    public NodoAsignacion(NodoIdentificador identificador, NodoExpresion expresion) {
        super(":=");
        this.identificador = identificador;
        this.expresion = expresion;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                identificador.graficar(miId) +
                expresion.graficar(miId);
    }

    @Override
    public String generarAssembler() {
        StringBuilder stringBuilder = new StringBuilder();
        if (! expresion.soyHoja()) {
            stringBuilder.append(expresion.generarAssembler());
        }
        var descripcionNodo = expresion.getDescripcionNodo();
        if (descripcionNodo.contains("CTE-STR")) {
            stringBuilder.append("MOV EAX, OFFSET ").append(expresion.getID()).append("\n");
        } else {
            stringBuilder.append("MOV EAX, ").append(expresion.getID()).append("\n");
        }
        stringBuilder.append("MOV ").append(identificador.getID()).append(", EAX").append("\n");
        return stringBuilder.toString();
    }
}
