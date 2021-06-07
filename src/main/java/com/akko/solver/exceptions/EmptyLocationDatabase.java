package com.akko.solver.exceptions;

public class EmptyLocationDatabase extends RuntimeException{
    public EmptyLocationDatabase(){
        super("Database is empty!\n");
    }
}
