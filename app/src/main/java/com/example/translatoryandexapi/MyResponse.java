package com.example.translatoryandexapi;
import java.util.Arrays;

public class MyResponse {
    int code;
    String lang;
    String[] text;

    @Override
    public String toString(){
        return Arrays.toString(text);
    }
}
