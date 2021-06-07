package com.akko.solver.exceptions;

public class LocationNotFound extends RuntimeException{
    public LocationNotFound(long id){
        super("There is no location with id: "+id+"\n");
    }
}
