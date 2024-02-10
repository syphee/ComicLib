/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// ------------------------------------
// Name : Landicho James Evans Arvesu
// Class : DIT/FT/1B/04
// Admission Number : P2107967
// ------------------------------------

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

// TODO - Invalid password validation - Popup " Does not exist in database!" pops up in 2nd window when invalid password inputted
public class AdminSystem {

    private ArrayList<Admin> adminLoginArr = new ArrayList<Admin>();

    public AdminSystem() {
        adminLoginArr.add(new Admin("James", "pa$$woRD123"));
        adminLoginArr.add(new Admin("Bob", "pa$$woRD123"));

    }

    // hardcoded Database verification
    public boolean verifyAdmin(String user, String password) {
        boolean doesExist = false;
        boolean isIncorrectPassword = false;

        for (var i = 0; i < adminLoginArr.size(); i++) {

            // Verify existence of user
            if (user.equals(adminLoginArr.get(i).getUser())) {
                System.out.println("Found username in database..Verifying password..");

                if (password.equals(adminLoginArr.get(i).getPassword())) {
                    System.out.println("Successfully verified user..Redirecting to menu..");
                    doesExist = true;

                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Password. Please enter again or contact the System Administrator.");

                    // logs
                    System.out.println("Incorrect password!");
                    isIncorrectPassword = true;
                }
            }
        }

        // determines admin rights to the menu if admin indeed is correct.
        if (doesExist == true) {
            // if account verified
            JOptionPane.showMessageDialog(null, "Launching System with administrator privileges..", "System", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            // if incorrect password input
            if (isIncorrectPassword != true) {
                // if account not verified / does not exist in database
                JOptionPane.showMessageDialog(null, "Account does not exist within the database! Please contact the System administrator.", "Error", JOptionPane.ERROR_MESSAGE);

            }
            // else will not show dialog, will just return not logged in.
            return false;
        }
    }

    public String getUserName(int index) {
        return adminLoginArr.get(index).getUser();
    }

    // Login page
    public boolean verifyLogin() {

        JTextField username = new JTextField(5);
        JPasswordField password = new JPasswordField(5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Username :"));
        myPanel.add(username);
        myPanel.add(Box.createHorizontalStrut(30)); // a spacer
        myPanel.add(new JLabel("Password :"));
        myPanel.add(password);

        String[] buttons = {"Login", "Continue as Guest", "Shut Down"};

        boolean hasLoggedIn = false;

        // Returns true / false value depending if logged in as admin or guest.
        boolean isAdmin = false;
        boolean state = false;

        int result;

        do {
            result = JOptionPane.showOptionDialog(null, myPanel, "Please Enter your username and password.", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[0]);

            // if first option
            if (result == 0) {

                System.out.println("<--- Log In --->");
                System.out.println("Login : " + username.getText());
                System.out.println("Password : " + password.getText());
                System.out.println("<------------->");

                // returns true when successfully logged in.
                if (verifyAdmin(username.getText(), password.getText()) == true) {
                    hasLoggedIn = true;
                    isAdmin = true;
                    JOptionPane.showMessageDialog(null, "Welcome to SP Comic Rental System, " + username.getText() + ".");
                } else {
                    hasLoggedIn = false;
                    isAdmin = false;

                    // empties text fields when unsuccessful log in
                    username.setText("");
                    password.setText("");
                }

            } // if second option
            else if (result == 1) {
                JOptionPane.showMessageDialog(null, "Welcome to SP Comic Rental System, Guest.");
                System.out.println("Continuing as guest..");
                hasLoggedIn = true;

                // return not admin
                isAdmin = false;
            } else if (result == 2) {
                JOptionPane.showMessageDialog(null, "Thank you for using Comic Rental.\nWe look forward to serve you in the near future.");
                System.exit(0);
                // log
                

                // decided to forego System.exit(0); as I am not able to get the uptime of system as System.exit(0) completely shuts down the whole program
                // https://stackoverflow.com/questions/32790/alternatives-to-system-exit1
            } else if (result == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Please log in.", "Login", JOptionPane.ERROR_MESSAGE);
                hasLoggedIn = false;
                isAdmin = false;

            }

        } while (result == JOptionPane.CLOSED_OPTION || hasLoggedIn == false);
        // forces program to continue to run for user to login

        // returns true value if admin and is logged in already / false if not admin and is logged in as guest.
        if (hasLoggedIn == true && isAdmin == true) {
            state = true;
        } else if (hasLoggedIn == true && isAdmin == false) {
            state = false;
        }

        return state;
    }

    public void manageAdmin() {
        String menu = "<html><center>Manage Admin<br>"
                + "***************************************<br><br>"
                + "1. Add Administrator<br>Add an adminstrator<br><br>"
                + "2. Delete Administrator<br>Delete an administrator<br><br>"
                + "***************************************</center></html>";

        String[] list = {"Add Administrator", "Delete Administrator", "Exit"};

        int adminChoice = 0;

        do {
            try {
                adminChoice = JOptionPane.showOptionDialog(null, menu, "Admin Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, list, list[0]);

                // Admin menu
                switch (adminChoice) {
                    case 0:
                        System.out.println("Adding Administrator..");
                        addAdmin();
                        break;
                    case 1:
                        System.out.println("Delete an Administrator...");
                        deleteAdmin();
                        break;

                    case 2:
                        System.out.println("Exiting Administrator Management..");
                        break;
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } while (adminChoice != 2);

    }

    public void addAdmin(String username, String createPassword, String confirmPassword) {
//        JTextField username = new JTextField(5);
//        JTextField createPassword = new JTextField(5);
//        JPasswordField confirmPassword = new JPasswordField(5);

        // Creating custom panel
//        JPanel myPanel = new JPanel();
//
//        //ISBN 13
//        myPanel.add(new JLabel("<html>Please enter the values.<br><br>For Password, Please key in a password with:<br>1. Atleast one numeric character<br>"
//                + "2. Atleast one lowercase characer and uppercase character<br>"
//                + "3. Must ave one special symbol among @#$%<br>"
//                + "4. Password length should be between 8 and 20 characters.<br></html>"));
//        myPanel.add(Box.createHorizontalStrut(30)); // a spacer
//
//        //Username input
//        myPanel.add(new JLabel("Create username :"));
//        myPanel.add(username);
//        //myPanel.add(Box.createHorizontalStrut(30)); // a spacer
//
//        //Create password input
//        myPanel.add(new JLabel("Password :"));
//        myPanel.add(createPassword);
//
//        //Confirm password input
//        myPanel.add(new JLabel("Confirm Password :"));
//        myPanel.add(confirmPassword);

        //validate exit
        // We do not want to end the program once a new admin is created, as we may want to create more than 1 admin account when we open the "Manage Administrators" menu. Thus,
        // I have left it to exit once the user has pressed the "Exit" button.
        boolean hasPressedExit = false;

        // Button options
        String[] buttons = {"Create administrator", "Exit"};

      
            

                // If create administrator button pressed

                    if (username.isBlank() || createPassword.isBlank() || confirmPassword.isBlank()) {
                        JOptionPane.showMessageDialog(null, "You have missing fields. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        
                    }
                    //logs output for created account
                    System.out.println("<--Create Administrator-->");

                    System.out.println("Login : " + username);
                    System.out.println("Password : " + createPassword);
                    System.out.println("Confirm Password : " + confirmPassword);

                    // Validations
                    boolean alreadyExists = false;

                    // check if input name already exists in the database.
                    // We do not want to check if first casing is upper ; I believe every username should be "unique" ; I made it to only invoke an error if the username 
                    // matches 1 to 1.
                    for (var i = 0; i < adminLoginArr.size(); i++) {
                        if (username.equals(adminLoginArr.get(i).getUser())) {

                            // Username already exists
                            alreadyExists = true;
                        }
                    }
                    // Check if password input is the same, then check if password meets password requirements, and if all fields are inputted

                    if (alreadyExists == false && username.isBlank() == false) {
                        if (createPassword.equals(confirmPassword)) {
                            System.out.println("Input password matches, Validating password...\n");

                            // Validate password if matches requirements
                            if (isValidPassword(createPassword)) {
                                JOptionPane.showMessageDialog(null, "Administrator with Name and Password:\n\nName : " + username + "\nPassword :" + createPassword + "\n\nhas successfully been created.");

                                // add the newly created admin
                                adminLoginArr.add(new Admin(username, createPassword));

                                // logs
                                System.out.println(username);
                            } else {
                                JOptionPane.showMessageDialog(null, "***ERROR***\nPlease key in a password with:\n"
                                        + "1. Atleast one numeric character\n"
                                        + "2. Atleast one lowercase character and uppercase character\n"
                                        + "3. Must have one special symbol among @#$%\n"
                                        + "4. Password length should be between 8 and 20 characters.", "Error", JOptionPane.ERROR_MESSAGE);

                                // logs 
                                System.out.println("\n***ERROR***\nPlease key in a password with:\n"
                                        + "1. Atleast one numeric character\n"
                                        + "2. Atleast one lowercase character and uppercase character\n"
                                        + "3. Must have one special symbol among @#$%\n"
                                        + "4. Password length should be between 8 and 20 characters.");
                            }

                        }// if passwords do not match
                        else {
                            JOptionPane.showMessageDialog(null, "Passwords do not match. Please enter again.", "Error", JOptionPane.ERROR_MESSAGE);

                            // logs
                            System.out.println("Passwords do not match. Please enter again.\n");
                        }
                    } // if username already exists
                    else {
                        if (alreadyExists == true) {
                            JOptionPane.showMessageDialog(null, "Username already exists in the database. Please re-enter a username.", "Error", JOptionPane.ERROR_MESSAGE);

                            // logs
                            System.out.println("\n\n***ERROR***");
                            throw new IllegalArgumentException("Username already exists in the database. Please re-enter a username.\n");

                            // if missing fields
                        } else if (username.isBlank() == true || createPassword.isBlank() == true || confirmPassword.isBlank() == true) {
                            JOptionPane.showMessageDialog(null, "Missing fields. Please input again.", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }

                } // exits


            
            

        

    

    public void deleteAdmin(String username, String confirmPassword, String masterKey) {
        String SYSTEM_masterKey = "Please";
//        JTextField username = new JTextField(5);
//        JPasswordField confirmPassword = new JPasswordField(5);
//        JTextField masterKey = new JTextField(5);

//        // Creating custom panel
//        JPanel myPanel = new JPanel();
//
//        //Username input
//        myPanel.add(new JLabel("Username :"));
//        myPanel.add(username);
//        //myPanel.add(Box.createHorizontalStrut(30)); // a spacer
//
//        //Confirm password input
//        myPanel.add(new JLabel("Confirm Password :"));
//        myPanel.add(confirmPassword);
//
//        // Master key -  hardcoded fixed key
//        myPanel.add(new JLabel("Enter Master Key :"));
//        myPanel.add(masterKey);
//
//        String SYSTEM_masterKey = "Please";
//        //validate exit
//        // We do not want to end the program once an admin, as we may want to delete more than 1 admin account when we open the "Manage Administrators" menu. Thus,
//        // I have left it to exit once the user has pressed the "Exit" button.
//        boolean hasPressedExit = false;

        // Button options




        
         
                    //logs output for created account
                    System.out.println("<--Create Administrator-->");

                    System.out.println("Login : " + username);
                    System.out.println("Confirm Password : " + confirmPassword);

                    // Validations
                    boolean alreadyExists = false;

                    // check if there is still 1 existing admin in the database.
                    // We do not want to delete all admins in 1 session or else the admin panel will not be accessible.
                    if (adminLoginArr.size() == 1) {
                        // closes program

                        JOptionPane.showMessageDialog(null, "You may no longer further delete administrators. Please contact the System Administrator.");

                    }

                    // check for admin
                    for (var i = 0; i < adminLoginArr.size(); i++) {

                        if (username.equals(adminLoginArr.get(i).getUser()) && confirmPassword.equals(adminLoginArr.get(i).getPassword()) && masterKey.equals(SYSTEM_masterKey)) {
                            System.out.println("\nACCOUNT FOUND!");
                            // Username exists
                            alreadyExists = true;

                            // Deletes administrator
                            adminLoginArr.remove(i);
                            JOptionPane.showMessageDialog(null, "Administrator with username " + username + " has successfully been deleted.");

                            //logs 
                            System.out.println("\nSuccessfully deleted Administrator : " + username);

                        }
                        // if wrong input
                    }

                    // error validation
                    if (alreadyExists == false) {

                        // Missing fields
                        if (masterKey.isEmpty() || username.isEmpty() || confirmPassword.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "You have missing fields. Please check again.", "Error", JOptionPane.ERROR_MESSAGE);
                        } // If wrong username / password
                        else if (masterKey.equals(SYSTEM_masterKey) == true & username.isEmpty() == false && confirmPassword.isEmpty() == false) {
                            JOptionPane.showMessageDialog(null, "Username / Password is Invalid. Please try again or contact your System Administrator.", "Error", JOptionPane.ERROR_MESSAGE);
                        } // Master key invalid
                        else if (masterKey.equals(SYSTEM_masterKey) != true) {
                            JOptionPane.showMessageDialog(null, "Invalid Masterkey. Please try again or contact the System Administrator.", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }

                } // exits

    



    

    // testing methods purposes
    public static void main(String[] args) {
        AdminSystem S1 = new AdminSystem();
        S1.manageAdmin();

    }

    public static boolean isValidPassword(String password) {

        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
