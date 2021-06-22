package main.ast;

import main.assembler.recorrido.ConstantesASM;

public class NodoSuma extends NodoExpresionBinaria {

    public NodoSuma(NodoExpresion izquierda, NodoExpresion derecha) {
        super("+", izquierda, derecha);
    }

    public String generarAssembler() {

        ConstantesASM.data.append(this.getID()).append(" DW, ?");

        String result = "";
        if (!izquierda.soyHoja()) {
            result += izquierda.generarAssembler();
        }
        if (!derecha.soyHoja()) {
            result += derecha.generarAssembler();
        }

        result += "\n;inicio suma;'\n";
        result += "FLD " + derecha.getID() + "\n";
        result += "FLD " + izquierda.getID() + "\n";
        result += "FADD" + "\n";
        result += "FSTP" + this.getID() + "\n";
        result += "\n\n;fin suma;\n";
        return result;
    }
}