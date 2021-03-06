package main.ast;

import main.assembler.recorrido.ConstantesASM;

public class NodoDistinto extends NodoComparacionExpresiones {

    private static int count = 0;
    private final int distNumber;

    public NodoDistinto(NodoExpresion expresionIzquierda, NodoExpresion expresionDerecha) {
        super(expresionIzquierda, expresionDerecha, "<>");
        count++;
        distNumber = count;
        this.setID("@_DIST" + count);
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
        String comparar = "MOV EAX, " + expresionDerecha.getID()  + "\n" +
                "MOV EBX, " + expresionIzquierda.getID()  + "\n" +
                "CMP EAX, EBX\n";
        stringBuilder.append(comparar);
        stringBuilder.append("JNE DIST_TRUE").append(distNumber).append("\n");
        stringBuilder.append("MOV ").append(this.getID()).append(", 0").append("\n");
        stringBuilder.append("JMP DIST_END").append(distNumber).append("\n");
        stringBuilder.append("\nDIST_TRUE").append(distNumber).append(":").append("\n");
        stringBuilder.append("MOV ").append(this.getID()).append(", 1").append("\n");
        stringBuilder.append("\nDIST_END").append(distNumber).append(":").append("\n");
        return stringBuilder.toString();
    }
}
