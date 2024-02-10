/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author James
 */
public class Manga extends Comic {
    private String translation;
    public Manga(){}
    
    public Manga(String INPUT_isbnCode, String INPUT_title, int INPUT_pageVal, double INPUT_cost,String INPUT_translation){
        super(INPUT_isbnCode,INPUT_title,INPUT_pageVal,INPUT_cost);
        
        translation = INPUT_translation;
    }
    
    
    @Override
    public String getAdditionalInfo(){
        
        return translation;
    }
}
