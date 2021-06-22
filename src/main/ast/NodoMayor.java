package main.ast;

import main.assembler.recorrido.ConstantesASM;

public class NodoMayor extends NodoComparacionExpresiones {

    private static int count;
    private int graterNumber;

    public NodoMayor(NodoExpresion expresionIzquierda, NodoExpresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha, ">");
        count++;
        graterNumber = count;
        this.setID("@_GREATER" + count);
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
        stringBuilder.append("JA GREATER_TRUE").append(graterNumber).append("\n");
        stringBuilder.append("MOV ").append(this.getID()).append(", 0").append("\n");
        stringBuilder.append("JMP GREATER_END").append(graterNumber).append("\n");
        stringBuilder.append("GREATER_TRUE").append(graterNumber).append(":").append("\n");
        stringBuilder.append("MOV ").append(this.getID()).append(", 1").append("\n");
        stringBuilder.append("GREATER_END").append(graterNumber).append(":").append("\n");
        return stringBuilder.toString();
    }
}
