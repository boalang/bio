#!/bin/bash

echo -e "\n"

$HADOOP_HOME/sbin/start-dfs.sh

echo -e "\n"

$HADOOP_HOME/sbin/start-yarn.sh

echo -e "\n"

hadoop fs -mkdir /dataset/
hadoop fs -mkdir /tmp/

echo -e "\n"


hadoop fs -put /tmp/genomes.seq /dataset/
hadoop fs -put /tmp/features.seq /dataset/
hadoop fs -put /tmp/assemblers.seq /dataset/

echo -e "\n"

mv /tmp/RefSeq ~/RefSeq
