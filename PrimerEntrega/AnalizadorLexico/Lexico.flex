import java_cup.runtime.Symbol;
import jflex.core.sym;

%%


/*%cupsym Simbolo*/
%cup
%public
%class Lexico
%line
%column
%char
%init{
	try{
		String carpeta = System.getProperty("user.dir");
		String ruta = carpeta + "/ts.txt";
		f = new File(ruta);
		bw = new BufferedWriter(new FileWriter(f,true));
		listaSimbolos = new ArrayList<>();
	}catch (IOException e){
		e.printStackTrace();
	}
%init}

%{
	BufferedWriter bw;
	File f;
	ArrayList<String> listaSimbolos;
	
	public void writeSymbolTable(String s) throws IOException{
		if(!listaSimbolos.contains(s.split(",")[0])){
			bw.write(s);
			bw.newLine();
			bw.flush();
			listaSimbolos.add(s.split(",")[0]);
		}
	}
	
	public String s = "";
	final int MAX_STRING = 30;
	final int MAX_INT = Short.MAX_VALUE;
	final float MAX_FLOAT = Float.MAX_VALUE;

	private boolean verify_real(String x) throws Exception {
		float f = Float.parseFloat(x);
		if (f < -MAX_FLOAT || f > MAX_FLOAT) {
			throw new Exception("La longitud del lexema "+x+" excede la esperada");
		}
		return true;
	}

	private boolean verify_int(String x) throws Exception {
		try {
			int i = Integer.parseInt(x);
			if (i < -MAX_INT || i > MAX_INT) {
				throw new Exception("La longitud del lexema "+x+" excede la esperada");
			}
		}catch (NumberFormatException e) {
			throw new Exception("La longitud del lexema "+x+" excede la esperada");
		}
		return true;
	}

	private boolean verify_string(String x) throws Exception {
		if (x.length() > MAX_STRING) {
			throw new Exception("La longitud del lexema "+x+" excede la esperada");
		}
		return true;
	}
%}

While = while | WHILE
If = if | IF | If
Print = print | PRINT
DeclareB = declare | DECLARE
DeclareE = enddeclare | ENDDECLARE
ProgramB = BEGIN.PROGRAM | begin.program
ProgramE = END.PROGRAM | end.program
InList = inlist | INLIST
VarId = {Nombre}
Comentario = "</" ~"/>"

And = "&&"
Or = "||"
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

Asignacion = ":="
Letra = [a-zA-Z]
Digito = [0-9]
Numero = {Digito}+ | (- {Digito}+)
Real = ({Numero}+ "." {Digito}*) | ({Numero}* "." {Digito}+)
Blanco = [ \r\n]
EspacioBlanco = [ \t\f\r\n]
CaracterEspecial = {Letra}|{Digito}|"!"|"#"|"$"|"%"|"&"|"'"|"("|")"|"*"|"+"|","|"-"|"."|"/"|":"|";"|"{"|"="|"}"|"?"|"@"|"["|"]"|"^"|"_"|"`"|"{"|"|"|"}"|"~"|"\""
String = {Blanco} | {CaracterEspecial}*
Nombre = ({Letra})+ ({Digito} | {Letra} | "_")*


%%

<YYINITIAL> {

{Comentario}			{/**/}

{While} 				{
						System.out.println("Token While encontrado, Lexema "+ yytext());
						s=s+"Token While encontrado, Lexema "+ yytext()+"\n";
						}
{If} 					{
						System.out.println("Token If encontrado, Lexema "+ yytext());
						s=s+"Token If encontrado, Lexema "+ yytext()+"\n";
						}
{Print}					{
						System.out.println("Token Print encontrado, Lexema "+ yytext());
						s=s+"Token Print encontrado, Lexema "+ yytext()+"\n";
						}
{DeclareB}				{
						System.out.println("Token DeclareB encontrado, Lexema "+ yytext());
						s=s+"Token DeclareB encontrado, Lexema "+ yytext()+"\n";
						}
{DeclareE}				{
						System.out.println("Token DeclareE encontrado, Lexema "+ yytext());
						s=s+"Token DeclareE encontrado, Lexema "+ yytext()+"\n";
						}
{ProgramB}				{
						System.out.println("Token ProgramB encontrado, Lexema "+ yytext());
						s=s+"Token ProgramB encontrado, Lexema "+ yytext()+"\n";
						}
{ProgramE}				{
						System.out.println("Token ProgramE encontrado, Lexema "+ yytext());
						s=s+"Token ProgramE encontrado, Lexema "+ yytext()+"\n";
						}
{InList}				{
						System.out.println("Token InList encontrado, Lexema "+ yytext());
						s=s+"Token InList encontrado, Lexema "+ yytext()+"\n";
						}

{ParA}					{
						System.out.println("Token ParA encontrado, Lexema "+ yytext());
						s=s+"Token ParA encontrado, Lexema "+ yytext()+"\n";
						}
{ParC}					{
						System.out.println("Token ParC encontrado, Lexema "+ yytext());
						s=s+"Token ParC encontrado, Lexema "+ yytext()+"\n";
						}
{LlaveA}				{
						System.out.println("Token LlaveA encontrado, Lexema "+ yytext());
						s=s+"Token LlaveA encontrado, Lexema "+ yytext()+"\n";
						}
{LlaveC}				{
						System.out.println("Token LlaveC encontrado, Lexema "+ yytext());
						s=s+"Token LlaveC encontrado, Lexema "+ yytext()+"\n";
						}
{CorcheteA}				{
						System.out.println("Token CorcheteA encontrado, Lexema "+ yytext());
						s=s+"Token CorcheteA encontrado, Lexema "+ yytext()+"\n";
						}
{CorcheteC}				{
						System.out.println("Token CorcheteC encontrado, Lexema "+ yytext());
						s=s+"Token CorcheteC encontrado, Lexema "+ yytext()+"\n";
						}

{And}					{
						System.out.println("Token And encontrado, Lexema "+ yytext());
						s=s+"Token And encontrado, Lexema "+ yytext()+"\n";
						}
{Or}					{
						System.out.println("Token Or encontrado, Lexema "+ yytext());
						s=s+"Token Or encontrado, Lexema "+ yytext()+"\n";
						}
{Mayor}					{
						System.out.println("Token Mayor encontrado, Lexema "+ yytext());
						s=s+"Token Mayor encontrado, Lexema "+ yytext()+"\n";
						}
{MayorI}				{
						System.out.println("Token MayorI encontrado, Lexema "+ yytext());
						s=s+"Token MayorI encontrado, Lexema "+ yytext()+"\n";
						}
{Menor}					{
						System.out.println("Token Menor encontrado, Lexema "+ yytext());
						s=s+"Token Menor encontrado, Lexema "+ yytext()+"\n";
						}
{MenorI}				{
						System.out.println("Token MenorI encontrado, Lexema "+ yytext());
						s=s+"Token MenorI encontrado, Lexema "+ yytext()+"\n";
						}
{Distinto}				{
						System.out.println("Token Distinto encontrado, Lexema "+ yytext());
						s=s+"Token Distinto encontrado, Lexema "+ yytext()+"\n";
						}
{Igual}					{
						System.out.println("Token Igual encontrado, Lexema "+ yytext());
						s=s+"Token Igual encontrado, Lexema "+ yytext()+"\n";
						}
{Suma}					{
						System.out.println("Token Suma encontrado, Lexema "+ yytext());
						s=s+"Token Suma encontrado, Lexema "+ yytext()+"\n";
						}
{Resta}					{
						System.out.println("Token Resta encontrado, Lexema "+ yytext());
						s=s+"Token Resta encontrado, Lexema "+ yytext()+"\n";
						}
{Multiplicacion}		{
						System.out.println("Token Multiplicacion encontrado, Lexema "+ yytext());
						s=s+"Token Multiplicacion encontrado, Lexema "+ yytext()+"\n";
						}
{Division}				{
						System.out.println("Token Division encontrado, Lexema "+ yytext());
						s=s+"Token Division encontrado, Lexema "+ yytext()+"\n";
						}
{Coma}					{
						System.out.println("Token Coma encontrado, Lexema "+ yytext());
						s=s+"Token Coma encontrado, Lexema "+ yytext()+"\n";
						}
{PuntoC}				{
						System.out.println("Token PuntoC encontrado, Lexema "+ yytext());
						s=s+"Token PuntoC encontrado, Lexema "+ yytext()+"\n";
						}
{Asignacion}			{
						System.out.println("Token Asignacion encontrado, Lexema "+ yytext());
						s=s+"Token Asignacion encontrado, Lexema "+ yytext()+"\n";
						}

{Numero}				{
							verify_int(yytext());
							System.out.println("Token Numero encontrado, Lexema "+ yytext());
							s=s+"Token Numero encontrado, Lexema "+ yytext()+"\n";
							writeSymbolTable("_"+ yytext() + ",Numero,,"+ yytext()+ ",");
						}
{Real}					{
							verify_real(yytext());
							System.out.println("Token Real encontrado, Lexema "+ yytext());
							s=s+"Token Real encontrado, Lexema "+ yytext()+"\n";
							writeSymbolTable("_"+ yytext() + ",Real,,"+ yytext()+ ",");
						}
"\"" [^\"\n\r]* "\""				{
							verify_string(yytext());
							System.out.println("Token String encontrado, Lexema "+ yytext());
							s=s+"Token String encontrado, Lexema "+ yytext()+"\n";
							writeSymbolTable("_"+ yytext() + ",String,,"+ yytext()+ ","+ yytext().length());
						}

{EspacioBlanco}			{ /* ignore */ }

{VarId}					{
						System.out.println("Token VarId encontrado, Lexema "+ yytext());
						s=s+"Token VarId encontrado, Lexema "+ yytext()+"\n";
						writeSymbolTable(yytext() + ",VarId,,"+",");
						}

}

[^]		{ throw new Error("Caracter no permitido: <" + yytext() + "> en la linea " + yyline); }