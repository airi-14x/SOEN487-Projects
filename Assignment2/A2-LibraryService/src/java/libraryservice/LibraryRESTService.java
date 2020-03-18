package libraryservice;

import a2.librarycore.Book;
import a2.librarycore.BookList;
import a2.librarysystem.Library;
import a2.librarysystem.LibraryException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jasminelatendresse
 */
@Path("/LibraryRESTService")
public class LibraryRESTService {

    Library librarySystem = new Library();

    //List books - Produces TEXT_PLAIN
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/books_plain")
    public Response listBooksPlain() {
        String output = librarySystem.displayBooks();
        return Response.status(200).entity(output).build();
    }

    //List books - Produces JSON
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/books_json")
    public List<Book> listBooksJSON() {
        ConcurrentHashMap bookMap = librarySystem.getBooksMap();
        return new ArrayList<>(bookMap.values());
    }

    //List books - Produces XML
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/books_xml")
    public BookList listBooksXML() throws LibraryException {
        ConcurrentHashMap bookMap = librarySystem.getBooksMap();
        List<Book> bookList = new ArrayList<>(bookMap.values());
        BookList books = new BookList();
        books.setList(bookList);
        return books;
    }

    //List books - Produces HTML
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/books_html")
    public Response listBooksHTML() throws LibraryException {
        String output = librarySystem.booksToHtml();
        return Response.status(200).entity(output).build();
    }

    //Get book - Produces TEXT_PLAIN
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/book_plain/{id}")
    public Response getBookPlain(@PathParam("id") int id) {
        String output = librarySystem.getBook(id);
        return Response.status(200).entity(output).build();
    }

    //Get book - Produces JSON
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/book_json/{id}")
    public Response getBookJSON(@PathParam("id") int id) throws LibraryException {
        Book book = librarySystem.getBookById(id);
        return Response.status(200).entity(book).build();
    }

    //Get book - Produces XML
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/book_xml/{id}")
    public Response getBookXML(@PathParam("id") int id) throws LibraryException {
        Book book = librarySystem.getBookById(id);
        return Response.status(200).entity(book).build();
    }

    //Get book - Produces HTML
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/book_html/{id}")
    public Response getBookHTML(@PathParam("id") int id) throws LibraryException {
        Book book = librarySystem.getBookById(id);
        String output = librarySystem.bookToHtml(book);
        return Response.status(200).entity(output).build();
    }

    //Add book - Basic data types 
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/book_basic/add")
    public Response addBookBasic(@FormParam("title") String title,
            @FormParam("description") String description,
            @FormParam("isbn") String isbn,
            @FormParam("author") String author,
            @FormParam("publisher") String publisher,
            @FormParam("callNumber") String callNumber) {
        try {
            librarySystem.addBook(title, description, isbn, author, publisher, callNumber);
            return Response.status(200).entity("Success").build();
        } catch (Exception e) {
            return Response.status(500).entity("Error").build();
        }

    }

    //Add book - Complex data types 
    @POST
    @Path("/book_json/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBookComplex(String book) throws IOException, JAXBException {
        ObjectMapper objectMapper = new ObjectMapper();
        Book bookObject = objectMapper.readValue(book, Book.class);
        try {
            librarySystem.addBook(bookObject.getTitle(), bookObject.getDescription(), bookObject.getIsbn(), bookObject.getAuthor(),
                    bookObject.getPublisher(), bookObject.getCallNumber());
            return Response.status(200).entity("Success").build();
        } catch (Exception e) {
            return Response.status(500).entity("Error").build();
        }

    }

    //Update book - Basic data types
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/book_basic/update")
    public Response updateBookBasic(@FormParam("id") int id,
            @FormParam("title") String title,
            @FormParam("description") String description,
            @FormParam("isbn") String isbn,
            @FormParam("author") String author,
            @FormParam("publisher") String publisher,
            @FormParam("callNumber") String callNumber) {
        try {
            librarySystem.updateBook(id, title, description, isbn, author, publisher, callNumber);
            return Response.status(200).entity("Success").build();
        } catch (Exception e) {
            return Response.status(500).entity("Error").build();
        }
    }

    //Update book - Complex data types
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/book_json/update/{id}")
    public Response updateBookXml(@PathParam("id") int id, String bookJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Book book = objectMapper.readValue(bookJson, Book.class);
            librarySystem.updateBook(id, book.getTitle(), book.getDescription(), book.getIsbn(),
                    book.getAuthor(), book.getPublisher(), book.getCallNumber());
            return Response.status(200).entity("Success").build();
        } catch (Exception e) {
            return Response.status(500).entity("Error").build();
        }
    }

    //Delete book - Produces TEXT_PLAIN
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/delete/{id}")
    public Response deleteBookPlain(@PathParam("id") int id) {
        try {
            librarySystem.removeBook(id);
            return Response.status(200).entity("Success").build();
        } catch (LibraryException ex) {
            return Response.status(500).entity("Error").build();
            
        }
    }

}
