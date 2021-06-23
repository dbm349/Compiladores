package main.ast;

import main.assembler.recorrido.ConstantesASM;

public class NodoMultiplicacion extends NodoExpresionBinaria {
    private static int count = 0;
    private final int mulNumber;

    public NodoMultiplicacion(NodoExpresion izquierda, NodoExpresion derecha) {
        super("*", izquierda, derecha);
        count++;
        mulNumber = count;
        this.setID("@__MULT" + mulNumber);
    }

    public String generarAssembler() {

        ConstantesASM.data.append(this.getID()).append(" dd, ?\n");

        String result = "";
        if (!izquierda.soyHoja()) {
            result += izquierda.generarAssembler();
        }
        if (!derecha.soyHoja()) {
            result += derecha.generarAssembler();
        }

        /*result += "\n;inicio multiplicacion*\n";
        result += "\tXOR EAX, EAX\n";
        result += "\tXOR EBX, EBX\n";
        result += "\tMOV AX, " + derecha.getID() + "\n";
        result += "\tMOV BX, " + izquierda.getID() + "\n";
        result += "\tIMUL EBX"+ "\n";
        result += "\tMOV " + this.getID() + ", AX\n";
        result += ";fin multiplicacion*\n\n";*/

        result += "\n;inicio multiplicacion*\n";
        result += "\tFLD " + derecha.getID() + "\n";
        result += "\tFLD " + izquierda.getID() + "\n";
        result += "\tFMUL "+ "\n";
        result += "\tFSTP " + this.getID() + "\n";
        result += ";fin multiplicacion*\n\n";
        return result;
    }
}
