#!/bin/bash

yarn application -status $1| awk '/Start-Time/ || /Finish-Time/' | paste - -  | awk '{print ($6-$3)/60000}'
