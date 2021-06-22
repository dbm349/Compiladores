package main.ast;

import main.assembler.recorrido.ConstantesASM;

public class NodoMenor extends NodoComparacionExpresiones {

    private static int count;
    private final int minorNumber;
    public NodoMenor(NodoExpresion expresionIzquierda, NodoExpresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha, "<");
        count++;
        minorNumber = count;
        this.setID("@_MINOR" + count);
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
        stringBuilder.append("JAE MINOR_TRUE").append(minorNumber).append("\n");
        stringBuilder.append("MOV ").append(this.getID()).append(", 0").append("\n");
        stringBuilder.append("JMP MINOR_END").append(minorNumber).append("\n");
        stringBuilder.append("\nMINOR_TRUE").append(minorNumber).append(":").append("\n");
        stringBuilder.append("MOV ").append(this.getID()).append(", 1").append("\n");
        stringBuilder.append("\nMINOR_END").append(minorNumber).append(":").append("\n");
        return stringBuilder.toString();
    }
}
