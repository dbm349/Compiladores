package main;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReader f;
		try {
			f = new FileReader("prueba.txt");
			Lexico Lexer = new Lexico(f);
			parser sintactico = new parser(Lexer);
			sintactico.parse();
			//Lexer.next_token();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay un archivo para analizar");
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		
	}

}
