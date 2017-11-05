# ccal-compiler
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fbutlerx%2Fccal-compiler.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2Fbutlerx%2Fccal-compiler?ref=badge_shield)

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


## License
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fbutlerx%2Fccal-compiler.svg?type=large)](https://app.fossa.io/projects/git%2Bgithub.com%2Fbutlerx%2Fccal-compiler?ref=badge_large)