#!/bin/bash

#
# Author: Robert Dyer <rdyer@bgsu.edu>
#

# path to Hadoop install root
HADOOP_HOME=/usr/local/hadoop-1.2.1
COMPILER_HOME=/home/hadoop/compiler

if [ $# -eq 2 ] && [ -n "$2" ]; then
	DIR=$2
else
	DIR=current
fi

MAIN="boa.compiler.BoaCompiler"
JAR="boa-compiler.jar"

java -cp $COMPILER_HOME/$DIR/dist/$JAR:.:`$HADOOP_HOME/bin/hadoop classpath` $MAIN -i $1

#sudo find /tmp -name "$1" >> logs.txt

#cp -r *.jar /tmp/
