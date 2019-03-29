#!/bin/sh

OUTPUT="$1"
RAWPATH="$2"

java -cp ".:lib/*:lib/datagen/*:lib/json/*:src/java/boa/dsi/*:lib/hadoop-dependencies/*:lib/datageneration/*" boa.bio.datagen.JSONGen $OUTPUT $RAWPATH

