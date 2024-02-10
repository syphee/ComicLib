/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// ------------------------------------
// Name : Landicho James Evans Arvesu
// Class : DIT/FT/1B/04
// Admission Number : P2107967
// ------------------------------------

import java.io.Serializable;
import java.util.ArrayList;

public class Rentee implements Serializable{

    private String memberID;
    private String name;

    protected ArrayList<Comic> comicList = new ArrayList<Comic>();
    
    // CA2 Method changes
    //From storing actual comic objects to arraylist, store just ISBN for comics, then later on use comic method, rentee ISBN to find comics
    protected ArrayList<String> comicsList = new ArrayList<String>();
    
    public Rentee() {
    }

    ;
    public Rentee(String INPUT_memberID, String INPUT_name,ArrayList<String> INPUT_ComicList) {
        memberID = INPUT_memberID;
        name = INPUT_name;
        
        
        // CA2 changes
        comicsList = INPUT_ComicList;

    }

    public String getID() {
        return memberID;
    }
    
    public ArrayList<Comic> getComicArr(){
        return comicList;
    }

    public String getName() {
        return name;
    }

    // to add all found comics via ISBN - refer to RentalSystem Class
    public void storeComicList(ArrayList<Comic> list) {
        comicList.addAll(list);
    }
    
    public void addComic(Comic input){
        comicList.add(input);
    }
    
    public int returnComicSize(){
        return comicList.size();
    }

    public String displayComics() {
        String output = "";
        double rentVal = 0;

        for (var i = 0; i < comicList.size(); i++) {
            output += ("\n" + (i + 1) + ". " + (comicList.get(i).getTitle()));

            rentVal += (comicList.get(i).getRentalPerDay());
        }

        output += ("\n\nTotal Rental Per Day :$" + String.format("%.2f", rentVal));
        return output;
    }
    
    // return comic based on isbn
    public String getComicISBN(int index){
        return comicsList.get(index);
    }
    
    public String getComicTitle(int index){
        return comicList.get(index).getTitle();
    }
    
    

    // EXTRA
    public String getDetailsHTML() {
        return ("<html>Member ID : " + memberID + "<br> "
                + "Name : " + name + "<br>"
                + "For Member ID, please enter numbers without the \"M\". </html>");
    }

    public void setID(String ID) {
        if (String.valueOf(ID).length() == 6 && ID.matches("[0-9]+")) {
            int offset = 0;
            String input = ID;

            StringBuilder output = new StringBuilder(input);
            System.out.println("\nInputted ISBN : " + ID);

            System.out.println("Formatting " + output + "...");

            output.insert(offset, 'M');

            System.out.println("\nFormatted ISBN : " + output.toString());

            memberID = output.toString();
        } else {
            throw new IllegalArgumentException("Member Must be of 6 Numbers,NUMBERS!!!");
        }
    }

    public void setName(String INPUT_name) {
        name = INPUT_name;
    }
    
    public ArrayList<String> getComicsList(){
        return comicsList;
    }
    
    public ArrayList<Comic> getComicsListArray(){
        return comicList;
    }
    
   
}
