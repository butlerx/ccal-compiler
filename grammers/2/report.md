# CCal Parser

## Tokenisation

To begin I defined all the reserved symbols and words such as var, const, main and void.

```
TOKEN [IGNORE_CASE]: { /* Keywords and punctuation */
  < VAR : "var" >
  | < CONST : "const" >
  | < RETURN : "return" >
  | < INTEGER : "integer" >
  | < BOOLEAN : "boolean" >
  | < VOID : "void" >
  | < MAIN : "main" >
  | < IF : "if" >
  | < ELSE : "else" >
  | < TRUE : "true" >
  | < FALSE : "false" >
  | < WHILE : "while" >
  | < SKP : "skip" >
  | < COLON : ":" >
  | < BEGIN : "begin" >
  | < END : "end" >
  | < IS : "is">
  | < NOT : "∼" >
  | < OR : "||" >
  | < AND : "&&" >
  | < EQUAL : "==" >
  | < NOT_EQUAL : "!=" >
  | < LESS_THAN : "<" >
  | < GREATER_THAN : ">" >
  | < LESS_EQUALS : "<=" >
  | < GREATER_EQUALS : ">=" >
  | < SEMIC : ";" >
  | < ASSIGN : "=" >
  | < PRINT : "print" >
  | < LBR : "(" >
  | < RBR : ")" >
  | < COMMA : "," >
  | < PLUS_SIGN : "+" >
  | < MINUS_SIGN : "-" >
  | < MULT_SIGN : "*" >
  | < DIV_SIGN : "/" >
}
```

These are all the symbols ill be using in my langauge for structure.
These are reserved for languages in the ccal and wih we used for parseing.
I then defined Digits, letters and any other symbols that i want to allow as part of varibale names.
These are difined as any combination of each other of any length.

``` JJTree
TOKEN : { /* Numbers and identifiers */
  < NUM : "0" | ("-")? ["1" - "9"] (<DIGIT>)* >
  | < #DIGIT : ["0" - "9"] >
  | < ID : (<LETTER>) (<LETTER> | <DIGIT> | "_")* >
  | < #LETTER : ["a" - "z", "A" - "Z"] >
}
```

I then add tokens for the syboml i want to ignore. These sybols include tabbing, spaces, newlines and comments. There are two different types of comments that need to be ignored. Single line comment, those that start with `//`and mutli line comments that can be nested. Single line comments are ignore by looking for `//` and ignoreing all combinations leters and numbers of any length until there is an end of line.

```
SKIP : { /* Ignoring spaces/tabs/newlines */
  <"//" (["a"-"z"]|["A"-"Z"]|["0"-"9"]|" ")*("\n"|"\r"|"\r\n")>
  | " "
  | "\t"
  | "\n"
  | "\r"
  | "\f"
}
```

Multiline Comments are ignored by initialising a variable of counting comment at the begining. Then once it sees a `/*` it increases this variable and goes in to comment mode where every time it sees a `/*` it increases this varibale and if it sees a `*/` it decreases this variable and once the variable reaches 0 it returns to normal mode. While in comment mode it ignores all symbols thta arnt `/*` or `*/`.

```
TOKEN_MGR_DECLS : {
  static int commentNesting = 0;
}

SKIP : { /* COMMENTS */
  "/*" { commentNesting++; } : IN_COMMENT
}

<IN_COMMENT> SKIP : {
  "/*" {
    commentNesting++;
  } |
  "*/" {
    commentNesting--;
    if (commentNesting == 0) {
      SwitchTo(DEFAULT);
    }
  } | <~[]>
}
```

## Grammer

The Grammer of the program defines how a program should be read and parsed. Ive defined the program so as to expect declarations first, whixh is a declaration of variables. then a list of functions then the main method. I then define the main methosd to be starting with main and wrapped in braces. both a function and main begin with decleration anbd statments but a function differs as it is recusivly called as you do not know how many functions you may have. A function also differs in that it defines what type it will return and takes params and must finish with a return though it can return an empty opject.

```
void program () : {} {
  declList()
  funcList()
  mainProg()
  <EOF>
}

void mainProg () : {} {
  <MAIN>
  <LEFT_BRACE>
    declList()
    statementBlock()
  <RIGHT_BRACE>
}

void function () : {} {
  type() <ID> <LBR> param() <RBR>
  <LEFT_BRACE>
    declList()
    statementBlock()
    <RETURN> <LBR> (expression() | {} ) <RBR> <SEMIC>
  <RIGHT_BRACE>
}

void funcList () : {} {
  ((function() funcList()) | {})
}
```

A Statement is called recusivly as you do not know how many staments may be defined. statemenmts must support and id being assigned aswell. A statement could also have a condition check of a while or if block wrapping a recusive call to of more statements. An if statemnent may for may not be follewed by and else statement. The else statemnt is not requred and  is only used when it is called.
```
void statement () : {} {
  <ID> (<ASSIGN> expression() | <LBR> argList() <RBR>) <SEMIC>
  | <LEFT_BRACE> statementBlock() <RIGHT_BRACE>
  | <IF> condition() <LEFT_BRACE> statementBlock() <RIGHT_BRACE>
    [<ELSE> <LEFT_BRACE> statementBlock() <RIGHT_BRACE>]
  | <WHILE> <LBR> condition() <RBR>
      <LEFT_BRACE> statementBlock() <RIGHT_BRACE>
  | <SKP> <SEMIC>
}

void statementBlock () : {} {
  ((statement() statementBlock()) | {})
}
```

expression condictionaly calls it self recirsivly if there are arithmethric operatpors.
```
void expression () : {} {
  (<ID> [<LBR> argList() <RBR>] | <TRUE> | <FALSE> | <NUM>)
      [ arithOp()  expression() ]
  | [<MINUS_SIGN>](<ID>|<NUM>) [<LBR> argList() <RBR>]
  | <LBR> expression() <RBR> [ arithOp()  expression() ]
}
```
Conditions recurovly calls it self to check join other comdition and give negative ones, finally giveing an expression.
```
void condition () : {} {
  <LBR> condition() <RBR> [( <AND> | <OR>) condition()]
  |<NOT> condition()
  | expression() [compOp() expression()] [( <AND> | <OR>) condition()]
}
```
