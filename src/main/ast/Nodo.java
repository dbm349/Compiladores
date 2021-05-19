package main.ast;

public class Nodo {
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
}