package libraryconsole;

import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jasminelatendresse
 */
public class Console {
    public static void main(String[] args) {
        //User interactions
        //Create console menu to display to user
        
        System.out.println(" =========================================");
        System.out.println("|   Welcome to the Library Console App    |");
        System.out.println(" =========================================");
        displayOptions();
        
        Scanner scan = new Scanner(System.in);
        int userOption = scan.nextInt(); // Error check needed
        
        switch(userOption){
            case 1:
                break;
            case 2: 
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            default:
                break;
        }
       
        
    }
    
    public static void displayOptions(){
        System.out.println("Please choose from the following options: ");
        System.out.println("1: Help / About");
        System.out.println("2: List current books");
        System.out.println("3: Display book");
        System.out.println("4: Add Book");
        System.out.println("5: Update Book");
        System.out.println("6: Delete Book");
        System.out.println("7: Quit");
    }
}
