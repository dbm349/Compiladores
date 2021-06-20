package main.ast;

import main.assembler.recorrido.RecorredorArbol;

public class Nodo implements RecorredorArbol {
    //@_var1
    //@_varN
    public static int contadorVariables = 0;

    private String descripcion;

    public Nodo(String descripcion) {
        this.descripcion = descripcion;
    }

    protected String getIdNodo() {
        int hashCode;
        if (this.hashCode() >= 0) {
            hashCode = this.hashCode();
        } else {
            hashCode = - this.hashCode();
        }
        return "nodo_" + hashCode;
    }

    public String getDescripcionNodo() {
        return descripcion;
    }

    protected String graficar(String idPadre) {
        return String.format("%1$s [label=\"%2$s\"]\n%3$s -- %1$s\n", getIdNodo(), getDescripcionNodo(), idPadre);
    }

    @Override
    public boolean todosSusHijosSonHojas() {
        return false;
    }

    @Override
    public boolean soyHoja() {
        return false;
    }

    @Override
    public Nodo hallarPrimerSubArbolConTodasHojas() {
        return null;
    }

    @Override
    public int getContadorVariables() {
        return ++contadorVariables;
    }

    @Override
    public String generarAssembler() {
        return "";
    }
}