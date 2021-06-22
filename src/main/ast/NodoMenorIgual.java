package main.ast;

import main.assembler.recorrido.ConstantesASM;

public class NodoMenorIgual extends NodoComparacionExpresiones {

    private static int count;
    private final int minorEqualsNumber;

    public NodoMenorIgual(NodoExpresion expresionIzquierda, NodoExpresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha, "<=");
        count++;
        minorEqualsNumber = count;
        this.setID("@_MINOR_EQ" + count);
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
        String comparar = "MOV AX, " + expresionIzquierda.getID()  + "\n" +
                "MOV BX, " + expresionDerecha.getID()  + "\n" +
                "CMP AX, BX\n";
        stringBuilder.append(comparar);
        stringBuilder.append("JAE MINOR_EQ_TRUE").append(minorEqualsNumber).append("\n");
        stringBuilder.append("MOV ").append(this.getID()).append(", 0").append("\n");
        stringBuilder.append("JMP MINOR_EQ_END").append(minorEqualsNumber).append("\n");
        stringBuilder.append("\nMINOR_EQ_TRUE").append(minorEqualsNumber).append(":").append("\n");
        stringBuilder.append("MOV ").append(this.getID()).append(", 1").append("\n");
        stringBuilder.append("\nMINOR_EQ_END").append(minorEqualsNumber).append(":").append("\n");
        return stringBuilder.toString();
    }
}
