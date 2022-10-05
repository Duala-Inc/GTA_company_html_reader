package com.duala.ghana_registered_books_html_parser;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        Parser parser = new Parser(new String[]{
                "C:\\Users\\KWAKU\\Desktop\\gta_1.txt",
                "C:\\Users\\KWAKU\\Desktop\\gta_2.txt",
                "C:\\Users\\KWAKU\\Desktop\\gta_3.txt",
        });

        parser.parse();
        parser.write();
//        try {
//            parser.writeToFile("C:\\Users\\KWAKU\\Desktop\\books.json");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


    }
}
