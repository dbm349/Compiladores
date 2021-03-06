package main.ast;

import main.assembler.recorrido.ConstantesASM;

public class NodoAnd extends NodoCondicionBooleana {

    private static int count = 0;
    private final int andNumber;

    public NodoAnd(NodoCondicion condicionIzquierda, NodoCondicion condicionDerecha) {
        super(condicionIzquierda, condicionDerecha, "AND");
        count++;
        andNumber = count;
        setID("@_AND" + count);
    }

    @Override
    public String generarAssembler() {
        ConstantesASM.data.append(this.getID()).append(" dd ?\n");
        StringBuilder stringBuilder = new StringBuilder();
        if (!condicionIzquierda.soyHoja()) {
            stringBuilder.append(condicionIzquierda.generarAssembler());
        }
        if (!condicionDerecha.soyHoja()) {
            stringBuilder.append(condicionDerecha.generarAssembler());
        }
        //TODO: CORREGIR
        String comparar = "MOV EAX, " + condicionIzquierda.getID() + "\n" +
                "MOV EBX, 1\n" +
                "CMP EAX, EBX\n" +
                "JNE INST_AND_FALSE" + andNumber + "\n" +
                "MOV EAX, " + condicionDerecha.getID() + "\n" +
                "MOV EBX, 1\n" +
                "CMP EAX, EBX\n" +
                "JNE INST_AND_FALSE" + andNumber + "\n";
        stringBuilder.append(comparar);
        stringBuilder.append("MOV ").append(this.getID()).append(", 1").append("\n");
        stringBuilder.append("JMP INST_AND_END").append(andNumber).append("\n");
        stringBuilder.append("\nINST_AND_FALSE").append(andNumber).append(":").append("\n");
        stringBuilder.append("MOV ").append(this.getID()).append(", 0").append("\n");
        stringBuilder.append("\nINST_AND_END").append(andNumber).append(":").append("\n");
        return stringBuilder.toString();
    }
}
