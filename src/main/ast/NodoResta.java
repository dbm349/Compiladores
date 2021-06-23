package main.ast;

import main.assembler.recorrido.ConstantesASM;

public class NodoResta extends NodoExpresionBinaria {

    private static int count = 0;
    private final int subNumber;

    public NodoResta(NodoExpresion izquierda, NodoExpresion derecha) {
        super("-", izquierda, derecha);
        count++;
        subNumber = count;
        this.setID("@__RESTA" + subNumber);
    }

    public String generarAssembler() {

        ConstantesASM.data.append(this.getID()).append(" DW, ?\n");

        String result = "";
        if (!izquierda.soyHoja()) {
            result += izquierda.generarAssembler();
        }
        if (!derecha.soyHoja()) {
            result += derecha.generarAssembler();
        }

        result += "\n;inicio resta-\n";
        result += "\tMOV AX, " + derecha.getID() + "\n";
        result += "\tMOV BX, " + izquierda.getID() + "\n";
        result += "\tSUB BX, AX" + "\n";
        result += "\tMOV " + this.getID() + ", BX\n";
        result += ";fin resta-\n\n";

        /*result += "\n;inicio resta-\n";
        result += "\tFLD " + derecha.getID() + "\n";
        result += "\tFLD " + izquierda.getID() + "\n";
        result += "\tFSUB" + "\n";
        result += "\tFSTP " + this.getID() + "\n";
        result += ";fin resta-\n\n";*/
        return result;
    }
}
