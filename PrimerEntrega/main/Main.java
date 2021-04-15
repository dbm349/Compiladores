package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java_cup.runtime.Symbol;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		/*IDECompilador ventana = new IDECompilador();
		ventana.setVisible(true);*/
		
		try {
			FileReader f = new FileReader("prueba.txt");
			Lexico Lexer = new Lexico(f);
			Lexer.next_token();
		} catch (FileNotFoundException ex) {
			System.out.println("No se encontró el archivo");
		}

	}

}
