package main.ast;

import main.assembler.recorrido.ConstantesASM;

public class NodoSuma extends NodoExpresionBinaria {

    private static int count = 0;
    private final int sumNumber;

    public NodoSuma(NodoExpresion izquierda, NodoExpresion derecha) {
        super("+", izquierda, derecha);
        count++;
        sumNumber = count;
        this.setID("@__SUMA" + sumNumber);
    }

    public String generarAssembler() {

        ConstantesASM.data.append(this.getID()).append(" DD, ?\n");

        String result = "";
        if (!izquierda.soyHoja()) {
            result += izquierda.generarAssembler();
        }
        if (!derecha.soyHoja()) {
            result += derecha.generarAssembler();
        }

        result += "\n;inicio suma;\n";
        result += "\tFLD " + derecha.getID() + "\n";
        result += "\tFLD " + izquierda.getID() + "\n";
        result += "\tFADD" + "\n";
        result += "\tFSTP " + this.getID() + "\n";
        result += ";fin suma;\n";
        return result;
    }
}