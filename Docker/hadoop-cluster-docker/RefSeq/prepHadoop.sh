#!/bin/sh
hadoop fs -mkdir /dataset
hadoop fs -put /pylon5/mc5fr5p/hbagheri/00_dataset/annotations.seq  /dataset
hadoop fs -ls /dataset/
