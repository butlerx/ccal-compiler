#! /usr/bin/env sh

set -e

noGram () {
  echo "Grammer doesn't exist"
  exit 1
}
fail () {
  echo "====== $1 Fail ========="
  exit 1
}

rm -rf build
cp -r src build
cd build
for grammer in grammers/*; do
  cd "$grammer" || noGram
  jjtree ccal.jjt
  javacc ccal.jj
  javac ./*.java
  for script in test/*.ccal; do
    echo "======== Testing $script ========="
    java Ccal "$script" 2> /dev/null || fail "$script"
  done
  cd ../..
done
