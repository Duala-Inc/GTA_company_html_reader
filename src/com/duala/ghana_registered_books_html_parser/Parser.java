package com.duala.ghana_registered_books_html_parser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
    String fileContents = "";
    ArrayList<String> parsedContents = new ArrayList<>();
    HashMap<String, Book > json = new HashMap<>();


    Parser(String path){
        //read the file
        try {
            readFile(path);
        } catch (Exception e){
            System.out.println("Error with file reader");
        }

        //split the file contents
        parse();

        convertToJSON();
    }


    /**
     * Reads the file contents
     * @param path
     * @throws IOException
     */
    void readFile(String path) throws IOException {
        FileReader in1 ;
        BufferedReader br = null;

        try{
            in1 = new FileReader(path);
            br = new BufferedReader(in1); // can also be a oneliner

            String x;
            while ((x=br.readLine()) != null) {
                fileContents += x;
            }
        }
        finally{
            if (br !=null)
                br.close();
        }
    }

    void parse(){
        String splitContents[] = fileContents.split("</tbody");

        //spliting each table
        for (String table: splitContents) {
            String[] temp;
            temp = table.split("</tr>");

            //spliting each row
            for (String row: temp ) {
                String[] tempCell = row.split("</td>");
                for (String value: tempCell) {
                    int index = value.lastIndexOf(">");
                    parsedContents.add(value.substring(index+1));

                }
            }
        }
    }


    void convertToJSON(){
        Book book = new Book();
        ArrayList<String> temp = new ArrayList<>();
        int index = 0;
        for (String value: parsedContents) {

            //reset temp contents if 8 elements have been stored
            // 8 because there are 8 cells for each book
            if(temp.size() == 8) {
                book.put("s/n", temp.get(0));
                book.put("subject", temp.get(1));
                book.put("title", temp.get(2));
                book.put("isbn", temp.get(3));
                book.put("level", temp.get(4));
                book.put("type", temp.get(5));
                book.put("publisher", temp.get(6));
                book.put("author", temp.get(7));


                index++;
                json.put(String.valueOf( index),book);
                book = new Book();
                temp.clear();
            }

            temp.add(value);
        }
    }


    void writeToFile(String path) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(path);

        for(int index = 1; index <= json.size(); index++){
            Book book = json.get(String.valueOf(index));
            pw.print(book.toString());
            pw.println(",");

        }
        pw.close();
    }


}
