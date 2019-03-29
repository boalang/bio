#!/bin/bash
 
#
# Author: Robert Dyer <rdyer@iastate.edu>
#

# path to Hadoop install root
HADOOP_HOME=/usr/local/hadoop-1.2.1
# path on HDFS where sequence files are stored
CACHE_ROOT="/repcache"
# path on HDFS where output is stored
OUTPUT_ROOT="/user/hadoop"

case "$1" in
	*.jar) CLASS=`basename $1 | sed s/\.jar//` ;;
	*) ;;
esac

$HADOOP_HOME/bin/hadoop fs -test -e $OUTPUT_ROOT/$3
if [ $? -eq 0 ]; then
	$HADOOP_HOME/bin/hadoop fs -rmr $OUTPUT_ROOT/$3
fi

MAIN="boa.$CLASS"
INPUT="$CACHE_ROOT/$2/"

$HADOOP_HOME/bin/hadoop jar $1 $MAIN ${*:4} $INPUT $OUTPUT_ROOT/$3
