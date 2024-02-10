/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// ------------------------------------
// Name : Landicho James Evans Arvesu
// Class : DIT/FT/1B/04
// Admission Number : P2107967
// ------------------------------------

import javax.swing.JOptionPane;

public class RentalMenu {

    public static void main(String[] args) {

        // take timing of start program
        long startTime = System.nanoTime();
        RentalSystem RentSys = new RentalSystem();
        try {

            RentSys.StartProgram();
            

        } catch (Exception ex) {
            System.out.println(ex);
        }

        // stop taking time of program 
        long endTime = System.nanoTime();
        // calculate time taken, convert microseconds to seconds
        long duration = ((endTime / 1000000000) - (startTime / 1000000000));
        // please fix lol ( DONE )
        JOptionPane.showMessageDialog(null, RentSys.systemUptime(duration), "Shut Down", JOptionPane.INFORMATION_MESSAGE);

        // testing methods purposes
        //RentSys.manageComics();
        //RentSys.getComicISBN();
        //RentSys.getMemberID();
        //RentSys.getEarningStatistic();
        //AdminSys.adminLoginArr.get(0).changePassword();
    }

}
