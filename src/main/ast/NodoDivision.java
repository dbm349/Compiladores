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
        ConstantesASM.data.append(this.getID()).append(" DD, ?");
        String result = "";
        if (!izquierda.soyHoja()) {
            result += izquierda.generarAssembler();
        }
        if (!derecha.soyHoja()) {
            result += derecha.generarAssembler();
        }
/*        result += "MOV AX, " + derecha.getID() + "\n";
        result += "MOV BX, " + izquierda.getID() + "\n";
        result += "DIV BX" + "\n";
        result += "MOV " + this.getID() + ", [0000 + AL]" + "\n";*/

        result += "\n;inicio division;'\n";
        result += "FLD " + derecha.getID() + "\n";
        result += "FLD " + izquierda.getID() + "\n";
        result += "FDIV" + "\n";
        result += "FSTP " + this.getID() + "\n";
        result += "\n\n;fin division;\n";
        return result;
    }
}
