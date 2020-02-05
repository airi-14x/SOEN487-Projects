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
    private ConcurrentHashMap<Integer, Book> books;
    //private ServletContext context;
    private AtomicInteger bookMapKey;
    
    public LibrarySystem(){
        books = new ConcurrentHashMap<Integer, Book>();
        bookMapKey = new AtomicInteger();
    }
    
    public ConcurrentHashMap<Integer, Book> getMap() {
        // !! Need to check if Servlet Context exists
        //if (getServletContext() == null ) return null;
        //System.out.println(books.toString());
        return books;
    }
    
    public String displayBooks(){
       String currentBooks = books.toString();
       return currentBooks;
    }

    public String addBook(String title, String description, String isbn, String author, String publisher) {
        Book book = new Book(title, description, isbn, author, publisher);
        int bookID = bookMapKey.incrementAndGet();
        book.setId(bookID);
        books.put(bookID, book);
        String bookInfo = book.toString();
        return "You just created this book: " + bookInfo;
    }

    public String getBook(int id) {
        if (books.containsKey(id)){
            Book currentBook = books.get(id);
            String bookInfo = currentBook.toString();
            return bookInfo;
        }
        else{
            return "Book doesn't exist";
        }

    }

    public String updateBook(int id, String title, String description, String isbn, String author, String publisher) {
        Book book = new Book(title, description, isbn, author, publisher);
        if (books.get(id) == null) {
            return "Book cannot be updated";
        } else {
            books.put(id, book); // Replace with current book object
            return "Updated book";
        }
    }

    public String removeBook(int id) {
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
