/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// ------------------------------------
// Name : Landicho James Evans Arvesu
// Class : DIT/FT/1B/04
// Admission Number : P2107967
// ------------------------------------

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Admin {

    private String user;
    private String password;

    public Admin(String INPUT_user, String INPUT_password) {
        user = INPUT_user;
        password = INPUT_password;

    }

    protected void changePassword() {

        boolean hasValidated = false;
        do {
            try {
                String newPassword = JOptionPane.showInputDialog(null, "Type in a new password", "Change Password", JOptionPane.QUESTION_MESSAGE);
                
                // remove whitespace
                newPassword = newPassword.trim();
                
                if (isValidPassword(newPassword) == true) {

                    // set admin new password
                    password = newPassword;
                    JOptionPane.showMessageDialog(null, "Successfully changed password.", "Change Password", JOptionPane.INFORMATION_MESSAGE);

                    // logs
                    System.out.println("Successfully changed password.\nNew password : " + newPassword);
                    hasValidated = true;
                } else {
                    throw new IllegalArgumentException("\n***ERROR***\nPlease key in a password with:\n"
                            + "1. Atleast one numeric character\n"
                            + "2. Atleast one lowercase character and uppercase character\n"
                            + "3. Must have one special symbol among @#$%\n"
                            + "4. Password length should be between 8 and 20 characters.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "***ERROR***\nPlease key in a password with:\n"
                        + "1. Atleast one numeric character\n"
                        + "2. Atleast one lowercase character and uppercase character\n"
                        + "3. Must have one special symbol among @#$%\n"
                        + "4. Password length should be between 8 and 20 characters.", "Error", JOptionPane.ERROR_MESSAGE);

                //logs
                System.out.println(ex + "\n");
            }
        } while (hasValidated == false);
    }

    public static boolean isValidPassword(String password) {

        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    
    public String getUser(){
        return user;
    }
    
    public String getPassword(){
        return password;
    }
}

