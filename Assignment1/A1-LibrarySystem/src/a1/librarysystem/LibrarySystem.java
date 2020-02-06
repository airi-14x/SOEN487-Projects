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
    private static ConcurrentHashMap<Integer, Book> books;
    private static AtomicInteger bookMapKey;
    private static LibrarySystem instance = null;
    
    // Cannot be private
    public LibrarySystem(){
    }
    
    public static synchronized LibrarySystem getInstance(){
        if (instance == null){
            instance = new LibrarySystem();
            System.out.println("Created an instance of LibrarySystem");
            books = new ConcurrentHashMap<Integer, Book>();
            bookMapKey = new AtomicInteger();
        }
        return instance;
    }
    
    public ConcurrentHashMap<Integer, Book> getMap() {
        return books;
    }
    
    public synchronized String displayBooks(){
       String currentBooks = books.toString();
       return currentBooks;
    }

    public synchronized String addBook(String title, String description, String isbn, String author, String publisher) {
        Book book = new Book(title, description, isbn, author, publisher);
        int bookID = bookMapKey.incrementAndGet();
        book.setId(bookID);
        books.put(bookID, book);
        String bookInfo = book.toString();
        return "You just created this book: " + bookInfo;
    }

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

    public synchronized String updateBook(int id, String title, String description, String isbn, String author, String publisher) {
        Book book = new Book(title, description, isbn, author, publisher);
        if (books.get(id) == null) {
            return "Book cannot be updated";
        } else {
            books.put(id, book); // Replace with current book object
            return "Updated book";
        }
    }

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
