#!/bin/sh
BOAFILE="$1"
OUTPUT="$2"
java -cp ".:lib/*:lib/datagen/*:lib/json/*:src/java/boa/dsi/*:lib/hadoop-dependencies/*:lib/datageneration/*" boa.DSMaster $BOAFILE . $OUTPUT

