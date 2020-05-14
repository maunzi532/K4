#!/bin/bash

RETURN=$PWD
cd "$( dirname "${BASH_SOURCE[0]}" )"
../out/jlink/bin/KdE_K
cd $RETURN