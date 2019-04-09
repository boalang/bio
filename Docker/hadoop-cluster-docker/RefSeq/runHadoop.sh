#!/bin/sh
BOAFILE="$1"
java -cp ".:lib/*:lib/datagen/*:lib/json/*:src/java/boa/dsi/*:lib/hadoop-dependencies/*:lib/datageneration/*" boa.DSMaster $BOAFILE . output

case "$1" in
	*.boa) FILENAME=`basename $1 | sed s/\.boa//` ;;
	*) ;;
esac

echo ${FILENAME^}


FILENAME2=${FILENAME^}
echo

hadoop jar  generatedsrc/$FILENAME2.jar boa.$FILENAME2 /dataset/ /tmp/$2
