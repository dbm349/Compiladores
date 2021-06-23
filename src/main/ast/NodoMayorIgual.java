package main.ast;

import main.assembler.recorrido.ConstantesASM;

public class NodoMayorIgual extends NodoComparacionExpresiones{

    private static int count;
    private final int graterEqualsNumber;

    public NodoMayorIgual(NodoExpresion expresionIzquierda, NodoExpresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha, ">=");
        count++;
        graterEqualsNumber = count;
        this.setID("@_GREATER_EQ" + count);
    }

    @Override
    public String generarAssembler() {
        ConstantesASM.data.append(this.getID()).append(" dd ?\n");
        StringBuilder stringBuilder = new StringBuilder();
        if (! expresionIzquierda.soyHoja()) {
            stringBuilder.append(expresionIzquierda.generarAssembler());
        }
        if (! expresionDerecha.soyHoja()) {
            stringBuilder.append(expresionDerecha.generarAssembler());
        }
        String comparar = "MOV EAX, " + expresionIzquierda.getID()  + "\n" +
                "MOV EBX, " + expresionDerecha.getID()  + "\n" +
                "CMP EAX, EBX\n";
        stringBuilder.append(comparar);
        stringBuilder.append("JAE GREATER_EQ_TRUE").append(graterEqualsNumber).append("\n");
        stringBuilder.append("MOV ").append(this.getID()).append(", 0").append("\n");
        stringBuilder.append("JMP GREATER_EQ_END").append(graterEqualsNumber).append("\n");
        stringBuilder.append("\nGREATER_EQ_TRUE").append(graterEqualsNumber).append(":").append("\n");
        stringBuilder.append("MOV ").append(this.getID()).append(", 1").append("\n");
        stringBuilder.append("\nGREATER_EQ_END").append(graterEqualsNumber).append(":").append("\n");
        return stringBuilder.toString();
    }
}
