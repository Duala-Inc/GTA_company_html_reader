package com.duala.ghana_registered_books_html_parser;

public class Book {
    String value ="";


    void put(String key, String val){
        if (value.isBlank())
        value += "\n" + key + " : " + val.strip();
        else
        value += ",\n" + key + " : " + val.strip();
    }

    void clear(){
        value = "";
    }

    @Override
    public String toString() {
        return "{\n\t" +
                value  +
                "\n\t}";
    }
}
