package libraryconsole;

import a1.librarycore.Book;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.ws.rs.core.Response;
import libraryclient.LibraryClient;

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

    public static LibraryClient client = new LibraryClient();

    public static void main(String[] args) {
        //User interactions
        //Create console menu to display to user

        System.out.println(" =========================================");
        System.out.println("|   Welcome to the Library Console App    |");
        System.out.println(" =========================================");
        System.out.println("");

        int id = -1;
        String title;
        String description;
        String isbn;
        String author;
        String publisher;
        boolean isAnumber = false;

        while (true) {
            displayOptions();
            Scanner scan = new Scanner(System.in);
            int userOption = 0;
            
            id = 0;
            title = "";
            description = "";
            isbn = "";
            author = "";
            publisher = "";

            try {
                userOption = scan.nextInt();
                isAnumber = true;
                System.out.println("-------------------------------------------");
            } catch (InputMismatchException e) {
                if(!isAnumber) {
                    System.out.println("Invalid input - Please enter a number");
                }
            }

            switch (userOption) {
                case 1:
                    displayHelpMenu();
                    break;
                case 2:
                    System.out.println("List of all current books:");
                    //System.out.println(client.listBooks(Response.class));
                    System.out.println(client.listBooks(String.class));
                    break;
                case 3:
                    System.out.print("Enter the id of the book you want to display:");
                    try {
                        id = scan.nextInt();
                        System.out.println(client.getBook(String.class, id));
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input - Please enter an integer");
                    }
                    break;
                case 4:
                    System.out.print("Enter a title:");
                    title = scan.next();
                    System.out.print("Enter a description:");
                    description = scan.next();
                    System.out.print("Enter a ISBN:");
                    isbn = scan.next();
                    System.out.print("Enter author:");
                    author = scan.next();
                    System.out.print("Enter publisher:");
                    publisher = scan.next();
                    System.out.println(client.addBook(title, description, isbn, author, publisher));
                    
                    break;
                case 5:
                    System.out.print("Enter the id of the book you want to update:");
                    try {
                        id = scan.nextInt();
                        System.out.print("Enter title:");
                        title = scan.next();
                        System.out.print("Enter a description:");
                        description = scan.next();
                        System.out.print("Enter a ISBN:");
                        isbn = scan.next();
                        System.out.print("Enter author:");
                        author = scan.next();
                        System.out.print("Enter publisher:");
                        publisher = scan.next();
                        client.updateBook(id, title, description, isbn, author, publisher);
                        //System.out.println(client.getBook(String.class, id));
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input - Please enter an integer");
                    }
                    break;
                case 6:
                    System.out.print("Enter the id of the book you want to delete:");
                    try{
                       id = scan.nextInt(); 
                       client.deleteBook(id);
                    }
                    catch(InputMismatchException e) {
                        System.out.println("Invalid input - Please enter an integer");
                    }
                    break;
                case 7:
                    System.out.println("Exiting Library Console App.");
                    System.exit(0);
                    break;
                default:
                    if(isAnumber) {
                        System.out.println("Option not found");
                    }
                    break;    
            }

        }
    }

    private static void displayOptions() {
        System.out.println("==========================================");
        System.out.println("Please choose from the following options: ");
        System.out.println("==========================================");
        System.out.println("1: Help / About");
        System.out.println("2: List current books");
        System.out.println("3: Display book");
        System.out.println("4: Add Book");
        System.out.println("5: Update Book");
        System.out.println("6: Delete Book");
        System.out.println("7: Quit");
        System.out.println("");
        System.out.print("Enter a number: ");
    }

    private static void displayHelpMenu() {

    }

}
