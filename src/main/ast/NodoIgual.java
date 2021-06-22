package main.ast;

import main.assembler.recorrido.ConstantesASM;

public class NodoIgual extends NodoComparacionExpresiones {

    private static int count = 0;
    private final int equalsNumber;

    public NodoIgual(NodoExpresion expresionIzquierda, NodoExpresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha, "=");
        count++;
        equalsNumber = count;
        this.setID("@_EQ" + count);
    }

    @Override
    public String generarAssembler() {
        ConstantesASM.data.append(this.getID()).append(" DW ?\n");
        StringBuilder stringBuilder = new StringBuilder();
        if (! expresionIzquierda.soyHoja()) {
            stringBuilder.append(expresionIzquierda.generarAssembler());
        }
        if (! expresionDerecha.soyHoja()) {
            stringBuilder.append(expresionDerecha.generarAssembler());
        }
        String comparar = "MOV AX, " + expresionDerecha.getID()  + "\n" +
                "MOV BX, " + expresionIzquierda.getID()  + "\n" +
                "CMP AX, BX\n";
        stringBuilder.append(comparar);
        stringBuilder.append("JE EQ_TRUE").append(equalsNumber).append("\n");
        stringBuilder.append("MOV ").append(this.getID()).append(", 0").append("\n");
        stringBuilder.append("JMP EQ_END").append(equalsNumber).append("\n");
        stringBuilder.append("EQ_TRUE").append(equalsNumber).append(":").append("\n");
        stringBuilder.append("MOV ").append(this.getID()).append(", 1").append("\n");
        stringBuilder.append("EQ_END").append(equalsNumber).append(":").append("\n");
        return stringBuilder.toString();
    }
}
