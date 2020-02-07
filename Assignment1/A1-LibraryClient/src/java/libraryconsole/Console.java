package libraryconsole;

import a1.librarycore.Book;
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
        
        
        int id;
        String title;
        String description;
        String isbn;
        String author;
        String publisher;
        
        while (true) {
            displayOptions();
            Scanner scan = new Scanner(System.in);
            int userOption = scan.nextInt();
           
            switch (userOption) {
                case 1:
                    displayHelpMenu();
                    break;
                case 2:
                    System.out.println("List of all current books:");
                    client.listBooks(Response.class);
                    break;
                case 3:
                    System.out.print("Enter the id of the book you want to display:");
                    id = scan.nextInt();
                    System.out.println(client.getBook(String.class, id));
                    break;
                case 4:
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
                    client.addBook(title, description, isbn, author, publisher);
                    break;
                case 5:
                    System.out.print("Enter the id of the book you want to update:");
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
                    break;
                case 6:
                    System.out.print("Enter the id of the book you want to delete:");
                    id = scan.nextInt();
                    client.deleteBook(id);
                    break;
                case 7:
                    System.out.println("Exiting Library Console App.");
                    System.exit(0);
                    break;
                default:
                    break;
            }

        }
    }

    private static void displayOptions() {
        System.out.println("Please choose from the following options: ");
        System.out.println("1: Help / About");
        System.out.println("2: List current books");
        System.out.println("3: Display book");
        System.out.println("4: Add Book");
        System.out.println("5: Update Book");
        System.out.println("6: Delete Book");
        System.out.println("7: Quit");
    }
    
    private static void displayHelpMenu() {
        
    }
    
}
