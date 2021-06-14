package com.campominado.Exceptions;

public class LeaveException extends RuntimeException{

    static final long serialVersionUID = 2L;

    @Override
    public String getMessage() {
        return "Até mais";
    }
}
