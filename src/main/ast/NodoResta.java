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

        ConstantesASM.data.append(this.getID()).append(" DD, ?\n");

        String result = "";
        if (!izquierda.soyHoja()) {
            result += izquierda.generarAssembler();
        }
        if (!derecha.soyHoja()) {
            result += derecha.generarAssembler();
        }

        result += "\n;inicio resta;'\n";
        result += "FLD " + derecha.getID() + "\n";
        result += "FLD " + izquierda.getID() + "\n";
        result += "FSUB" + "\n";
        result += "FSTP " + this.getID() + "\n";
        result += "\n\n;fin resta;\n";
        return result;
    }
}
