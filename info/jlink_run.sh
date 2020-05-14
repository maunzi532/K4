#!/bin/bash

#Parameters: JDK directory, JavaFX jmods directory
#Use inverted vm option order

RETURN=$PWD
cd "$( dirname "${BASH_SOURCE[0]}" )"
rm -rf ../out/jlink
$1/bin/jlink \
  --module-path ../out/production:$2 \
  --add-modules kdE_K \
  --output ../out/jlink \
  --launcher KdE_K=kdE_K/gui.TextGUI \
  --add-options \"-XX:+UseZGC\" \
  --add-options \"-XX:+UnlockExperimentalVMOptions\" \
  --add-options \"--enable-preview\"
../out/jlink/bin/KdE_K
cd $RETURN