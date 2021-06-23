package main.ast;

import main.assembler.recorrido.ConstantesASM;

public class NodoDivision extends NodoExpresionBinaria {

    private static int count = 0;
    private int divNumber;

    public NodoDivision(NodoExpresion izquierda, NodoExpresion derecha) {
        super("/", izquierda, derecha);
        count++;
        divNumber = count;
        this.setID("@__DIV" + divNumber);
    }

    @Override
    public String generarAssembler() {
        ConstantesASM.data.append(this.getID()).append(" DW, ?\n");
        String result = "";
        if (!izquierda.soyHoja()) {
            result += izquierda.generarAssembler();
        }
        if (!derecha.soyHoja()) {
            result += derecha.generarAssembler();
        }

        result += "\n;inicio multiplicacion*\n";
        result += "\tXOR EAX, EAX\n";
        result += "\tXOR EBX, EBX\n";
        result += "\tMOV AX, " + derecha.getID() + "\n";
        result += "\tMOV BX, " + izquierda.getID() + "\n";
        result += "\tDIV EBX"+ "\n";
        result += "\tMOV " + this.getID() + ", DX\n";
        result += ";fin multiplicacion*\n\n";

/*        result += "MOV AX, " + derecha.getID() + "\n";
        result += "MOV BX, " + izquierda.getID() + "\n";
        result += "DIV BX" + "\n";
        result += "MOV " + this.getID() + ", [0000 + AL]" + "\n";*/

        /*result += "\n;inicio division/\n";
        result += "\tFLD " + derecha.getID() + "\n";
        result += "\tFLD " + izquierda.getID() + "\n";
        result += "\tFDIV" + "\n";
        result += "\tFSTP " + this.getID() + "\n";
        result += ";fin division/\n\n";*/
        return result;
    }
}
