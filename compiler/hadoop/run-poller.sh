#!/bin/sh

SCRIPT=`which $0`

cd `dirname $SCRIPT`

CLASSPATH=`/usr/local/hadoop-1.2.1/bin/hadoop classpath`
export CLASSPATH=.:$CLASSPATH
javac BoaCompilePoller.java
exec java BoaCompilePoller
