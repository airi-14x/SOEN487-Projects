/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a1.main;

import a1.librarycore.Book;
import a1.librarysystem.LibrarySystem;

/**
 *
 * @author Airi
 */
public class Main {

    public static void main(String args[]) {
        LibrarySystem librarySystem = new LibrarySystem();
        //String output = librarySystem.getBook(Integer.parseInt("4"));
        //System.out.println(output);
        //Book book = new Book("abc","Hello World", "1213", "sss", "ddd");
        librarySystem.addBook("abc", "Hello World", "1213", "sss", "ddd");
        System.out.println(librarySystem.displayBooks());
        System.out.println(librarySystem.getBook(1).toString());
    }
}
