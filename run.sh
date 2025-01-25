#!/bin/bash

# Install package and run the MazeRunner
ARGS=$1

# For testing purposes and for the MVP, I am going to put the straight maze file

mvn clean package
# java -jar target/mazerunner.jar -i $ARGS 
java -jar target/mazerunner.jar -i ./examples/straight.maz.txt

