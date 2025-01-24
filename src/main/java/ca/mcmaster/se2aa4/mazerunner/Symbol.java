package ca.mcmaster.se2aa4.mazerunner;

public enum Symbol{
    WALL('#'), EMPTY(' ');

    private char symbol;

    Symbol(char symbol){
        this.symbol = symbol;
    }

    public String toString(){
        return String.valueOf(symbol);
    }
}