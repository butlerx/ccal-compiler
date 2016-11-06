javacc ccal.jj && javac *.java &&
for script in test/*.ccal; do echo $script; java Ccal $script || exit 1; done
