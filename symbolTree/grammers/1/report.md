# Semantic Analysis and Intermediate Representation for the CCAL Language

### Declaration on Plagiarism

Assignment Submission Form

This form must be filled in and completed by the student(s) submitting an assignment

Name(s): __Cian Butler__  
Programme: __Case 4__  
Module Code: __CA4003__  
Assignment Title: __Semantic Analysis and Intermediate Representation for the CCAL Language__  
Submission Date: __11-12-2016__  
Module Coordinator:  __Dr. David Sinclair__  

I/We declare that this material, which I/We now submit for assessment, is entirely my/our
own work and has not been taken from the work of others, save and to the extent that such
work has been cited and acknowledged within the text of my/our work. I/We understand
that plagiarism, collusion, and copying are grave and serious offences in the university and
accept the penalties that would be imposed should I engage in plagiarism, collusion or
copying. I/We have read and understood the Assignment Regulations. I/We have identified
and included the source of all facts, ideas, opinions, and viewpoints of others in the
assignment references. Direct quotations from books, journal articles, internet sources,
module text, or any other source whatsoever are acknowledged and the source cited are
identified in the assignment references. This assignment, or any part of it, has not been
previously submitted by me/us or any other person for assessment on this or any other
course of study.

I/We have read and understood the referencing guidelines found at
http://www.dcu.ie/info/regulations/plagiarism.shtml , https://www4.dcu.ie/students/az/plagiarism
and/or recommended in the assignment guidelines.


Name(s): __Cian Butler__ Date: __11/12/2016__

## Abstract Syntax Tree
To Make the Abstract Syntax Tree I had to modify my original grammar to make sure all types could create a node.
I then assigned all values i would need and set jjtree to use void by default.
AST from multiply.ccal
```
Abstract Syntax Tree:
 Program
  Function
   Type(integer)
   Id(multiply)
   Param
    Id(x)
    Type(integer)
    Id(y)
    Type(integer)
   VarDecl
    Id(result)
    Type(integer)
   VarDecl
    Id(minus_sign)
    Type(boolean)
   Condition
    Condition
     Id(x)
     CompOp(<)
     Num(0)
     Condition
      Id(y)
      CompOp(>=)
      Num(0)
   Id(minus_sign)
   Bool(true)
   Id(x)
   Id(x)
   Condition
    Id(y)
    CompOp(<)
    Num(0)
    Condition
     Id(x)
     CompOp(>=)
     Num(0)
   Id(minus_sign)
   Bool(true)
   Id(y)
   Id(y)
   Condition
    Condition
     Id(x)
     CompOp(<)
     Num(0)
    Condition
     Id(y)
     CompOp(<)
     Num(0)
   Id(minus_sign)
   Bool(false)
   Id(x)
   Id(x)
   Id(y)
   Id(y)
   Id(minus_sign)
   Bool(false)
   Id(result)
   Num(0)
   Condition
    Id(y)
    CompOp(>)
    Num(0)
   Id(result)
   Id(result)
   ArithOp(+)
   Id(x)
   Id(y)
   Id(y)
   ArithOp(-)
   Num(1)
   Condition
    Id(minus_sign)
    CompOp(==)
    Bool(true)
   Id(result)
   Id(result)
   Id(result)
  MainProg
   VarDecl
    Id(arg_1)
    Type(integer)
   VarDecl
    Id(arg_2)
    Type(integer)
   VarDecl
    Id(result)
    Type(integer)
   ConstDecl
    Id(five)
    Type(integer)
    Num(5)
   Id(arg_1)
   Num(6)
   Id(arg_2)
   Id(five)
   Id(result)
   Id(multiply)
   ArgList
    Id(arg_1)
    Id(arg_2)
```

## Symbol Table & Semantic Analysis
I Used a vistor to generate the symbol table while also preforming semantic checks. I did this so I could take advantage of simpleNode to navigate the tree.

I used a HashMap data structure to store my symbol table.
```
HashMap<String, HashMap<String, STC>> ST = new HashMap();
```
The first key is used as the scope while the second is the identifier of the STC object.
The symbol table is created when the program node is visted it then loops through and checks all its child nodes.
```
public class TypeCheckVisitor implements CcalVisitor {
  private static HashMap<String, HashMap<String, STC>> ST = new HashMap<>();
```
Every time a declaration node is visted it creates the inner symbol table and puts it in the global symbol. It will return an error if an variable has already been declared in the same scope. The Scope is set every time a it enters a child node, it doesnt reset when it leaves a node just but regets the scope from the parent.
Symbol Table for multiply.ccal
```
Symbol Table:
__________global__________
__________main__________
type = int
value = result
type = int
value = arg_1
type = int
value = arg_2
type = int
value = five
__________multiply__________
type = int
value = result
type = bool
value = minus_sign
type = int
value = x
type = int
value = y
```
## Semtantic checks.

Though there is code attempting to this it is non functional to the spec.
```
Type Checking:
Not all declared functions were called
```
The code though not functioning was meant to check if all declared functions are used. It would do this by having a map of all declared function and called functions and compared them. It would add the declared functions to the list as it visited  the functions node, and the when it visted the node calling it it would add it to the called list. The code for the called list though didnt find the right functions so didnt function.
