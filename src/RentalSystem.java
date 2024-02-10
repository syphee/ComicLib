

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// ------------------------------------
// Name : Landicho James Evans Arvesu
// Class : DIT/FT/1B/04
// Admission Number : P2107967
// ------------------------------------
// TODO - Add rand ISBN generator when making new comic using math.random() - ( FORGONE )
// 
// TODO - Convert normal array to arraylist ( DONE )
// TODO - Add rand comic generator for each rentee ( DONE )
// TODO - Check for whitespace for validation ( DONE )
// TODO - toUpperCase input for input match ( DONE )
// TODO - Implement JPanel for multiple textbox input for description editing for manageComics(), multiple buttons on JOptionPane ( Add comic, Modify comics , Delete comics ) ( DONE )
// TODO - Error validation for Editing comics ( DONE )
// FIXES NEEDED ------------------------------------
// JOptionPane Cancel button event handling - https://stackoverflow.com/questions/11494222/how-to-handle-cancel-button-in-joptionpane ( DONE )
// TODO [ URGENT ] - FIX Validation when all rentees are deleted , infinite error loop on Delete functions ( DONE )
// TODO - Fix Shutdown button after login when going back to login page , it redirects back to menu instead of shutting down. ( DONE )
// TODO - Fix Comic database multiplying upon open and edit of "Modifying comics" when there is 1 comic left ( DONE )
// TODO - Update values when newly editted info ( DONE )
// Align JOptionPane vertically, not horizontally ( FORGONE )
// if have time / brain energy lol
// TODO - make a separate class specifically for error handling ( FORGONE )
// TODO - Create / delete comics ; ( DONE )
// TODO - Make logout selection in main menu to switch between guest and admin accounts ( DONE )
// extra features ------------------------------------
// Whitespace validation
// Arraylist
// Admin Menu ( ON GOING )
// System Uptime 
// - used html to arrange text in admin menu
// Login page - sign in as administrator, or access system as a guest
// Many Error validation for different user experiences / actions that may cause errors
// Imports
import java.util.ArrayList;

// Login
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Box;

// ISBN formatter
import java.lang.StringBuilder;

public class RentalSystem {

    // Comic DB
    protected ArrayList<Comic> comicArr = new ArrayList<Comic>();

    // Rentee DB
    protected ArrayList<Rentee> renteeArr = new ArrayList<Rentee>();

    // For manage comics and manage Rentee
    protected ArrayList<String> pageList = new ArrayList<String>();
    protected ArrayList<String> pageContent = new ArrayList<String>();

    // Init admin system
    AdminSystem S1 = new AdminSystem();
    Rental_IO Database = new Rental_IO();

    // TODO - Array list ( DONE )
    public RentalSystem() {

        // Comics
        // TODO - Add in more random comics
        System.out.println("System Initializing.....\n");

        Database.StartIO("Input", null);
        renteeArr.addAll(Database.retrieveRenteeDB());
        comicArr.addAll(Database.retrieveComicDB());

        // get stored isbns of all rentees and assign them all with comics respective to their accounts
        // inner loop is current rentee's ammount of comics
        // in inner loop, do a search of comics in DB
        // .size() is an actual method in Rentee class, not java's built in size
        System.out.println("Size of Comic DB : " + comicArr.size());
        System.out.println("Size of Rentee DB : " + renteeArr.size());

        for (var i = 0; i < comicArr.size(); i++) {
            System.out.println(comicArr.get(i).getTitle());
        }

        for (var i = 0; i < renteeArr.size(); i++) {

            // add tmp array of comicList for 1 rentee
            ArrayList<Comic> tmpComicArr = new ArrayList<Comic>();
            tmpComicArr.clear();

            // get size of the array of rentee's comics
            for (var j = 0; j < renteeArr.get(i).getComicsList().size(); j++) {

                // whole db size
                for (var k = 0; k < comicArr.size(); k++) {

                    // search comics by ISBN in the DB
                    String currentQuery = comicArr.get(k).getISBN();

                    System.out.println(comicArr.get(k).getTitle());

                    if (currentQuery.equals(renteeArr.get(i).getComicISBN(j))) {
                        System.out.println(" Match with Rentee comic list!");
                        System.out.println("Current Comic : " + renteeArr.get(i).getComicISBN(j));

                        //get all info about the comic
                        tmpComicArr.add(comicArr.get(k));

                    }
                }
            }

            // store found comic arr into rentee
            renteeArr.get(i).storeComicList(tmpComicArr);

        }

//
//        // Assigning random comics to rentees ****************************
//        // Generate how many comics the rentee will have ; either 2 or more.
//        int min = 2;
//        int max = comicArr.size();
//
//        // TODO - Check for duplicate generated comic, declare "quantity" variable and "quantity" header title(joptionpane) in getMemberID().
//        // if duplicate generated comic exists in current rentee database, "quantity" variable will be updated to reflect on the number of duplicates.
//        // Storing all comics to rentees.
//        for (var i = 0; i < renteeArr.size(); i++) {
//
//            int randComicNum = (int) Math.floor(Math.random() * (max - min + 1) + min);
//
//            for (var j = 0; j < randComicNum; j++) {
//
//                // generate random comic to be stored in rentee
//                int rand = ((int) Math.floor(Math.random() * (max - min + 1) + min) - 1);
//
//                Comic generatedComic = comicArr.get(rand);
//
//                //checks if generated comic is already in the user's 
//                renteeArr.get(i).storeComicList(generatedComic);
//            }
//        }
        System.out.println("System loaded!!\nRunning...\n");
    }

    // Comic methods
    public String getComicCode(int index) {

        return comicArr.get(index).getDetails();

    }

    public String getComicStats(int index) {
        return comicArr.get(index).getStatisticDetails();
    }

    // no validation needed
    // TODO - Fix output ( FORGONE )
    // TODO - Page system?
    public void getAllComics() {

        // to store all comics
        String output = "";

        // header
        //output += ("ISBN-13                 |Title                                          |Pages|Price/Day |Deposit \n------------------------------------------------------------------------------------------------\n");
        output += ("ISBN-13|Title|Pages|Price/Day|Deposit\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        // output
        for (var i = 0; i < comicArr.size(); i++) {
            output += "\n" + (comicArr.get(i).getDetails());
        }
        JTextArea comicOutput = new JTextArea(output);

        // set the output to be uneditable so that useWr cannot backspace the output/
        comicOutput.setEditable(false);

        JOptionPane.showMessageDialog(null, comicOutput, "All Comics", JOptionPane.INFORMATION_MESSAGE);

        // logs
        System.out.println(output);
    }

    // TO DO - validation
    // add exception handling for empty string
    public void getComicISBN() {
        boolean doesExist = false;

        // 
        // user input
        String INPUT = JOptionPane.showInputDialog(null, "Enter ISBN-13 to search: ", "input", JOptionPane.QUESTION_MESSAGE);

        // clear whitespace in input
        if (INPUT != null) {
            INPUT = INPUT.trim();
        }
        // loop thru array
        try {
            System.out.println("<-- Searching Comic by ISBN-13 -->");
            System.out.println("Searching database...\n");
            for (var i = 0; i < comicArr.size(); i++) {

                if (comicArr.get(i).getISBN().equals(INPUT)) {
                    JOptionPane.showMessageDialog(null, comicArr.get(i).getStatisticDetails(), "Message", JOptionPane.INFORMATION_MESSAGE);
                    doesExist = true;

                    // logs output 
                    System.out.println("****ISBN-13 FOUND IN DATABASE!!****");
                    System.out.println(comicArr.get(i).getISBN());
                    System.out.println(comicArr.get(i).getStatisticDetails() + "\n");

                    break;
                }
            }
            if (INPUT == null) {
                System.out.println("Pressed cancel button, returning...");
            } // Checks for whitespace input
            else if (INPUT.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please input a comic ISBN-13.");
                throw new IllegalArgumentException("Invalid Input");
            } // if does not exist in database
            else if (doesExist == false) {
                JOptionPane.showMessageDialog(null, "Cannot find the comic " + "\"" + INPUT + "\"" + "!!");

                // return true to prevent invalid input message
                doesExist = true;
                throw new IllegalArgumentException("NO COMIC MATCHES INPUT: " + INPUT);

            }

            // Error handling
        } catch (Exception ex) {

//            // if wrong input
//            if (doesExist == false ) {
//                JOptionPane.showMessageDialog(null, "Please put a valid input.");
//
//            }
            //logs 
            System.out.println("***ERROR DETECTED***\n" + ex + "\n");
        }

    }

    // Rentee methods
    // TODO - Exception Handling ( DONE )
    // TODO - Error in searching name  - input does not match even though same ( DONE )
    public void getMemberID() {
        boolean doesExist = false;
        // user input
        String INPUT = JOptionPane.showInputDialog(null, "Enter MemberID to search: ", "input", JOptionPane.QUESTION_MESSAGE);
        // clear whitespaces

        if (INPUT != null) {
            if (INPUT.isBlank() == false) {
                INPUT = INPUT.trim();
                // set first letter to uppercase
                INPUT = INPUT.substring(0, 1).toUpperCase() + INPUT.substring(1).toLowerCase();
            }
        }
        try {

            System.out.println("Searching " + INPUT + ".....");

            // loop thru array
            System.out.println("<-- Searching Rentee by MemberID -->");
            System.out.println("Searching database...\n");
            for (var i = 0; i < renteeArr.size(); i++) {
                // getting names
                System.out.println(renteeArr.get(i).getID());

                if (renteeArr.get(i).getID().equals(INPUT)) {

                    // JOptionPane
                    JOptionPane.showMessageDialog(null, "MemberID                 | Name\n****************************************************************************\n"
                            + renteeArr.get(i).getID() + "                    "
                            + renteeArr.get(i).getName()
                            + "\n\nComics Loaned                                                                           "
                            + renteeArr.get(i).displayComics() + "\n", "Message", JOptionPane.INFORMATION_MESSAGE);
                    doesExist = true;

                    // logs output 
                    System.out.println("\n****MEMBERID FOUND IN DATABASE!!****\n\n"
                            + "| Name                       | MemberID");
                    System.out.println(renteeArr.get(i).getName() + "                | " + INPUT);
                    System.out.println(renteeArr.get(i).displayComics() + "\n");

                    break;
                }

            }

            // if cancel button pressed
            if (INPUT == null) {
                System.out.println("Cancel button pressed.. Exiting...");

            } // if whitespace
            else if (INPUT.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please input a MemberID.");
                throw new IllegalArgumentException("No MemberID Inputted");
            } // if does not exist in database
            else if (doesExist == false) {
                JOptionPane.showMessageDialog(null, "Cannot find the Member " + "\"" + INPUT + "\"" + "!!");

                // return true to prevent invalid input message
                doesExist = true;
                throw new IllegalArgumentException("NO MEMBERID MATCHES INPUT: " + INPUT);

            }

            // Error Handling
        } catch (Exception ex) {
            if (doesExist == false && INPUT == null) {
                JOptionPane.showMessageDialog(null, "Please input a MemberID.");
            }
            //logs 
            System.out.println("***ERROR DETECTED***\n" + ex + "\n");
        }

    }

    public String getEarningStatistic() {

        double renteeAvg = 0;

        for (var i = 0; i < (renteeArr.size()); i++) {
            for (var j = 0; j < renteeArr.get(i).returnComicSize(); j++) {
                renteeAvg += renteeArr.get(i).comicList.get(j).getRentalPerDay();
            }
        }
        System.out.println("EarnStat");
        renteeAvg = (renteeAvg / renteeArr.size());
        String output = ("Earning Per Day:\n"
                + "--------------------------------------\n\n"
                + "There are " + renteeArr.size() + " Rentees in total.\n\n"
                + "Average earning per day based on numbers of rentees is $" + String.format("%.2f", renteeAvg)
                + "\n\nTotal earning per day is $" + String.format("%.2f", (renteeAvg * renteeArr.size())));
        return output;

    }

    public void StartProgram() {

        //AdminSystem S1 = new AdminSystem();
        boolean user = S1.verifyLogin();

        // choose menu
        int menuChoice = 0;

        // close the program
        boolean hasShutDown = false;

        do {

            do {

                try {

                    String menu = ("Enter your option:\n\n1. Display All comics\n2. Search Comic by ISBN-13\n3. Search Rentee by MemberID\n4. Print Earning Statistic\n5. Exit\n\n6. Log out");

                    // Adds extra menu option when logged in as admin
                    if (user == true) {
                        menu += "\n7. Admin Menu\n";
                    }

                    // user prompt option
                    String input = JOptionPane.showInputDialog(null, menu, "Comic Rental System", JOptionPane.QUESTION_MESSAGE);

                    // parse user choice to str
                    menuChoice = Integer.parseInt(input);

                    // If menu input is not the 1-5 choices
                    if (menuChoice < 1 || menuChoice > 7) {
                        // will be caught under catch
                        throw new IllegalArgumentException("For input: " + menuChoice);
                    }

                    // Main Menu -------------------------------------------------------
                    switch (menuChoice) {
                        case 1:
                            getAllComics();
                            break;
                        case 2:
                            getComicISBN();
                            break;
                        case 3:
                            getMemberID();
                            break;
                        case 4:
                            getEarningStatistic();
                            break;
                        case 5:
                            JOptionPane.showMessageDialog(null, "Thank you for using Comic Rental.\nWe look forward to serve you in the near future.");
                            System.out.println("\n*******Thank you for using Comic Rental.*******\nWe look forward to serve you in the near future.\n\nShutting down...");

                            // to close program
                            hasShutDown = true;
                            break;

                        // Administrator page
                        case 6:
                            JOptionPane.showMessageDialog(null, "Logging out..", "Log out", JOptionPane.INFORMATION_MESSAGE);

                            user = S1.verifyLogin();

                            break;
                        case 7:
                            if (user == true) {
                                adminMenu();
                            } // if not administrator
                            else {
                                throw new IllegalArgumentException("You do not have the permission to access this option. [ Not an Administrator ]");
                            }

                    }

                } // Exception Handling
                catch (Exception ex) {

                    // if logout is selected, user will either shut down or log in again, thus declare shutdown as true.
                    if (menuChoice == 6) {
                        hasShutDown = true;
                    } else {

                        JOptionPane.showMessageDialog(null, "Invalid option! Please enter in the range from 1 to 5.", "Error", JOptionPane.ERROR_MESSAGE);

                        //logs 
                        System.out.println("**ERROR DETECTED**\n" + ex + "\n");
                    }
                }

                // exit when menuchoice == 5 ( Exit ) or 6 ( Log out ) 
                // 
            } while (menuChoice != 5 && hasShutDown != true || menuChoice != 6 && hasShutDown != true);

            // if menuChoice == 5, breaks off loop, otherwise if menuChoice == 6 , goes back to top to log in again.
        } while (hasShutDown == false);

    }

    // please fix lol ( DONE ) - probably will need to move this to rentalmenu class instead to cater the log out
    public String systemUptime(long duration) {
        int seconds, minutes;

        minutes = (int) (duration / 60);

        seconds = (int) (duration - (minutes * 60));

        return ("System has been up for " + minutes + " minutes , " + seconds + " seconds.");

    }

    // Extra features
    public void adminMenu() {

        //logs
        System.out.println("\nLaunching Admin Menu...");

        String menu = "<html><center>Welcome to SP Comic Rental Admin Menu.<br><br>*********************************************************************<br>1. Manage Rentees<br>Add,Delete or Modify Rentee details.<br><br>2. Manage Comics<br>Add,Delete or Modify Comics in the database.<br><br>3. Manage Administrator accounts<br>Add,Delete or Modify existing Administrator accounts in the system.<br>*********************************************************************</center></html>";

        int adminChoice = 0;

        String[] list = new String[]{"Manage Rentees", "Manage Comics", "Manage Administrators", "Exit"};

        do {
            try {
                adminChoice = JOptionPane.showOptionDialog(null, menu, "Admin Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, list, list[0]);

                // Admin menu
                switch (adminChoice) {
                    case 0:
                        System.out.println("Managing Rentees..");
                        manageRentees();
                        break;
                    case 1:
                        System.out.println("Managing Comics...");
                        manageComics();
                        break;
                    case 2:
                        System.out.println("Managing Administrators..");
                        S1.manageAdmin();
                        break;
                    case 3:
                        System.out.println("Exiting Admin menu..\n");
                        break;
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } while (adminChoice != 3);
        //S1.adminMenu();

    }

    public void manageComics() {

        //logs
        System.out.println("\nLaunching Comics Menu...");

        String menu = "<html><center>Manage Comics<br>"
                + "***************************************<br><br>"
                + "1. Add Comics<br>Add a comic into SP's Library database<br><br>"
                + "2. Delete Comics<br>Delete a comic from SP's Library database<br><br>"
                + "3. Modify Comics<br>Modify information of a comic from SP's Library database<br><br>"
                + "***************************************</center></html>";

        int comicsChoice = 0;

        String[] list = new String[]{"Add Comics", "Delete Comics", "Modify Comics", "Exit"};

        do {
            try {
                comicsChoice = JOptionPane.showOptionDialog(null, menu, "Admin Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, list, list[0]);

                // Admin menu
                switch (comicsChoice) {
                    case 0:
                        System.out.println("Adding Comics..");
                        addComics();
                        break;
                    case 1:
                        if (comicArr.size() > 0) {
                            System.out.println("Deleting Comics...\n");
                            deleteComics();
                        } else {
                            JOptionPane.showMessageDialog(null, "Comic database is empty! Please add in comics to the database.");
                            throw new Exception("Comic database is empty!");
                        }
                        break;
                    case 2:
                        if (comicArr.size() > 0) {
                            System.out.println("Modifying Comics..");
                            editComics();
                        } else {
                            JOptionPane.showMessageDialog(null, "Comic database is empty! Please add in comics to the database.");
                            throw new Exception("Comic database is empty!");
                        }
                        break;
                    case 3:
                        System.out.println("Exiting Admin menu..\n");
                        break;
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } while (comicsChoice != 3);
        //S1.adminMenu();
    }

    // Comic admin menu methods
    public void editComics() {
        // comic books per page
        int min = 1;
        int max = 15;

        // current page
        int currentPage = 0;

        int perPage = 2;

        String pushedNum;

        // start pos
        int l = 0;

        // size
        int size = comicArr.size() / perPage;

        // stores 15 numbers in 1 page, depending on the max
        // then push page content to 1st page of list
        // actual formula will be comicArr size divided by 15 , result will be the loop repeat plus 1 because we want the remaining comiics to be stored still
        // clear previous entries
        pageList.clear();
        pageContent.clear();

        for (var j = 0; j < size; j++) {
            for (var i = 0; i < perPage; i++) {

                // instantiate to null to empty arr
                pushedNum = null;

                // parse tostr
                // Comic list
                pushedNum = (l + 1 + ". " + comicArr.get(l).getTitle());

                System.out.println((l + 1) + ". " + comicArr.get(l).getTitle() + "^");
                // move up the array
                l += 1;

                pageContent.add(pushedNum);

            }
            // push 1st page to the list
            pageList.add(pageContent.toString());

            // clear page content for next loop
            pageContent.clear();

            //System.out.println(pageList.toString());
        }
        System.out.println("\nDB Size : " + comicArr.size());
        System.out.println("Pagelist size: " + pageList.size() * perPage);

        // get remaining length of remaining numbers
        int fixedSize = (comicArr.size() - (pageList.size() * perPage));

        System.out.println("Numbers not pushed yet : " + fixedSize);

        // push the rest of the numbers outside the array as one array if perPage is inequal to size of comicArr
        if (comicArr.size() % perPage != 0) {
            for (var i = fixedSize - 1; i >= 0; i--) {

                // clear out previous entry
                // numbers left out
                pageContent.add(l + 1 + ". " + comicArr.get(l).getTitle());

            }
            // push in last array
            pageList.add(pageContent.toString());
        }
        // final array 
        //System.out.println(pageList.toString());

        // to scroll through content
        int choice = 0;

        // optionpane section
        JTextField selectNumber = new JTextField(5);
        JPanel myPanel = new JPanel();

        myPanel.add(selectNumber);

        String[] buttons = {"Next", "Previous", "Exit", "Select"};

        // initial page
        int i = 0;
        // store numbers in label
        JLabel result = new JLabel();

        do {
            try {
                // i value handling
                if (i < 0) {
                    i = 0;
                } else if (i > pageList.size() - 1) {
                    i = pageList.size() - 1;
                }

                // change text
                result.setText(pageList.get(i));

                myPanel.add(result);

                choice = JOptionPane.showOptionDialog(null, myPanel, "Select a comic", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);

                switch (choice) {
                    case 0:
                        i++;
                        break;
                    case 1:
                        i--;
                        break;

                    case 2:

                        break;
                    case 3:
                        int selectedComic = Integer.parseInt(selectNumber.getText()) - 1;

                        JOptionPane.showMessageDialog(null, "Selected Comic : " + comicArr.get(selectedComic).getTitle(), "Selected Comic", JOptionPane.INFORMATION_MESSAGE);
                        onEditComics(selectedComic);
                }

                // if input is bigger than comic db
            } catch (IndexOutOfBoundsException ex) {

                JOptionPane.showMessageDialog(null, "Selected comic does not exist in the database.", "Error", JOptionPane.ERROR_MESSAGE);

                System.out.println(ex);

                // if input string
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please input the number of the comic according to the list..", "Error", JOptionPane.ERROR_MESSAGE);

                System.out.println(ex);
            } // other errors
            catch (Exception ex) {
                System.out.println(ex);
            }

        } while (choice != 2);

    }

    public void onEditComics(int index) {
        JTextField setISBN = new JTextField(5);
        JTextField setTitle = new JTextField(5);
        JTextField setCost = new JTextField(5);

        JPanel myPanel = new JPanel();

        // get details
        myPanel.add(new JLabel(comicArr.get(index).getDetailsHTML()));

        myPanel.add(new JLabel("Set ISBN :"));
        myPanel.add(setISBN);
        // put current isbn
        setISBN.setText(comicArr.get(index).getISBN());

        myPanel.add(new JLabel("Set Title :"));
        myPanel.add(setTitle);
        // put current title
        setTitle.setText(comicArr.get(index).getTitle());

        myPanel.add(new JLabel("Set Cost :"));
        myPanel.add(setCost);
        // put current isbn
        setCost.setText(Double.toString(comicArr.get(index).getCost()));

        int result = 0;
        String[] buttons = {"Edit Comic", "Exit"};

        do {
            try {
                result = JOptionPane.showOptionDialog(null, myPanel, "Create Comics", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, buttons, buttons[0]);

                switch (result) {
                    case 0:

                        /// used double as any number longer than 9 characters will throw a numberformatexception when declared as int.
                        String PARSED_isbn = setISBN.getText();

                        // set new isbn
                        comicArr.get(index).setISBN(PARSED_isbn);
                        System.out.println("first");

                        double PARSED_cost = Double.parseDouble(setCost.getText());
                        // set new cost
                        comicArr.get(index).setCost(PARSED_cost);

                        // set new title
                        comicArr.get(index).setTitle(setTitle.getText());

                        JOptionPane.showMessageDialog(null, "Successfully edit comic with information.");
                    case 1:
                        break;

                }

            } catch (Exception ex) {

                System.out.println(ex);
            }
        } while (result != 1);

    }

    public boolean addComics(String ISBN, String Title, String pageVal, String cost) {
        //init variables
        int PARSED_pageVal;
        String PARSED_ISBN;
        double PARSED_comicCost;
        
//        System.out.println("ISBN - 13 Code : " + isbnCode.getText());
//                    System.out.println("Comic Title : " + comicTitle.getText());
//                    System.out.println("Total Page numbers : " + pageVal.getText());
//                    System.out.println("Comic Cost :" + comicCost.getText());

        
        
        //validate exit
        boolean hasAdded = false;
     
            try {

                    //logs output for created account
                    System.out.println("<--Create Comic-->");

                    System.out.println("ISBN - 13 Code : " + ISBN);
                    System.out.println("Comic Title : " + Title);
                    System.out.println("Total Page numbers : " + pageVal);
                    System.out.println("Comic Cost :" + cost);

                    // check if ISBN / comic title already exists in the database.
                    boolean alreadyExists = false;
                    for (var i = 0; i < comicArr.size(); i++) {

                        if (formatISBN(ISBN).equals(comicArr.get(i).getISBN())) {

                            // isbn already exists
                            alreadyExists = true;
                            JOptionPane.showMessageDialog(null, "ISBN - 13 Code already exists in the database! Please input a different ISBN - 13 Code.");
                            throw new Exception("***ERROR***\nISBN - 13 Already exists in the database!");
                        }

                        if (Title.equals(comicArr.get(i).getTitle())) {

                            // title already exists
                            alreadyExists = true;

                            JOptionPane.showMessageDialog(null, "Comic Title already exists in the database! Please input a different Comic Title.");
                            throw new Exception("***ERROR***\nTitle already exists in the database!");

                        }
                    }

                    // if isbn does not exist yet
                    if (alreadyExists == false) {

                        // parsing of pageVal and comic Cost
                        // validate if they are 
                        try {

                            PARSED_pageVal = Integer.parseInt(pageVal);
                            PARSED_comicCost = Double.parseDouble(cost);
                            PARSED_ISBN = ISBN ;

                            // format the ISBN
                            String data = formatISBN(PARSED_ISBN);

                            // Creation of new comic
                            comicArr.add((new Comic(data, Title, PARSED_pageVal, PARSED_comicCost)));

                            JOptionPane.showMessageDialog(null, "Comic " + Title + " with ISBN-13 " + ISBN + " has been created.");
                            hasAdded = true;
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "***ERROR***\nPlease input valid values.");
                        }

                    }

                

            } catch (Exception ex) {
                System.out.println(ex);
            }
    
            return hasAdded;
    }
    
    public boolean addManga(String ISBN, String Title, String pageVal, String cost,String Additional_INF) {
        //init variables
        int PARSED_pageVal;
        String PARSED_ISBN;
        double PARSED_comicCost;
        
//        System.out.println("ISBN - 13 Code : " + isbnCode.getText());
//                    System.out.println("Comic Title : " + comicTitle.getText());
//                    System.out.println("Total Page numbers : " + pageVal.getText());
//                    System.out.println("Comic Cost :" + comicCost.getText());

        
        
        //validate exit
        boolean hasAdded = false;
     
            try {

                    //logs output for created account
                    System.out.println("<--Create Comic-->");

                    System.out.println("ISBN - 13 Code : " + ISBN);
                    System.out.println("Comic Title : " + Title);
                    System.out.println("Total Page numbers : " + pageVal);
                    System.out.println("Comic Cost :" + cost);

                    // check if ISBN / comic title already exists in the database.
                    boolean alreadyExists = false;
                    for (var i = 0; i < comicArr.size(); i++) {

                        if (formatISBN(ISBN).equals(comicArr.get(i).getISBN())) {

                            // isbn already exists
                            alreadyExists = true;
                            JOptionPane.showMessageDialog(null, "ISBN - 13 Code already exists in the database! Please input a different ISBN - 13 Code.");
                            throw new Exception("***ERROR***\nISBN - 13 Already exists in the database!");
                        }

                        if (Title.equals(comicArr.get(i).getTitle())) {

                            // title already exists
                            alreadyExists = true;

                            JOptionPane.showMessageDialog(null, "Comic Title already exists in the database! Please input a different Comic Title.");
                            throw new Exception("***ERROR***\nTitle already exists in the database!");

                        }
                    }

                    // if isbn does not exist yet
                    if (alreadyExists == false) {

                        // parsing of pageVal and comic Cost
                        // validate if they are 
                        try {

                            PARSED_pageVal = Integer.parseInt(pageVal);
                            PARSED_comicCost = Double.parseDouble(cost);
                            PARSED_ISBN = ISBN ;

                            // format the ISBN
                            String data = formatISBN(PARSED_ISBN);

                            // Creation of new comic
                            comicArr.add((new Manga(data, Title, PARSED_pageVal, PARSED_comicCost,Additional_INF)));

                            JOptionPane.showMessageDialog(null, "Comic " + Title + " with ISBN-13 " + ISBN + " has been created.");
                            hasAdded = true;
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "***ERROR***\nPlease input valid values.");
                        }

                    }

                

            } catch (Exception ex) {
                System.out.println(ex);
            }
    
            return hasAdded;
    }

    // TODO [ URGENT ] - FIX Validation when all entries are deleted , infinite error loop on Delete functions
    public void deleteComics() {
        if (comicArr.size() > 0) {
            // to scroll through content
            int choice = 0;
            // initial page
            int i = 0;
            do {
                if (comicArr.size() > 0) {
                    int perPage = 2;

                    String pushedNum;

                    // start pos
                    int l = 0;

                    // size
                    int size = comicArr.size() / perPage;

                    // stores 15 numbers in 1 page, depending on the max
                    // then push page content to 1st page of list
                    // actual formula will be comicArr size divided by 15 , result will be the loop repeat plus 1 because we want the remaining comiics to be stored still
                    // clear previous entries
                    pageList.clear();
                    pageContent.clear();

                    for (var j = 0; j < size; j++) {
                        for (var k = 0; k < perPage; k++) {

                            // instantiate to null to empty arr
                            pushedNum = null;

                            // parse tostr
                            // Comic list
                            pushedNum = (l + 1 + ". " + comicArr.get(l).getTitle());

                            System.out.println((l + 1) + ". " + comicArr.get(l).getTitle() + "^");
                            // move up the array
                            l += 1;

                            pageContent.add(pushedNum);

                        }
                        // push 1st page to the list
                        pageList.add(pageContent.toString());

                        // clear page content for next loop
                        pageContent.clear();

                    }
                    System.out.println("\nDB Size : " + comicArr.size());
                    System.out.println("Pagelist size: " + pageList.size() * perPage);

                    // get remaining length of remaining numbers
                    int fixedSize = (comicArr.size() - (pageList.size() * perPage));

                    System.out.println("Numbers not pushed yet : " + fixedSize + "\n");

                    // push the rest of the numbers outside the array as one array if perPage is inequal to size of comicArr
                    if (comicArr.size() % perPage != 0) {
                        for (var y = fixedSize - 1; y >= 0; y--) {

                            // clear out previous entry
                            // numbers left out
                            pageContent.add(l + 1 + ". " + comicArr.get(l).getTitle());

                        }
                        // push in last array
                        pageList.add(pageContent.toString());
                    }
                    // final array 
                    //System.out.println(pageList.toString());

//        // content that was not pushed !
//        System.out.println(comicArr[lastNum]);
//        // push last num in array!
//        pageList.add(Integer.toString(comicArr[lastNum]));
//        
//        // final array
//        System.out.println(pageList.toString());
//
                    // to scroll through content
                    //int choice = 0;
                    // optionpane section
                    JTextField selectNumber = new JTextField(5);
                    JPanel myPanel = new JPanel();

                    myPanel.add(selectNumber);

                    String[] buttons = {"Next", "Previous", "Exit", "Select"};

                    // store numbers in label
                    JLabel result = new JLabel();

                    //do {
                    try {
                        // i value handling
                        if (i < 0) {
                            i = 0;
                        } else if (i > pageList.size() - 1) {
                            i = pageList.size() - 1;
                        }

                        // change text
                        result.setText(pageList.get(i));

                        myPanel.add(result);

                        choice = JOptionPane.showOptionDialog(null, myPanel, "Select a comic", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);

                        switch (choice) {
                            case 0:
                                i++;
                                break;
                            case 1:
                                i--;
                                break;

                            case 2:
                                break;
                            case 3:
                                int selectedComic = Integer.parseInt(selectNumber.getText()) - 1;
                                JOptionPane.showMessageDialog(null, "Selected Comic : " + comicArr.get(selectedComic).getTitle(), "Selected Comic", JOptionPane.INFORMATION_MESSAGE);
                                onDeleteComics(selectedComic);
                        }
                        // if input is bigger than comic db
                    } catch (IndexOutOfBoundsException ex) {

                        JOptionPane.showMessageDialog(null, "Selected comic does not exist in the database.", "Error", JOptionPane.ERROR_MESSAGE);

                        System.out.println(ex);

                        // if input string
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please input the number of the comic according to the list..", "Error", JOptionPane.ERROR_MESSAGE);

                        System.out.println(ex);
                    } // other errors
                    catch (Exception ex) {
                        System.out.println(ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Comic database is empty! Please add in comics to the database.");
                    throw new IllegalArgumentException("Comic database is empty!");
                }
            } while (choice != 2);
        } else {
            JOptionPane.showMessageDialog(null, "Comic database is empty. Please add in comics to the database.", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Comic database is empty!");
        }
    }

    public void onDeleteComics(int index) {
        if (comicArr.size() > 0) {
            int result = 0;
            boolean hasDeleted = false;
            String[] buttons = {"Yes", "No"};

            try {
                result = JOptionPane.showOptionDialog(null, "Delete comic " + comicArr.get(index).getTitle() + " ?", "Create Comics", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, buttons, buttons[0]);

                switch (result) {
                    case 0:
                        JOptionPane.showMessageDialog(null, "Successfully deleted Comic " + comicArr.get(index).getTitle() + ".");
                        comicArr.remove(index);
                        hasDeleted = true;
                        break;
                    case 1:
                        break;

                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Comic database is empty! Please add in comics to the database.");
            throw new IllegalArgumentException("Comic database is empty!");
        }

    }

    // Rentees admin menu methods
    public void manageRentees() {

        //logs
        System.out.println("\nLaunching Rentee Menu...");

        String menu = "<html><center>Manage Rentees<br>"
                + "***************************************<br><br>"
                + "1. Add Rentee<br>Add a Rentee into SP's Library database<br><br>"
                + "2. Delete Comics<br>Delete a Rentee from SP's Library database<br><br>"
                + "3. Modify Comics<br>Modify information of a Rentee from SP's Library database<br><br>"
                + "***************************************</center></html>";

        int comicsChoice = 0;

        String[] list = new String[]{"Add Rentee", "Delete Rentee", "Modify Rentee", "Exit"};

        do {
            try {
                comicsChoice = JOptionPane.showOptionDialog(null, menu, "Admin Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, list, list[0]);

                // Admin menu
                switch (comicsChoice) {
                    case 0:
                        System.out.println("Adding Rentee..");
                        //addRentee();
                        break;
                    case 1:
                        if (renteeArr.size() > 0) {
                            System.out.println("Deleting Rentee...");
                            deleteRentee();
                        } else {
                            JOptionPane.showMessageDialog(null, "Rentee database is empty! Please add in rentees to the database.");
                            throw new IllegalArgumentException("Rentee database is empty!");
                        }
                        break;
                    case 2:
                        if (renteeArr.size() > 0) {
                            System.out.println("Modifying Rentee..");
                            editRentee();
                        } else {
                            JOptionPane.showMessageDialog(null, "Rentee database is empty! Please add in rentees to the database.");
                            throw new IllegalArgumentException("Rentee database is empty!");
                        }
                        break;
                    case 3:
                        System.out.println("Exiting Admin menu..\n");
                        break;
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } while (comicsChoice != 3);
        //S1.adminMenu();
    }

    // boolean to return a true / false value if member has actually been added
    public boolean addRentee(String MEMBER_ID, String NAME) {
        //init variables
        String PARSED_memberID = MEMBER_ID;
        String PARSED_name = NAME;

//        JTextField memberID = new JTextField(5);
//        JTextField name = new JTextField(5);
//
//        // Creating custom panel
//        JPanel myPanel = new JPanel();
//
//        //ISBN 13
//        myPanel.add(new JLabel("<html>Please enter the values.<br>For Member ID, please enter numbers without the \"M\".</html>"));
//        myPanel.add(Box.createHorizontalStrut(30)); // a spacer
//
//        myPanel.add(new JLabel("memberID :"));
//        myPanel.add(memberID);
//
//        //Create title
//        myPanel.add(new JLabel("name :"));
//        myPanel.add(name);
        //validate exit
        boolean hasAdded = false;

        // Button options
        int result = 0;

        try {
            //result = JOptionPane.showOptionDialog(null, myPanel, "Create Rentee", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);

            // If create administrator button pressed
            if (result == 0) {

                if (PARSED_memberID.isBlank() || PARSED_name.isBlank()) {
                    JOptionPane.showMessageDialog(null, "You have missing fields. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    throw new Exception("Missing fields");
                } else {
                    //logs output for created account
                    System.out.println("<--Create Rentee-->");

                    System.out.println("Member ID: " + setID(PARSED_memberID));
                    System.out.println("Name : " + PARSED_name);

                    // check if ISBN / comic title already exists in the database.
                    boolean alreadyExists = false;
                    String compareStr = setID(PARSED_memberID);

                    for (var i = 0; i < renteeArr.size(); i++) {

                        // if member ID exists
                        if (compareStr.equals(renteeArr.get(i).getID())) {

                            // isbn already exists
                            alreadyExists = true;
                            JOptionPane.showMessageDialog(null, "Member ID already exists in the database! Please input a different Member ID.");
                            throw new Exception("***ERROR***\nMember ID Already exists in the database!");
                        }

                        // if name exists
                        if (PARSED_name.equals(renteeArr.get(i).getName())) {

                            // title already exists
                            alreadyExists = true;

                            JOptionPane.showMessageDialog(null, "Name already exists in the database! Please input a different name.");
                            throw new Exception("***ERROR***\nName already exists in the database!");

                        }
                    }

                    // if memberid does not exist yet
                    if (alreadyExists == false) {

                        // parsing of pageVal and comic Cost
                        // validate if they are 
                        try {
                            // Creation of new comic

                            // if values are all entered
                            if (PARSED_memberID.isBlank() == false && PARSED_name.isBlank() == false) {
                                renteeArr.add((new Rentee(compareStr, PARSED_name, null)));

                                JOptionPane.showMessageDialog(null, "Rentee " + PARSED_name + " with MemberID " + compareStr + " has been created.");
                                hasAdded = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Empty Fields. Please Try again", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "***ERROR***\nPlease input valid values.");
                        }

                    }

                } // exits
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        return hasAdded;
    }

    // TODO [ URGENT ] - FIX Validation when all entries are deleted , infinite error loop on Delete functions
    public void deleteRentee() {
        if (renteeArr.size() > 0) {
            // to scroll through content
            int choice = 0;
            // initial page
            int i = 0;

            do {
                if (renteeArr.size() > 0) {
                    int perPage = 2;

                    String pushedNum;

                    // start pos
                    int l = 0;

                    // size
                    int size = renteeArr.size() / perPage;

                    // stores 15 numbers in 1 page, depending on the max
                    // then push page content to 1st page of list
                    // actual formula will be comicArr size divided by 15 , result will be the loop repeat plus 1 because we want the remaining comiics to be stored still
                    // clear previous entries
                    pageList.clear();
                    pageContent.clear();

                    for (var j = 0; j < size; j++) {
                        for (var k = 0; k < perPage; k++) {

                            // instantiate to null to empty arr
                            pushedNum = null;

                            // parse tostr
                            // Comic list
                            pushedNum = (l + 1 + ". " + renteeArr.get(l).getName());

                            System.out.println((l + 1) + ". " + renteeArr.get(l).getName() + "^");
                            // move up the array
                            l += 1;

                            pageContent.add(pushedNum);

                        }
                        // push 1st page to the list
                        pageList.add(pageContent.toString());

                        // clear page content for next loop
                        pageContent.clear();

                        //System.out.println(pageList.toString());
                    }
                    System.out.println("DB Size : " + renteeArr.size());
                    System.out.println("Pagelist size: " + pageList.size() * perPage);

                    // get remaining length of remaining numbers
                    int fixedSize = (renteeArr.size() - (pageList.size() * perPage));

                    System.out.println("Numbers not pushed yet : " + fixedSize);

                    // push the rest of the numbers outside the array as one array if perPage is inequal to size of comicArr
                    if (renteeArr.size() % perPage != 0) {
                        for (var y = fixedSize - 1; y >= 0; y--) {

                            // clear out previous entry
                            // numbers left out
                            pageContent.add(l + 1 + ". " + renteeArr.get(l).getName());

                        }
                        // push in last array
                        pageList.add(pageContent.toString());
                    }
                    // final array 
                    //System.out.println(pageList.toString());

//        // content that was not pushed !
//        System.out.println(comicArr[lastNum]);
//        // push last num in array!
//        pageList.add(Integer.toString(comicArr[lastNum]));
//        
//        // final array
//        System.out.println(pageList.toString());
//
                    // to scroll through content
                    //int choice = 0;
                    // optionpane section
                    JTextField selectNumber = new JTextField(5);
                    JPanel myPanel = new JPanel();

                    myPanel.add(selectNumber);

                    String[] buttons = {"Next", "Previous", "Exit", "Select"};

                    // store numbers in label
                    JLabel result = new JLabel();

                    //do {
                    try {
                        // i value handling
                        if (i < 0) {
                            i = 0;
                        } else if (i > pageList.size() - 1) {
                            i = pageList.size() - 1;
                        }

                        // change text
                        result.setText(pageList.get(i));

                        myPanel.add(result);

                        choice = JOptionPane.showOptionDialog(null, myPanel, "Select a Rentee", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);

                        switch (choice) {
                            case 0:
                                i++;
                                break;
                            case 1:
                                i--;
                                break;

                            case 2:
                                break;
                            //throw new Exception("Pressed Exit button..");
                            case 3:
                                int selectedRentee = Integer.parseInt(selectNumber.getText()) - 1;
                                JOptionPane.showMessageDialog(null, "Selected Rentee : " + renteeArr.get(selectedRentee).getName(), "Selected Comic", JOptionPane.INFORMATION_MESSAGE);
                                onDeleteRentee(selectedRentee);
                                break;
                        }
                        // if input is bigger than comic db
                    } catch (IndexOutOfBoundsException ex) {

                        JOptionPane.showMessageDialog(null, "Selected rentee does not exist in the database.", "Error", JOptionPane.ERROR_MESSAGE);

                        System.out.println(ex);

                        // if input string
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please input the number of the Rentee according to the list..", "Error", JOptionPane.ERROR_MESSAGE);

                        System.out.println(ex);
                    } // other errors
                    catch (Exception ex) {
                        System.out.println(ex);
                    }
                } else {

                    JOptionPane.showMessageDialog(null, "Rentee database is empty! Please add in rentees to the database.");
                    throw new IllegalArgumentException("Rentee database is empty!");
                }

            } while (choice != 2);
        }
    }

    public void editRentee() {
        // to scroll through content
        int choice = 0;
        // initial page
        int i = 0;
        do {
            int perPage = 3;

            String pushedNum;

            // start pos
            int l = 0;

            // size
            int size = renteeArr.size() / perPage;

            // stores 15 numbers in 1 page, depending on the max
            // then push page content to 1st page of list
            // actual formula will be comicArr size divided by 15 , result will be the loop repeat plus 1 because we want the remaining comiics to be stored still
            // clear previous entries
            pageList.clear();
            pageContent.clear();

            for (var j = 0; j < size; j++) {
                for (var k = 0; k < perPage; k++) {

                    // instantiate to null to empty arr
                    pushedNum = null;

                    // parse tostr
                    // Comic list
                    pushedNum = (l + 1 + ". " + renteeArr.get(l).getName());

                    System.out.println((l + 1) + ". " + renteeArr.get(l).getName() + "^");
                    // move up the array
                    l += 1;

                    pageContent.add(pushedNum);

                }
                // push 1st page to the list
                pageList.add(pageContent.toString());

                // clear page content for next loop
                pageContent.clear();

                //System.out.println(pageList.toString());
            }
            System.out.println("DB Size : " + renteeArr.size());
            System.out.println("Pagelist size: " + pageList.size() * perPage);

            // get remaining length of remaining numbers
            int fixedSize = (renteeArr.size() - (pageList.size() * perPage));

            System.out.println("Numbers not pushed yet : " + fixedSize);

            // push the rest of the numbers outside the array as one array if perPage is inequal to size of comicArr
            if (renteeArr.size() % perPage != 0) {
                for (var y = fixedSize - 1; y >= 0; y--) {

                    // clear out previous entry
                    // numbers left out
                    pageContent.add(l + 1 + ". " + renteeArr.get(l).getName());

                }
                // push in last array
                pageList.add(pageContent.toString());
            }
            // final array 
            //System.out.println(pageList.toString());

//        // content that was not pushed !
//        System.out.println(comicArr[lastNum]);
//        // push last num in array!
//        pageList.add(Integer.toString(comicArr[lastNum]));
//        
//        // final array
//        System.out.println(pageList.toString());
//
            // to scroll through content
            //int choice = 0;
            // optionpane section
            JTextField selectNumber = new JTextField(5);
            JPanel myPanel = new JPanel();

            myPanel.add(selectNumber);

            String[] buttons = {"Next", "Previous", "Exit", "Select"};

            // store numbers in label
            JLabel result = new JLabel();

            //do {
            try {
                // i value handling
                if (i < 0) {
                    i = 0;
                } else if (i > pageList.size() - 1) {
                    i = pageList.size() - 1;
                }

                // change text
                result.setText(pageList.get(i));

                myPanel.add(result);

                choice = JOptionPane.showOptionDialog(null, myPanel, "Select a Rentee", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);

                switch (choice) {
                    case 0:
                        i++;
                        break;
                    case 1:
                        i--;
                        break;

                    case 2:
                        break;
                    case 3:
                        int selectedRentee = Integer.parseInt(selectNumber.getText()) - 1;
                        JOptionPane.showMessageDialog(null, "Selected Rentee : " + renteeArr.get(selectedRentee).getName(), "Selected Comic", JOptionPane.INFORMATION_MESSAGE);
                        onEditRentee(selectedRentee);
                }
                // if input is bigger than comic db
            } catch (IndexOutOfBoundsException ex) {

                JOptionPane.showMessageDialog(null, "Selected rentee does not exist in the database.", "Error", JOptionPane.ERROR_MESSAGE);

                System.out.println(ex);

                // if input string
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please input the number of the Rntee according to the list..", "Error", JOptionPane.ERROR_MESSAGE);

                System.out.println(ex);
            } // other errors
            catch (Exception ex) {
                System.out.println(ex);
            }
        } while (choice != 2);
    }

    public void onEditRentee(int index) {

        //logs
        System.out.println("\nLaunching Rentee Menu...");

        String menu = "<html><center>Edit rentee : " + renteeArr.get(index).getName() + "<br>"
                + "***************************************<br><br>"
                + "1. Add comics<br>Add Newly rented comics to Rentee<br><br>"
                + "2. Delete Comics<br>Delete Fully paid comics from rentee<br><br>"
                + "3. Edit Information<br>Edit information of a Rentee from SP's Library database<br><br>"
                + "***************************************</center></html>";

        int comicsChoice = 0;

        String[] list = new String[]{"Add comics", "Delete Fully paid comics", "Edit Rentee Information", "Exit"};

        do {
            try {
                comicsChoice = JOptionPane.showOptionDialog(null, menu, "Rentee Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, list, list[0]);

                // Admin menu
                switch (comicsChoice) {
                    case 0:
                        System.out.println("Adding comics into rentee...");
                        addRenteeComics(index);
                        break;
                    case 1:

                        System.out.println("Deleting comics from rentee...");
                        deleteRenteeComics(index);
                        break;
                    case 2:
                        System.out.println("Modifying Rentee..");
                        editRenteeInformation(index);
                        break;
                    case 3:
                        System.out.println("Exiting Rentee menu..\n");
                        break;
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } while (comicsChoice != 3);
        //S1.adminMenu();

    }

    public void addRenteeComics(int index) {
        // to scroll through content
        int choice = 0;
        // initial page
        int i = 0;
        do {
            int perPage = 2;

            String pushedNum;

            // start pos
            int l = 0;

            // size
            int size = comicArr.size() / perPage;

            // stores 15 numbers in 1 page, depending on the max
            // then push page content to 1st page of list
            // actual formula will be comicArr size divided by 15 , result will be the loop repeat plus 1 because we want the remaining comiics to be stored still
            // clear previous entries
            pageList.clear();
            pageContent.clear();

            for (var j = 0; j < size; j++) {
                for (var k = 0; k < perPage; k++) {

                    // instantiate to null to empty arr
                    pushedNum = null;

                    // parse tostr
                    // Comic list
                    pushedNum = (l + 1 + ". " + comicArr.get(l).getTitle());

                    System.out.println(comicArr.get(l).getTitle() + "^");
                    // move up the array
                    l += 1;

                    pageContent.add(pushedNum);

                }
                // push 1st page to the list
                pageList.add(pageContent.toString());

                // clear page content for next loop
                pageContent.clear();

                //System.out.println(pageList.toString());
            }
            System.out.println("DB Size : " + comicArr.size());
            System.out.println("Pagelist size: " + pageList.size() * perPage);

            // get remaining length of remaining numbers
            int fixedSize = (comicArr.size() - (pageList.size() * perPage));

            System.out.println("Numbers not pushed yet : " + fixedSize);

            // push the rest of the numbers outside the array as one array if perPage is inequal to size of comicArr
            if (comicArr.size() % perPage != 0) {
                for (var y = fixedSize - 1; y >= 0; y--) {

                    // clear out previous entry
                    // numbers left out
                    pageContent.add(l + 1 + ". " + comicArr.get(l).getTitle());

                }
                // push in last array
                pageList.add(pageContent.toString());
            }
            // final array 
            // System.out.println(pageList.toString());

//        // content that was not pushed !
//        System.out.println(comicArr[lastNum]);
//        // push last num in array!
//        pageList.add(Integer.toString(comicArr[lastNum]));
//        
//        // final array
//        System.out.println(pageList.toString());
//
            // to scroll through content
            //int choice = 0;
            // optionpane section
            JTextField selectNumber = new JTextField(5);
            JPanel myPanel = new JPanel();

            myPanel.add(selectNumber);

            String[] buttons = {"Next", "Previous", "Exit", "Select"};

            // store numbers in label
            JLabel result = new JLabel();

            //do {
            try {
                // i value handling
                if (i < 0) {
                    i = 0;
                } else if (i > pageList.size() - 1) {
                    i = pageList.size() - 1;
                }

                // change text
                result.setText(pageList.get(i));

                myPanel.add(result);

                choice = JOptionPane.showOptionDialog(null, myPanel, "Select a comic to add into " + renteeArr.get(index).getName(), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);

                switch (choice) {
                    case 0:
                        i++;
                        break;
                    case 1:
                        i--;
                        break;

                    case 2:
                        break;
                    case 3:
                        int selectedComic = Integer.parseInt(selectNumber.getText()) - 1;
                        JOptionPane.showMessageDialog(null, "Selected Comic : " + comicArr.get(selectedComic).getTitle(), "Selected Comic", JOptionPane.INFORMATION_MESSAGE);
                        onAddRenteeComics(index, selectedComic);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }

        } while (choice != 2);
    }

    public void onAddRenteeComics(int index, int selectedComic) {

        int result = 0;

        String[] buttons = {"Yes", "No"};

        try {
            result = JOptionPane.showOptionDialog(null, "Add comic " + comicArr.get(selectedComic).getTitle() + " ?", "Add Comics into rentee", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);

            switch (result) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Successfully Added comic " + comicArr.get(selectedComic).getTitle() + " into " + renteeArr.get(index).getName() + " with Member ID " + renteeArr.get(index).getID());

                    // add comic
                    renteeArr.get(index).storeComicList(comicArr.get(selectedComic));

                    break;
                case 1:
                    break;

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void deleteRenteeComics(int index) {

        // to scroll through content
        if (renteeArr.get(index).comicList.size() > 0) {
            int choice = 0;
            // initial page
            int i = 0;
            do {

                int perPage = 2;

                String pushedNum;

                // start pos
                int l = 0;

                // size of comics of rentee
                int size = renteeArr.get(index).comicList.size() / perPage;

                // stores 15 numbers in 1 page, depending on the max
                // then push page content to 1st page of list
                // actual formula will be comicArr size divided by 15 , result will be the loop repeat plus 1 because we want the remaining comiics to be stored still
                // clear previous entries
                pageList.clear();
                pageContent.clear();

                for (var j = 0; j < size; j++) {
                    for (var k = 0; k < perPage; k++) {

                        // instantiate to null to empty arr
                        pushedNum = null;

                        // parse tostr
                        // Comic list
                        // gets title of comic stored in rentee
                        pushedNum = (l + 1 + ". " + renteeArr.get(index).comicList.get(l).getTitle());

                        System.out.println("Comic : " + renteeArr.get(index).comicList.get(l).getTitle() + "^");
                        // move up the array
                        l += 1;

                        pageContent.add(pushedNum);

                    }
                    // push 1st page to the list
                    pageList.add(pageContent.toString());

                    // clear page content for next loop
                    pageContent.clear();

                    //System.out.println(pageList.toString());
                }
                System.out.println("DB Size : " + renteeArr.get(index).comicList.size());
                System.out.println("Pagelist size: " + pageList.size() * perPage);

                // get remaining length of remaining numbers
                int fixedSize = (renteeArr.get(index).comicList.size() - (pageList.size() * perPage));

                System.out.println("Numbers not pushed yet : " + fixedSize);

                // push the rest of the numbers outside the array as one array if perPage is inequal to size of comicArr
                if (renteeArr.get(index).comicList.size() % perPage != 0) {
                    for (var y = fixedSize - 1; y >= 0; y--) {

                        // clear out previous entry
                        // numbers left out
                        pageContent.add(l + 1 + ". " + renteeArr.get(index).comicList.get(l).getTitle());

                    }
                    // push in last array
                    pageList.add(pageContent.toString());
                }
                // final array 
                //System.out.println(pageList.toString());

//        // content that was not pushed !
//        System.out.println(comicArr[lastNum]);
//        // push last num in array!
//        pageList.add(Integer.toString(comicArr[lastNum]));
//        
//        // final array
//        System.out.println(pageList.toString());
//
                // to scroll through content
                //int choice = 0;
                // optionpane section
                JTextField selectNumber = new JTextField(5);
                JPanel myPanel = new JPanel();

                myPanel.add(selectNumber);

                String[] buttons = {"Next", "Previous", "Exit", "Select"};

                // store numbers in label
                JLabel result = new JLabel();

                //do {
                try {
                    // i value handling
                    if (i < 0) {
                        i = 0;
                    } else if (i > pageList.size() - 1) {
                        i = pageList.size() - 1;
                    }

                    // change text
                    result.setText(pageList.get(i));

                    myPanel.add(result);

                    choice = JOptionPane.showOptionDialog(null, myPanel, "Select a Comic from Rentee " + renteeArr.get(index).getName(), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);

                    switch (choice) {
                        case 0:
                            i++;
                            break;
                        case 1:
                            i--;
                            break;

                        case 2:
                            break;
                        case 3:
                            int selectedComic = Integer.parseInt(selectNumber.getText()) - 1;
                            JOptionPane.showMessageDialog(null, "Selected Comic : " + renteeArr.get(index).comicList.get(selectedComic).getTitle(), "Selected Comic", JOptionPane.INFORMATION_MESSAGE);
                            onDeleteRenteeComic(index, selectedComic);
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }

            } while (choice != 2);
        } else {
            JOptionPane.showMessageDialog(null, "Rentee does not own any comics!. Please add in comics to the rentee.", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Comic database is empty!");
        }
    }

    public void onDeleteRenteeComic(int index, int selectedComic) {

        int result = 0;
        boolean hasDeleted = false;
        String[] buttons = {"Yes", "No"};

        try {
            result = JOptionPane.showOptionDialog(null, "Delete comic " + renteeArr.get(index).comicList.get(selectedComic).getTitle() + " ?", "Create Comics", JOptionPane.YES_NO_CANCEL_OPTION, 0, null, buttons, buttons[0]);

            switch (result) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Successfully deleted Comic " + renteeArr.get(index).comicList.get(selectedComic).getTitle() + " from " + renteeArr.get(index).getName() + " with Member ID " + renteeArr.get(index).getID());
                    renteeArr.get(index).comicList.remove(selectedComic);
                    hasDeleted = true;
                    break;
                case 1:
                    break;

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public boolean editRenteeInformation(int index, String setMemberID, String setName) {

        int result = 0;
        String[] buttons = {"Edit Information", "Exit"};
        boolean success = false;
      
            try {

                //Validate if ID already exists in database
                // set new ID
                boolean alreadyExists = false;
                String compareStr = setID(setMemberID);

                for (var i = 0; i < renteeArr.size(); i++) {
                    if (compareStr.equals(renteeArr.get(i).getID())) {

                        //if it compares its own memberID / no changes to ID
                        if (compareStr.equals(renteeArr.get(index).getID())) {
                            // bypass validation
                            alreadyExists = false;
                        } // if it is another rentee's ID
                        else {
                            JOptionPane.showMessageDialog(null, "Member ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                            alreadyExists = true;
                        }

                    }
                }

                if (alreadyExists == false) {
                    renteeArr.get(index).setID(setMemberID);
                    // set new name
                    renteeArr.get(index).setName(setName);
                    JOptionPane.showMessageDialog(null, "Successfully edit rentee with information.");
                    success = true;
                } else {
                    throw new Exception("Member ID Already Exists!");
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
            return success;
    }

    public void onDeleteRentee(int index) {
        if (renteeArr.size() > 0) {
            int result = 0;
            boolean hasDeleted = false;
            String[] buttons = {"Yes", "No"};

            try {
                result = JOptionPane.showOptionDialog(null, "Delete rentee " + renteeArr.get(index).getName() + " ?", "Delete rentee", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, buttons, buttons[0]);

                switch (result) {
                    case 0:
                        JOptionPane.showMessageDialog(null, "Successfully deleted rentee " + renteeArr.get(index).getName() + ".");
                        renteeArr.remove(index);
                        hasDeleted = true;
                        break;
                    case 1:
                        break;

                }

            } catch (Exception ex) {
                System.out.println(ex);

            }
        } else {
            JOptionPane.showMessageDialog(null, "Rentee database is empty! Please add in rentees to the database.");
            throw new IllegalArgumentException("Rentee database is empty!");
        }
    }

    // validation
    public String formatISBN(String ISBN) {
        // used double as any digits longer than 9 numbers will throw a numberformatexception
        // validates if input ISBN is 13 numbers, else it will throw an exception
        // regex matches only numbers
        if (String.valueOf(ISBN).length() == 13 && ISBN.matches("[0-9]+")) {
            int offset = 3;
            String input = ISBN;

            StringBuilder output = new StringBuilder(input);
            System.out.println("\nInputted ISBN : " + ISBN);

            System.out.println("Formatting " + output + "...");

            output.insert(offset, '-');

            System.out.println("\nFormatted ISBN : " + output.toString());
            return output.toString();

        } else {
            throw new IllegalArgumentException("ISBN-13 Must be of 13 Numbers, NUMBERS!!!");
        }
    }

    public String setID(String ID) {
        if (String.valueOf(ID).length() == 6 && ID.matches("[0-9]+")) {
            int offset = 0;
            String input = ID;

            StringBuilder output = new StringBuilder(input);
            System.out.println("\nInputted ISBN : " + ID);

            System.out.println("Formatting " + output + "...");

            output.insert(offset, 'M');

            System.out.println("\nFormatted ISBN : " + output.toString());

            return output.toString();
        } else {
            JOptionPane.showMessageDialog(null, "Member ID Must be of 6 Numbers!", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Member Must be of 6 Numbers,NUMBERS!!!");
        }
    }

    // CA2 section
    public String getISBN(int index) {
        return comicArr.get(index).getISBN();
    }

    public String getTitle(int index) {
        return comicArr.get(index).getTitle();
    }

    public double getRentalPerDay(int index) {
        return comicArr.get(index).getRentalPerDay();
    }

    public double getDepositCost(int index) {
        return comicArr.get(index).getCost();
    }

    public String getAdditionalInfo(int index) {
        return comicArr.get(index).getAdditionalInfo();
    }

    public Comic getComicObject(int index) {
        return comicArr.get(index);
    }

    public String getMemberID(int index) {
        return renteeArr.get(index).getID();
    }

    public String getMemberName(int index) {
        return renteeArr.get(index).getName();
    }

    public ArrayList<Comic> getMemberComicList(int index) {
        return renteeArr.get(index).getComicsListArray();
    }

    public void addRenteeToDatabase(Rentee input) {
        renteeArr.add(input);
    }

    public void addComicToRentee(int index, Comic INPUT_comic) {
        renteeArr.get(index).addComic(INPUT_comic);
    }

    public Rentee getRenteeObject(int index) {
        return renteeArr.get(index);
    }

    public int getComicListSize() {
        return comicArr.size();
    }

    public int getRenteeArrSize() {
        return renteeArr.size();
    }

    public void StopSystem(long startTime) {
        JOptionPane.showMessageDialog(null, "Saving data..");
        Database.OutputRentee(renteeArr);
        JOptionPane.showMessageDialog(null, "Thank you for using Comic Rental.\nWe look forward to serve you in the near future.");
        System.out.println("\n*******Thank you for using Comic Rental.*******\nWe look forward to serve you in the near future.\n\nShutting down...");
        
        
                        // stop taking time of program 
                long endTime = System.nanoTime();
                // calculate time taken, convert microseconds to seconds
                long duration = ((endTime / 1000000000) - (startTime / 1000000000));
                // please fix lol ( DONE )
                JOptionPane.showMessageDialog(null, systemUptime(duration), "Shut Down", JOptionPane.INFORMATION_MESSAGE);

        // to close program
        System.exit(0);

    }

    //testing purposes
    public static void main(String[] args) {
        RentalSystem S1 = new RentalSystem();

        S1.adminMenu();
    }

}
