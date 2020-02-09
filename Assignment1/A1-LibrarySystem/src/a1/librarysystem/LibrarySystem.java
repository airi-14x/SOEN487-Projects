/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a1.librarysystem;

import a1.librarycore.Book;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Airi
 */
public class LibrarySystem {
    private static ConcurrentHashMap<Integer, Book> books = new ConcurrentHashMap<Integer, Book>();
    private static AtomicInteger bookMapKey = new AtomicInteger();
    private static LibrarySystem instance = null;
    
    // Cannot be private
    public LibrarySystem(){
        System.out.println("Created an instance of LibrarySystem");
        //books = new ConcurrentHashMap<>();
        //bookMapKey = new AtomicInteger();
    }
    
    /*public static synchronized LibrarySystem getInstance(){
        if (instance == null){
            instance = new LibrarySystem();
            System.out.println("Created an instance of LibrarySystem");
            books = new ConcurrentHashMap<>();
            bookMapKey = new AtomicInteger();
        }
        return instance;
    }*/
    
    public ConcurrentHashMap<Integer, Book> getMap() {
        return books;
    }
    
    public synchronized String displayBooks(){
       String currentBooks = books.toString();
       if(books.isEmpty()) {
           return("No books to display");
       }
       return currentBooks;
    }

    //POST
    public synchronized String addBook(String title, String description, String isbn, String author, String publisher) {
        Book book = new Book(title, description, isbn, author, publisher);
        int bookID = bookMapKey.incrementAndGet();
        book.setId(bookID);
        books.put(bookID, book);
        String bookInfo = book.toString();
        return "You just created this book: " + bookInfo;
    }

    //GET
    public synchronized String getBook(int id) {
        if (books.containsKey(id)){
            Book currentBook = books.get(id);
            String bookInfo = currentBook.toString();
            return bookInfo;
        }
        else{
            return "Book doesn't exist";
        }

    }

    //PUT
    public synchronized String updateBook(int id, String title, String description, String isbn, String author, String publisher) {
        Book book = new Book(title, description, isbn, author, publisher);
        book.setId(id);
        if (books.get(id) == null) {
            return "Book cannot be updated";
        } else {
            books.put(id, book); // Replace with current book object
            return "Updated book";
        }
    }

    //DELETE
    public synchronized String removeBook(int id) {
        books.remove(id); // NULL if doesn't exist
        if (books.get(id) == null) {
            return "Book with " + id + " has been removed.";
        } else {
            return "Book cannot be removed!";
        }
    }

    @Override
    public String toString() {
        return "LibrarySystem{" + "books=" + books + ", bookMapKey=" + bookMapKey + '}';
    }
    
}
