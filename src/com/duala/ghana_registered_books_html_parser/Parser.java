package com.duala.ghana_registered_books_html_parser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
    String fileContents = "";
    ArrayList<String> parsedContents = new ArrayList<>();


    Parser(String[] paths){
        //read the files
        for (String path: paths) {
            try {
                readFile(path);
            } catch (Exception e){
                System.out.println("Error with file reader");
            }

        }

        //split the file contents
//        parse();
//
//        convertToJSON();
    }


    /**
     * Reads the file contents
     * @param path
     * @throws IOException
     */
    void readFile(String path) throws IOException {
        FileReader in1 ;
        BufferedReader br = null;
        String temp_fileContents = "";

        try{
            in1 = new FileReader(path);
            br = new BufferedReader(in1); // can also be a oneliner

            String x;
            while ((x=br.readLine()) != null) {
                temp_fileContents += x;
            }
        }
        finally{
            if (br !=null){
                br.close();
            }
            fileContents += temp_fileContents;
        }
    }

    void parse(){
        String splitContents[] = fileContents.split("</tr>");
        String entry = "";

        //splitting each row into cells
        for (String row: splitContents) {
            String cells[];
            cells = row.split("><");

            int start_ = 1;
            if (cells.length == 7){
                start_++;
            }
            for (int i = start_; i < start_+4; i++) {
                entry +=","+clean(cells[i]);
            }
//            System.out.println(entry.substring(1));
            parsedContents.add(entry.substring(1));
            entry = "";
        }

    }


    String clean(String string){
        int start = string.indexOf(">")+1;
        int last = string.indexOf("<");
        try{

            return string.substring(start,last).replaceAll(",", " ").replaceAll("&amp;","&");
        } catch (Exception e){
            return "";
        }
    }


    void write(){
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("C:\\Users\\KWAKU\\Desktop\\companies.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (String line : parsedContents) {
            pw.println(line);
        }
        pw.close();
    }



}
