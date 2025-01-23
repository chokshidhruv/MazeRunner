#!/bin/bash

# Install package and run the MazeRunner
ARGS=$1

# For testing purposes I am going to put a default file

mvn clean package
# java -jar target/mazerunner.jar -i $ARGS 
java -jar target/mazerunner.jar -i ./examples/small.maz.txt

