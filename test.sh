#! /usr/bin/env sh

set -e

noGram() {
  echo "Grammer doesn't exist"
  exit 1
}
fail() {
  echo "====== $1 Fail ========="
  exit 1
}

rm -rf build
cp -r "$1" build
cd build
for grammer in grammers/*; do
  cd "$grammer" || noGram
  jjtree ccal.jjt 1>/dev/null
  javacc ccal.jj 1>/dev/null
  javac ./*.java 1>/dev/null
  echo "======== Testing $grammer ========="
  for script in test/*.ccal; do
    echo "======== Testing $script ========="
    java Ccal "$script" || fail "$script"
  done
  cd ../..
done
