package main.assembler.recorrido;

import main.ast.Nodo;

/**
 * 1. Devolver true en soy hoja si no tengo hijos
 * 2. todosSusHijosSonHojas: devolver true cuando todos los nodos hijos son hojas
 * 3. llamar al metodo hallarPrimerSubArbolConTodasHojas, que tiene que devolver el primer nodo
 *      que devuelva true con todosSusHijosSonHojas() recorriendo en preorden
 * 4. generar assembler del subarbol con sólo hojas hayado.
 * 5. podar arbol, cambiar el subarbol por un NodoConstante por ejemplo.
 *
 */
public interface RecorredorArbol {

    /*Devuelve true si todos sus hijos son hojas
    * Devuelve false si tiene un nodo intermedio como hijo
    * */
    boolean todosSusHijosSonHojas();
    boolean soyHoja();
    /*
    * hallarPrimerSubArbolConTodasHojas
    * */
    Nodo hallarPrimerSubArbolConTodasHojas();

    //@_var1
    //@_var2
    int getContadorVariables();

    /*
    * Lo del video de las profesoras
     */
    String generarAssembler();


}
