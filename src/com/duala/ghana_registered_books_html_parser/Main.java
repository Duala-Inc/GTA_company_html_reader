package com.duala.ghana_registered_books_html_parser;


import java.io.FileNotFoundException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Parser reader = new Parser("C:\\Users\\KWAKU\\Desktop\\books.txt");
        try {
            reader.writeToFile("C:\\Users\\KWAKU\\Desktop\\books.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
