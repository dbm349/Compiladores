package main;

import main.ast.NodoPrograma;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		FileReader f;
		Lexico lexer = null;
		try {
			f = new FileReader("pruebas/prueba.txt");
			lexer = new Lexico(f);
			parser sintactico = new parser(lexer);
			NodoPrograma programa = (NodoPrograma) sintactico.parse().value;
			System.out.println("------------------------------------------------------");

			GeneradorAssembler.escribirASM(Arrays.asList(
					"include macros2.asm",
					"include number.asm",
					".MODEL SMALL",
					".386",
					".STACK 200h"), null, false);

			Tabla.generarASM();

			GeneradorAssembler.escribirASM(Arrays.asList(
					".CODE",
					"MOV AX,@DATA",
					"MOV DS,AX",
					"FINIT; Inicializa el coprocesador"
			), null, true);

			programa.generarAssembler();

			GeneradorAssembler.escribirASM(Arrays.asList(
					"FINAL:",
					"mov ah, 1 ; pausa, espera que oprima una tecla", 	//Quizás hay que quitarlo
					"int 21h ; AH=1 es el servicio de lectura",		  	//Quizás hay que quitarlo
					"MOV AX, 4C00h ; Sale del Dos",
					"END ; final del archivo."), null, true);

			System.out.println("------------------------------------------------------");

		} catch (FileNotFoundException e) {
			System.out.println("Archivo de prueba no encontrado.");
		} catch (Exception e) {
			System.out.println("Error abriendo archivo de prueba.");
		}
	}

}
