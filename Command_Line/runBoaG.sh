#!/bin/sh
BOAFILE="$1"
REFSEQ="$2"
OUTPUT="$3"
java -cp ".:lib/*:lib/datagen/*:lib/json/*:src/java/boa/dsi/*:lib/hadoop-dependencies/*:lib/datageneration/*" boa.DSMaster $BOAFILE $REFSEQ $OUTPUT

