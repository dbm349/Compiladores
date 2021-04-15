package main;

import java_cup.runtime.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

%%


/*%cupsym Simbolo*/
%cup
%public
%class Lexico
%line
%column
%char
%init{

%init}

%{
	
	public String s = "";
	public String Error = "";
	final int MAX_STRING = 30;
	final int MAX_INT = Short.MAX_VALUE;
	final float MAX_FLOAT = Float.MAX_VALUE;

	private boolean verify_real(String x) throws Exception {
		float f = Float.parseFloat(x);
		if (f < -MAX_FLOAT || f > MAX_FLOAT) {
			Error="La longitud del lexema "+x+" excede la esperada";
			throw new Exception("La longitud del lexema "+x+" excede la esperada");
		}
		return true;
	}

	private boolean verify_int(String x) throws Exception {
		try {
			int i = Integer.parseInt(x);
			if (i < -MAX_INT || i > MAX_INT) {
				Error="La longitud del lexema "+x+" excede la esperada";
				throw new Exception("La longitud del lexema "+x+" excede la esperada");
			}
		}catch (NumberFormatException e) {
			Error="La longitud del lexema "+x+" excede la esperada";
			throw new Exception("La longitud del lexema "+x+" excede la esperada");
		}
		return true;
	}

	private boolean verify_string(String x) throws Exception {
		if (x.length() > MAX_STRING) {
			Error="La longitud del lexema "+x+" excede la esperada";
			throw new Exception("La longitud del lexema "+x+" excede la esperada");
		}
		return true;
	}
%}

While = while | WHILE
If = if | IF | If
Else = else | ELSE | Else
Print = print | PRINT
DeclareB = declare | DECLARE
DeclareE = enddeclare | ENDDECLARE
ProgramB = BEGIN.PROGRAM | begin.program
ProgramE = END.PROGRAM | end.program
InList = inlist | INLIST
PInt = INT
PFloat = FLOAT
PString = STRING
VarId = {Nombre}
ComentarioAnidado = "</" ~ {Comentario} ~ "/>"
Comentario = "</" ~"/>"

And = "&&" | and | AND
Or = "||" | or | OR
Mayor = ">"
MayorI = ">="
Menor = "<"
MenorI = "<="
Distinto = "<>"
Igual = "==" | "="
Suma = "+"
Resta = "-"
Multiplicacion = "*"
Division = "/"
ParA = "("
ParC = ")"
LlaveA = "{"
LlaveC = "}"
CorcheteA = "["
CorcheteC = "]"
Coma = ","
PuntoC = ";"
Comilla = "\""

Asignacion = ":="
Letra = [a-zA-ZñÑ]
Digito = [0-9]
Numero = {Digito}+ | (- {Digito}+)
Real = ({Numero}+ "." {Digito}*) | ({Numero}* "." {Digito}+)
Blanco = [ \r\n]
EspacioBlanco = [ \t\f\r\n]
EspaciosBlanco = {EspacioBlanco}+
CaracterEspecial = {Letra}|{Digito}|"!"|"#"|"$"|"%"|"&"|"'"|"("|")"|"*"|"+"|","|"-"|"."|"/"|":"|";"|"{"|"="|"}"|"?"|"@"|"["|"]"|"^"|"_"|"`"|"{"|"|"|"}"|"~"|"\""
Nombre = ({Letra})+ ({Digito} | {Letra} | "_")*
Const_String = {Comilla} ({Letra}|{Digito}|{CaracterEspecial}|{EspacioBlanco})* {Comilla}


%%

<YYINITIAL> {

{Comentario}			{/**/}

{ComentarioAnidado}			{/**/}

{While} 				{
						s=s+"Token While encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.While,yytext());
						}
{If} 					{
						s=s+"Token If encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.If,yytext());
						}
{Else} 					{
						
						s=s+"Token Else encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.Else,yytext());
						}
{Print}					{
						
						s=s+"Token Print encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.Print,yytext());
						}
{DeclareB}				{
						
						s=s+"Token DeclareB encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.DeclareB,yytext());
						}
{DeclareE}				{
						
						s=s+"Token DeclareE encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.DeclareE,yytext());
						}
{ProgramB}				{
						
						s=s+"Token ProgramB encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.ProgramB,yytext());
						}
{ProgramE}				{
						
						s=s+"Token ProgramE encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.ProgramE,yytext());
						}
{InList}				{
						
						s=s+"Token InList encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.InList,yytext());
						}
{PInt}					{
						
						s=s+"Token PInt encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.PInt,yytext());
						}
{PFloat}				{
						
						s=s+"Token PFloat encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.PFloat,yytext());
						}
{PString}				{
						
						s=s+"Token PString encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.PString,yytext());
						}

{ParA}					{
						
						s=s+"Token ParA encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.ParA,yytext());
						}
{ParC}					{
						
						s=s+"Token ParC encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.ParC,yytext());
						}
{LlaveA}				{
						
						s=s+"Token LlaveA encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.LlaveA,yytext());
						}
{LlaveC}				{
						
						s=s+"Token LlaveC encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.LlaveC,yytext());
						}
{CorcheteA}				{
						
						s=s+"Token CorcheteA encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.CorcheteA,yytext());
						}
{CorcheteC}				{
						
						s=s+"Token CorcheteC encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.CorcheteC,yytext());
						}

{And}					{
						
						s=s+"Token And encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.And,yytext());
						}
{Or}					{
						
						s=s+"Token Or encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.Or,yytext());
						}
{Mayor}					{
						
						s=s+"Token Mayor encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.Mayor,yytext());
						}
{MayorI}				{
						
						s=s+"Token MayorI encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.MayorI,yytext());
						}
{Menor}					{
						
						s=s+"Token Menor encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.Menor,yytext());
						}
{MenorI}				{
						
						s=s+"Token MenorI encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.MenorI,yytext());
						}
{Distinto}				{
						
						s=s+"Token Distinto encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.Distinto,yytext());
						}
{Igual}					{
						
						s=s+"Token Igual encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.Igual,yytext());
						}
{Suma}					{
						
						s=s+"Token Suma encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.Suma,yytext());
						}
{Resta}					{
						
						s=s+"Token Resta encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.Resta,yytext());
						}
{Multiplicacion}		{
						
						s=s+"Token Multiplicacion encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.Multiplicacion,yytext());
						}
{Division}				{
						
						s=s+"Token Division encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.Division,yytext());
						}
{Coma}					{
						
						s=s+"Token Coma encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.Coma,yytext());
						}
{PuntoC}				{
						
						s=s+"Token PuntoC encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.PuntoC,yytext());
						}
{Asignacion}			{
						
						s=s+"Token Asignacion encontrado, Lexema "+ yytext()+"\n";
						return new Symbol(sym.Asignacion,yytext());
						}

{Numero}				{
							verify_int(yytext());
							s=s+"Token Numero encontrado, Lexema "+ yytext()+"\n";
							//writeSymbolTable("_"+ yytext() + ",Numero,,"+ yytext()+ ",");
							return new Symbol(sym.Numero,yytext());
						}
{Real}					{
							verify_real(yytext());
							s=s+"Token Real encontrado, Lexema "+ yytext()+"\n";
							//writeSymbolTable("_"+ yytext() + ",Real,,"+ yytext()+ ",");
							return new Symbol(sym.Real,yytext());
						}
"\"" [^\"\n\r]* "\""				{
							verify_string(yytext());						
							s=s+"Token Const_String encontrado, Lexema "+ yytext()+"\n";
							//writeSymbolTable("_"+ yytext() + ",Const_String,,"+ yytext()+ ","+ yytext().length());
							return new Symbol(sym.Const_String,yytext());
						}

{EspaciosBlanco}			{ /* ignore */ }

{VarId}					{
						
						s=s+"Token VarId encontrado, Lexema "+ yytext()+"\n";
						//writeSymbolTable(yytext() + ",VarId,,"+",");
						return new Symbol(sym.VarId,yytext());
						}

}

[^]		{ 	Error="Caracter no permitido: <" + yytext() + "> en la linea " + yyline;
			throw new Error("Caracter no permitido: <" + yytext() + "> en la linea " + yyline); }
<<EOF>> {return new Symbol(sym.EOF);}