package main.ast;

import java.util.List;

public class NodoBloque extends NodoSentencia {

    private final List<NodoSentencia> sentencias;

    public NodoBloque(List<NodoSentencia> sentencias, String tipo) {
        super(tipo);
        this.sentencias = sentencias;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        StringBuilder resultado = new StringBuilder(super.graficar(idPadre));

        for (NodoSentencia sentencia : this.sentencias) {
            resultado.append(sentencia.graficar(miId));
        }
        return resultado.toString();
    }

    public String generarAssembler() {
        StringBuilder assembler = new StringBuilder();
        for (NodoSentencia sentencia : sentencias) {
            assembler.append(sentencia.generarAssembler());
        }
        return assembler.toString();
    }
}
