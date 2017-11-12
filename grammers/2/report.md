---
title: A Lexical and Syntax Analyser Report 
---

# CCal Parser

Author: Cian Butler
Student No.: 13373596

## Tokenisation

To begin I defined all the reserved symbols and words such as var, const, main and void.
I made these Tokens case insensitive by declaring them with `INORE_CASE`.
These are all the symbols used in my language for structure.
These are reserved for languages in the ccal and will we used when parsing the language.
These Tokens will but used to define variables and functions in the languages as well as will be used to define the logic and precedence later.

I then defined Digits, letters and any other symbols that I want to allow as part of variable names.
These are defined as any combination of each other of any length.
Numbers are defined as either being  0, or any positive or negative number not starting with 0.

I then add tokens for the symbols I want to ignore. These symbols include tabbing, spaces, newlines and comments.
There are two different types of comments that need to be ignored. Single line comment, those that start with `//`and multi-line comments that can be nested.
Single line comments are ignore by looking for `//` and ignoring all combinations letters and numbers of any length until there is an end of line.

I ignore multi-line comments by at the start of initialising a variable at 0 for determining if I am in a comment.
Then once it sees a `/*` it increments this variable.
All other character except for `/*` and `*/` are ignored using the symbol `~[]`.
The Program now ignores every token till is sees a `/*` in which case it increments the counter, or sees a `*/` in which case it decrements the counter.
Once the counter equals 0 it begins to parse the program again.

## Grammar

The grammar of the program defines how a program should be read and parsed.
I've defined the program so as to expect declarations first, which is a declaration of variables and constants, followed by a list of functions and the main method.

In the grammar I define how to declare functions variables and constants.
All use the type function to set there type but a function is the only thing that can be of type void so it void is set as an alternative to type for a function rather then a type.

Parameter is defined as an id followed by a type. It can be followed by another parameter is separated by a comma.
A parameter comma is optional but if it sees one it call itself to parse it.
A parameter can also be an empty object.

A statement recursively calls its self to allow for a list of statements that follow.
In it I define the how variables are assigned and the structure of a while loop as well as an if and its optional else statement.

I initially defined condition and expression as stated in the grammar outlined in the doc, but this produced left recursion and choice conflicts.
To deal with this I moved parts of the logic from condition in to expression and allowed expression to handle parsing those expressions of the grammar.
Condition function then handled only logical effects such as negation, comparative operations and chaining other conditions together.

Expression function handles combining variables with each other using arithmetic comparatives and optionally calls itself so as to combined multiple variables.
