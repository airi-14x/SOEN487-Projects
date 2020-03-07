/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.library;

import a2.librarycore.Book;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Airi
 */
public class A2Library {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, LibraryException, IOException {
        Library library = Library.getInstance();
        library.dropLibraryTable();
        library.createLibraryTable();
        System.out.println("Create Table");
        ResultSet result;

        // Adding a book
        Book book1 = new Book("Meditations", "Written in Greek, without any intention of publication, by the only Roman emperor",
                "01404493371", "Marcus Aurelius", "Penguin Classic");
        String callNumber1 = "B 583 S74 2012";
        Book book2 = new Book("2-Meditations", "Written in Greek, without any intention of publication, by the only Roman emperor",
                "01404493372", "Marcus Aurelius", "Penguin Classic");
        String callNumber2 = "B 583 S74 2021";
        library.addNewBook(book1,callNumber1);
        library.addNewBook(book2,callNumber2);
        
        // Update book
        String callNumber3 = "B 583 S74 2022";
        library.updateBookInfo(1, book1, callNumber3);

        // List all Books
        System.out.println("List All:");
        result = library.listAllBooks();
        while (result.next()) {
            System.out.println(result.getString("id"));
            System.out.println(result.getString("title"));
            System.out.println(result.getString("description"));
            System.out.println(result.getString("isbn"));
            System.out.println(result.getString("author"));
            System.out.println(result.getString("publisher"));
            System.out.println(result.getString("call_number"));
            System.out.println("---");
        }

        // List a book
        System.out.println("List a book:");
        result = library.getBookInfo(1);
        while (result.next()) {
            System.out.println(result.getString("id"));
            System.out.println(result.getString("title"));
            System.out.println(result.getString("description"));
            System.out.println(result.getString("isbn"));
            System.out.println(result.getString("author"));
            System.out.println(result.getString("publisher"));
            System.out.println(result.getString("call_number"));
            System.out.println("---");
        }

        // Delete a book
        library.deleteBook(1);
        result = library.listAllBooks();
        System.out.println("After deleting a book:");
        while (result.next()) {
            System.out.println(result.getString("id"));
            System.out.println(result.getString("title"));
            System.out.println(result.getString("description"));
            System.out.println(result.getString("isbn"));
            System.out.println(result.getString("author"));
            System.out.println(result.getString("publisher"));
            System.out.println(result.getString("call_number"));
            System.out.println("---");
        }

    }

}
