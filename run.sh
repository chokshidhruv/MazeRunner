#!/bin/bash

# Install package and run the MazeRunner
ARGS=$1
mvn clean package
# java -jar target/mazerunner.jar -i $ARGS 
java -jar target/mazerunner.jar -i ./examples/straight.maz.txt

