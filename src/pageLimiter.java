/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author James
 */

// TODO - add a set method , because maxPage val will change when rentee / comic is added or removed
public class pageLimiter {
    
    // to take max pages
    private int maxPage;
    int increment = 0;
    
    public pageLimiter(int INPUT_maxPage){
        maxPage = INPUT_maxPage;
    }
    
    public int nextPage() throws Exception{
        System.out.println(increment);
        int output;
        // if has reached max pages
        
        if(increment >= maxPage - 1){
            increment = maxPage -1;
            throw new Exception("Reached max pages!");
            
        // always set to 1st page when user tries to go lower than 1
        
        }else{
            increment += 1;
        }
        return increment;
    }
    
    public int prevPage() throws Exception{
        System.out.println(increment);
        int output;
        // if is on page 1
        if(increment <= 0){
            increment = 0;
            throw new Exception("Reached lowest page!");
            
        // always set to 1st page when user tries to go lower than 1
        }else if (increment > 0){
            increment -= 1;
        }
        return increment;
    }
    
    // added plus 1 
    public int getPageValue(){
        return increment ;
    }
    
    public void overridePageValue(int value){
        increment = value;
    }
    
    // to use when adding rentee / comic / member id
    // override with array size
    public void overrideMaxPage(int value){
        maxPage = value;
    }
    
    
    
}
