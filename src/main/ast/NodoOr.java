package main.ast;

import main.assembler.recorrido.ConstantesASM;

public class NodoOr extends NodoCondicionBooleana {

    private static int count = 0;
    private final int orNumber;

    public NodoOr(NodoCondicion condicionIzquierda, NodoCondicion condicionDerecha) {
        super(condicionIzquierda, condicionDerecha, "OR");
        count++;
        orNumber = count;
        setID("@_OR" + count);
    }

    @Override
    public String generarAssembler() {
        ConstantesASM.data.append(this.getID()).append(" dd ?\n");
        StringBuilder stringBuilder = new StringBuilder();
        if (! condicionIzquierda.soyHoja()) {
            stringBuilder.append(condicionIzquierda.generarAssembler());
        }
        if (! condicionDerecha.soyHoja()) {
            stringBuilder.append(condicionDerecha.generarAssembler());
        }
        String comparar = "MOV EAX, " + condicionIzquierda.getID()  + "\n" +
                "MOV EBX, 1\n" +
                "CMP EAX, EBX\n" +
                "JE INST_OR_TRUE" + orNumber + "\n" +
                "MOV EAX, " + condicionDerecha.getID()  + "\n" +
                "MOV EBX, 1\n" +
                "CMP EAX, EBX\n" +
                "JE INST_OR_TRUE" + orNumber + "\n";
        stringBuilder.append(comparar);
        stringBuilder.append("MOV ").append(this.getID()).append(", 0").append("\n");
        stringBuilder.append("JMP INST_OR_END").append(orNumber).append("\n");
        stringBuilder.append("\nINST_OR_TRUE").append(orNumber).append(":").append("\n");
        stringBuilder.append("MOV ").append(this.getID()).append(", 1").append("\n");
        stringBuilder.append("\nINST_OR_END").append(orNumber).append(":").append("\n");
        return stringBuilder.toString();
    }
}
