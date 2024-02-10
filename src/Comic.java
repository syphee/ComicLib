
import java.io.Serializable;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// ------------------------------------
// Name : Landicho James Evans Arvesu
// Class : DIT/FT/1B/04
// Admission Number : P2107967
// ------------------------------------

public class Comic implements Serializable {

    private String isbnCode;
    private String comicTitle;
    private int pageVal;
    private double comicCost;
    private String additionalInfo;

    public Comic() {
    }

    public Comic(String INPUT_isbnCode, String INPUT_title, int INPUT_pageVal, double INPUT_cost) {
        isbnCode = INPUT_isbnCode;
        comicTitle = INPUT_title;
        pageVal = INPUT_pageVal;
        comicCost = INPUT_cost;
        
        additionalInfo = "This is a Comic in English";
        
    }

    // may need to math round
    public String getStatisticDetails() {
        return (comicTitle
                + "\n\nStock purchased at $" + comicCost
                + "\nCost $" + String.format("%.2f", (comicCost / 20)) + " per day to rent."
                + "\nRequire deposit of $" + String.format("%.2f", (comicCost + (comicCost * 0.1))));
    }

    public String getDetails() {
        return (isbnCode + "\t| "
                + comicTitle + "\t| "
                + pageVal + "\t| $"
                + String.format("%.2f", (comicCost / 20)) + "\t| $"
                + String.format("%.2f", (comicCost + (comicCost * 0.1))));

    }

    public double getCost() {
        return comicCost;
    }

    public String getTitle() {
        return comicTitle;
    }

    public String getISBN() {
        return isbnCode;
    }
    
    public String getAdditionalInfo(){
        return additionalInfo;
    }
    
    public void setAdditionalInfo(String input){
        additionalInfo = input;
    }

    public double getRentalPerDay() {
        return (comicCost / 20);
    }

    // EXTRA
    public void setCost(double INPUT_cost) {
        comicCost = INPUT_cost;
    }

    public void setTitle(String INPUT_Title) {
        comicTitle = INPUT_Title;
    }

    public void setISBN(String INPUT_ISBN) {
        if(String.valueOf(INPUT_ISBN).length() == 13 && INPUT_ISBN.matches("[0-9]+")){
        int offset = 3;
        String input = INPUT_ISBN;

        StringBuilder output = new StringBuilder(input);
        System.out.println("\nInputted ISBN : " + INPUT_ISBN);

        System.out.println("Formatting " + output + "...");

        output.insert(offset, '-');

        System.out.println("\nFormatted ISBN : " + output.toString());
        isbnCode = output.toString();
        }else{
            JOptionPane.showMessageDialog(null, "ISBN-13 Must be of 13 Numbers!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("ISBN-13 Must be of 13 Numbers,NUMBERS!!!");
        }
    }

    public String getDetailsHTML() {
        return ("<html>ISBN Code : " + isbnCode + "<br>" +
                "Comic Title : " + comicTitle + "<br>" +
                "Number of Pages : " + pageVal + "<br>" +
                "Rental Fee per day : " + String.format("%.2f", (comicCost / 20)) + "<br>" +
                "Deposit fee : " + String.format("%.2f", (comicCost + (comicCost * 0.1))) + "<br>"
                + "<br>For ISBN Code, Please enter numbers without the \"-\". " + "</html>");


    }
    
    public int getAmtofPages(){
        return pageVal;
    }
    
    
    
    

}
