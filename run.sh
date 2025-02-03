#!/bin/bash
# Please note, this script is for running the MazeRunner - solely intended for myself to run and test the MazeRunner. 
# This script is not intended for the user to run the MazeRunner as it has file path hardcoded.

# Install package and run the MazeRunner
ARGS=$1
mvn clean package
java -jar target/mazerunner.jar -i ./examples/straight.maz.txt


