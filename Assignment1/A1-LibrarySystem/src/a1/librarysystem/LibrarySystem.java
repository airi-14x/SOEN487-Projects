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
 * @author jasminelatendresse
 */
// Not a Main Class. This will be used by the Library Service
public class LibrarySystem {

    private ConcurrentHashMap<Integer, Book> books;
    //private ServletContext context;
    private AtomicInteger bookMapKey;

    public LibrarySystem() {
        books = new ConcurrentHashMap<Integer, Book>();
        bookMapKey = new AtomicInteger();
    }

    public ConcurrentHashMap<Integer, Book> getMap() {
        // !! Need to check if Servlet Context exists
        //if (getServletContext() == null ) return null;

        // !! Need to load the map of Books first time around!
        // **Method**
        return books;
    }

    public int addBook(Book book) {
        int bookID = bookMapKey.incrementAndGet();
        book.setId(bookID);
        books.put(bookID, book);
        return book.getId();
    }

    public Book getBook(int id) {
        // Find book with id!
        // Should check existance of id!!! To avoid null pointer errors.
        Book currentBook = books.get(id);
        return currentBook;
    }

    public int updateBook(Book book) {
        return book.getId();
    }

    public String removeBook(int id) {
        return "Book with " + id + " has been removed";
    }

}
