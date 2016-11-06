# ccal-compiler
javacc-compiler for ccal

## Build
To build just run
```
javacc ccal.jj
javac *.java
```

## Usage
ccal can be used multiple ways
 - it can read from standard input if `java Ccal` is called using ctrl+d to exit
If youve a ccal file already you can either run it
```
java Ccal < input.ccal
```
or
```
java Ccal input.ccal
```

## Tests
To test just run
```
./test.sh
```
