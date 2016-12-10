rm -rf build/*
cp -r src/* build
cd build
jjtree ccal.jjt && javacc ccal.jj && javac *.java
