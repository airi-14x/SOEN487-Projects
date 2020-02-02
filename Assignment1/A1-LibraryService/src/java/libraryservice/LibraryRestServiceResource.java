/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryservice;

import a1.librarycore.Book;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author jasminelatendresse
 */
@Path("/LibraryRestService")
public class LibraryRestServiceResource {

    private ConcurrentHashMap<Integer, Book> books;
    //private ServletContext context;
    private AtomicInteger bookMapKey;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LibraryRestServiceResource
     */
    public LibraryRestServiceResource() {
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

    /**
     * Retrieves representation of an instance of
     * libraryservice.LibraryRestServiceResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of
     * LibraryRestServiceResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }
}
