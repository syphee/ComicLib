/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author James
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;

public class Rental_IO {

    ArrayList<Rentee> RenteeData = new ArrayList<Rentee>();
    ArrayList<Comic> ComicData = new ArrayList<Comic>();

    // testing purposes
    public static void main(String[] args) {

        Rental_IO main = new Rental_IO();
        main.StartIO("Input", null);
    }
    // file path
    private String filePath = "comic.dat";

    // Class<?> - to determine which class is being saved
    // method - for input or output method
    // Object[] data - the "json" containing info
    // set null to 2nd param to just read data
    public void StartIO(String method, ArrayList<Rentee> RenteeData) {
        System.out.println(method);
        switch (method) {
            case "Input": {
                InputMethod();
            }
            break;
            case "Output": {
                OutputMethod(RenteeData);
            }
            break;
        }
    }

    // Read rentee and comics
    public void InputMethod() {
        try {
            InputRentee();
            InputComic();
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    // Write rentee to comic.dat
    public void OutputMethod(ArrayList<Rentee> data) {
        OutputRentee(data);
    }

    // write / to save
    public void OutputRentee(ArrayList<Rentee> input) {
        System.out.println("RENTAL OUTPUT - OUTPUT COMIC");

        // name of file to be saved
        File file = new File(filePath);

        // array of rentees to be saved in
        Rentee[] data = new Rentee[input.size()];
        for (var i = 0; i < data.length - 1; i++) {
            data[i] = new Rentee(input.get(i).getID(), input.get(i).getName(), input.get(i).getComicsList());
        }

        try {
            FileOutputStream strmFileOut = new FileOutputStream(file);
            ObjectOutputStream strmObjOut = new ObjectOutputStream(strmFileOut);

            strmObjOut.writeObject(data);

            for (int i = 0; i < data.length; i++) {
                System.out.println(data[i]);
                strmObjOut.writeObject(data[i]);
            }

            strmObjOut.close();
            System.out.println("Success in operation.");
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    //read / to load 
    //take in array, split array into 2 [id, name] [ comics]
    //
    //take comics length and store it in rentee list using rentee method
    // take note of 2 delimiter - , and #
    public void InputRentee() throws IOException {
        String regex = ";|#";

        System.out.println("RENTAL INPUT - INPUT RENTEE");

        String sLine;
        String[] data = null;

        File fileSave = new File("rentees.txt");
        Scanner objScanner = new Scanner(fileSave);

        

        while (objScanner.hasNextLine()) {
            ArrayList<String> memberInf = new ArrayList<String>();
            ArrayList<String> memberComics = new ArrayList<String>();
            
            // clear every each line
            memberInf.clear();
            memberComics.clear();

            sLine = objScanner.nextLine();
            data = sLine.split(regex);

            System.out.println("\nMember Info");
            for (var i = 0; i < 2; i++) {
                System.out.println(data[i]);
                memberInf.add(data[i]);
            }
            System.out.println("Member comics");
            // add comics to separate array
            for (var i = 2; i < data.length; i++) {
                System.out.println(data[i]);
                memberComics.add(data[i]);
            }
            System.out.println("\n");

            // Add newly generated Rentee to database
            Rentee newRentee = new Rentee(data[0], data[1], memberComics);

            RenteeData.add(newRentee);
            // Add rentee to  arraylist, which is to be sent back to system as one whole, then in system, loop around this arraylist to init rentees.              
        }
        objScanner.close();

    }

// delimiter - ;
    public void InputComic() throws IOException {
        System.out.println("RENTAL INPUT - INPUT COMIC");
        String regex = ";|#";

        String sLine;
        String[] data = null;

        File fileSave = new File("comics.txt");
        Scanner objScanner = new Scanner(fileSave);

        while (objScanner.hasNextLine()) {
            
            // redeclare values every each line 
            ArrayList<String> comicsISBN = new ArrayList<String>();
            ArrayList<String> comicsInf = new ArrayList<String>();
            ArrayList<String> comicType = new ArrayList<String>();

            sLine = objScanner.nextLine();
            data = sLine.split(regex);

            System.out.println("\nComic ISBN");
            for (var i = 0; i < 1; i++) {
                System.out.println(data[i]);
                comicsISBN.add(data[i]);
            }
            System.out.println("Comic info");
            // add comics to separate array
            for (var i = 1; i <= 3; i++) {
                System.out.println(data[i]);
                comicsInf.add(data[i]);
            }

            System.out.println("\nComic Type");
            for (var i = 4; i < data.length; i++) {
                System.out.println(data[i]);
                comicType.add(data[i]);

            }

            String TypeOfComic = data[4];
            String TypeOfLanguage = data[5];

            if (TypeOfComic.equals("Comic")) {
                System.out.println("^Current book is a " + TypeOfComic);

                // ISBN ( String ), Title ( String ), Pageval ( int ), Cost ( Double )
                Comic newComic = new Comic(comicsISBN.get(0), comicsInf.get(0), Integer.parseInt(comicsInf.get(1)), Double.parseDouble(comicsInf.get(2)));
                ComicData.add(newComic);

            } else if (TypeOfComic.equals("Manga")) {
                // process language type

                // english translation
                if (TypeOfLanguage.equals("EN")) {
                    System.out.println("^Current book is a " + TypeOfComic + " Translated in " + TypeOfLanguage);
                    Manga newManga = new Manga(comicsISBN.get(0), comicsInf.get(0), Integer.parseInt(comicsInf.get(1)), Double.parseDouble(comicsInf.get(2)), "This is a Manga Translated to English");
                    ComicData.add(newManga);
                } // english translation
                else if (TypeOfLanguage.equals("JP")) {
                    System.out.println("^Current book is a " + TypeOfComic + " Translated in " + TypeOfLanguage);
                    Manga newManga = new Manga(comicsISBN.get(0), comicsInf.get(0), Integer.parseInt(comicsInf.get(1)), Double.parseDouble(comicsInf.get(2)), "This is a Manga in Japanese");
                    ComicData.add(newManga);
                }

            } else {
                System.out.println("Unknown comic type!!");

            }

            // move on to next book to be processed
        }
        objScanner.close();
    }

    public ArrayList<Rentee> retrieveRenteeDB() {
        return RenteeData;
    }

    public ArrayList<Comic> retrieveComicDB() {
        return ComicData;
    }

}
