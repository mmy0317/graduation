package com.mayang.api.utils;

public class MyException extends RuntimeException{
    private static final long serialVersionUID = 16746723L;

    public MyException(){
        super();
    }

    public MyException(String s){
        super(s);
        }
}
