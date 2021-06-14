package com.campominado.Exceptions;

public class BombException extends  RuntimeException{

    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return "-_- " +
                "Game" +
                "Over" +
                "-_-";
    }
}
