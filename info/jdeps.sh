#!/bin/bash

#Parameters: JDK directory, JavaFX lib directory

RETURN=$PWD
cd "$( dirname "${BASH_SOURCE[0]}" )"
$1/bin/jdeps \
  --module-path ../out/production:$2 \
  --module kdE_K
cd $RETURN