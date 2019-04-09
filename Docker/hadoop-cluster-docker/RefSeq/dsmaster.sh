#!/bin/sh
BOAFILE="$1"
java -cp ".:lib/*:lib/datagen/*:lib/json/*:src/java/boa/dsi/*:lib/hadoop-dependencies/*:lib/datageneration/*" boa.DSMaster $BOAFILE /tmp/ output
