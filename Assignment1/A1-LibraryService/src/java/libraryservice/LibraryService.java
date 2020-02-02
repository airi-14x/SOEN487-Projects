/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryservice;

import a1.librarycore.Book;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author jasminelatendresse
 */
// Not a Main Class. This will be used by the Library Service
public class LibraryService {

    private ConcurrentHashMap<Integer, Book> books;
    //private ServletContext context;
    private AtomicInteger bookMapKey;

    public LibraryService() {
        books = new ConcurrentHashMap<Integer, Book>();
        bookMapKey = new AtomicInteger();
    }

    public ConcurrentHashMap<Integer, Book> getMap() {
        // !! Need to check if Servlet Context exists
        //if (getServletContext() == null ) return null;

        return books;
    }

    public String addBook(Book book) {
        int bookID = bookMapKey.incrementAndGet();
        book.setId(bookID);
        books.put(bookID, book);
        return "Created book with " + book.getId();
    }

    public Book getBook(int id) {
        Book currentBook = books.get(id); // NULL if doesn't exist
        return currentBook;
    }

    public String updateBook(Book book) {

        if (books.get(book.getId()) == null) {
            return "Book cannot be updated";
        } else {
            books.put(book.getId(), book); // Replace with current book object
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
