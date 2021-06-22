package main;

import main.ast.NodoPrograma;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReader f;
		Lexico lexer = null;
		try {
			f = new FileReader("pruebas/prueba-b.txt");
			lexer = new Lexico(f);
			parser sintactico = new parser(lexer);
			NodoPrograma programa = (NodoPrograma) sintactico.parse().value;
			System.out.println("------------------------------------------------------");
			String assembler = programa.generarAssembler();
			FileWriter fileWriter = new FileWriter("R:/tasm/prueba.asm");
			fileWriter.write(assembler);
			fileWriter.close();
			System.out.println("------------------------------------------------------");
			/*try {
				FileWriter archivo = new FileWriter("arbol.dot");
				PrintWriter pw = new PrintWriter(archivo);
				pw.println(programa.graficar());
				archivo.close();
			} catch (Exception e) {
				System.out.println(e);
			}

			String cmd = "dot -Tpng arbol.dot -o arbol.png";
			Runtime.getRuntime().exec(cmd);*/

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		} catch (Exception e) {
		}
	}

}
