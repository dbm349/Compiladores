# Gramática

## pgm

    <declaracion> <programa>

## listadesentencias

    <listadesentencias> <lineadesentencia> |
    <lineadesentencia>

## lineadesentencia

    <asignacion> |
    <salida> |
    <while> |
    <if>

## tipo

    PInt | PFloat | PString

## cond

    <condicion> | <condicionlogica> | <inlist>

## while

    While ParA <cond> ParC LlaveA <listadesentencias> LlaveC

## if

    If ParA <cond> ParC LlaveA <listadesentencias> LlaveC |
    If ParA <cond> ParC LlaveA <listadesentencias> LlaveC Else LlaveA <listadesentencias> LlaveC

## asignacion

    VarId <Asignacion> expresion |
    VarId <Asignacion> Const_String

## expresion

    <expresion> Suma <termino> |
    <expresion> Resta <termino> |
    <termino>

## termino

    <termino> Multiplicacion <factor> |
    <termino> Division <factor> |
    <factor>

## factor

    VarId | Numero | Real |
    ParA <expresion> ParC

## salida

    Print Const_String

## condicionlogica

    ParA <condicion> ParC |
    <condicion> And <condicion> |
    <condicion> Or <condicion> |
    ParA <condicion> ParC And ParA <condicion> ParC |
    ParA <condicion> ParC  Or ParA <condicion> ParC

## condicion

    <expresion> op_comp <expresion>

## op_comp

    Mayor | MayorI | Menor | MenorI | Distinto | Igual

## declaracion

    DeclareB <listadedeclaraciones> DeclareE

## listadedeclaraciones

    "[" <lineadedeclaracion> "]" |
    <listadedeclaraciones> "[" <lineadedeclaracion> "]"

## lineadedeclaracion

    VarId "]" Asignacion "[" tipo |
    VarId "," <lineadedeclaracion> "," tipo

## programa

    ProgramB <listadesentencias> ProgramE

## inlist

    <InList> ParA VarId Coma CorcheteA <listadecte> CorcheteC ParC

## listadecte

    <listareal> | <listanumero>

## listareal

    Real |
    <listareal> PuntoC Real

## listanumero

    Numero |
    <listanumero> PuntoC Numero