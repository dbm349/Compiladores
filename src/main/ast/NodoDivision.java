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
        //TODO: Corregir funcionalidad
        ConstantesASM.data.append(this.getID()).append(" DW, ?");
        String result = "";
        if (!izquierda.soyHoja()) {
            result += izquierda.generarAssembler();
        }
        if (!derecha.soyHoja()) {
            result += derecha.generarAssembler();
        }
        result += "MOV AX, " + derecha.getID() + "\n";
        result += "MOV BX, " + izquierda.getID() + "\n";
        result += "DIV BX" + "\n";
        result += "MOV " + this.getID() + ", [0000 + AL]" + "\n";
        return  result;
    }
}
