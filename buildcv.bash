#!/bin/bash

#TERMINATE ON ERRORS
# set -e

# ------------------
# Absolute path to this script. /home/user/bin/foo.sh
SCRIPT=$(readlink -f $0)
# Absolute path this script is in. /home/user/bin
SCRIPTPATH=`dirname $SCRIPT`

BUILDPATH=$SCRIPTPATH/build

cd $SCRIPTPATH/opencv

mkdir build

cd build

PATH=$PATH:/home/oleg/Qt/5.4/gcc_64 cmake -D CMAKE_BUILD_TYPE=RELEASE \
    -D CMAKE_INSTALL_PREFIX=$BUILDPATH \
    -D CLAMDBLAS_ROOT_DIR=$AMDAPPSDKROOT/clBLAS \
    -D CLAMDFFT_ROOT_DIR=$AMDAPPSDKROOT/clFFT \
    -D WITH_TBB=ON \
    -D BUILD_NEW_PYTHON_SUPPORT=ON \
    -D WITH_V4L=ON \
    -D WITH_QT=ON \
    -D WITH_OPENGL=ON \
    ..

make install -j 8

cd -
