package libraryservice;

import a2.librarycore.Book;
import a2.librarysystem.Library;
import a2.librarysystem.LibraryException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    //TODO
    //List books - Produces JSON
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/books_json")
    public Response listBooksJSON() throws JsonProcessingException {
        ConcurrentHashMap bookMap = librarySystem.getBooksMap();
        List<String> bookList = new ArrayList(bookMap.values());
        ObjectMapper objectMapper = new ObjectMapper();
        String output = objectMapper.writeValueAsString(bookList);
        return Response.status(200).entity(output).build();
    }

    //TODO - Make root element appear as Book
    //List books - Produces XML
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/books_xml")
    public Response listBooksXML() throws JsonProcessingException, LibraryException {
        ConcurrentHashMap bookMap = librarySystem.getBooksMap();
        XmlMapper objectMapper = new XmlMapper();
        String output = objectMapper.writeValueAsString(bookMap.values());
        return Response.status(200).entity(output).build();
    }

    //List books - Produces HTML
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/books_html")
    public Response listBooksHTML() throws LibraryException {
        ConcurrentHashMap bookMap = librarySystem.getBooksMap();
        List<String> bookList = new ArrayList(bookMap.values());
        StringBuilder output = new StringBuilder();
        output.append("<html><body><table><h1>List of all books</h1>");
        for (Iterator iter = bookList.iterator(); iter.hasNext();) {
            output.append("<tr><td>" + iter.next() + "</td></tr>");
        }
       output.append("</table></body></html>");
        
        return Response.status(200).entity(output.toString()).build();
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
        String output = "<html> " + "<title>" + "Library System" + "</title>"
                + "<body>"
                + "<h1>" + "Book information:" + "</h1>"
                + "<div>"
                + "Title: " + book.getTitle() + "<br/>"
                + "Description: " + book.getDescription() + "<br/>"
                + "Isbn: " + book.getIsbn() + "<br/>"
                + "Author: " + book.getAuthor() + "<br/>"
                + "Publisher: " + book.getPublisher() + "<br/>"
                + "Call Number: " + book.getCallNumber() + "<br/>"
                + "</body>" + "</html> ";
        return Response.status(200).entity(output).build();
    }

    //Add book - Basic data types 
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/book_basic/add")
    public Response addBookBasic(@QueryParam("title") String title,
            @QueryParam("description") String description,
            @QueryParam("isbn") String isbn,
            @QueryParam("author") String author,
            @QueryParam("publisher") String publisher,
            @QueryParam("callNumber") String callNumber) {
        try {
            librarySystem.addBook(title, description, isbn, author, publisher, callNumber);
            return Response.status(200).entity("Success").build();
        } catch (Exception e) {
            return Response.status(500).entity("Error").build();
        }

    }

    //TODO
    //Add book - Complex data types 
    @POST
    @Path("/book_complex/add")
    @Consumes(MediaType.APPLICATION_XML)
    public Response addBookComplex(Book book) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        Book bookObject = xmlMapper.readValue("<Book><title>" + book.getTitle() + "</title>" +
                "<description>" + book.getDescription() + "</description>" + 
                "<isbn>" + book.getIsbn() + "</isbn>" + 
                "<author>" + book.getAuthor() + "</author>" +
                "<publisher>" + book.getPublisher() + "</publisher>" + 
                "<callNumber>" + book.getCallNumber() + "</callNumber></Book>", Book.class);
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
    public Response updateBook(@QueryParam("id") int id,
            @QueryParam("title") String title,
            @QueryParam("description") String description,
            @QueryParam("isbn") String isbn,
            @QueryParam("author") String author,
            @QueryParam("publisher") String publisher,
            @QueryParam("callNumber") String callNumber) {
        try {
            librarySystem.updateBook(id, title, description, isbn, author, publisher, callNumber);
            return Response.status(200).entity("Success").build();
        } catch (Exception e) {
            return Response.status(500).entity("Error").build();
        }
    }

    //Update book - Complex data types
    //Delete book - Produces TEXT_PLAIN
    //Delete book - Produces JSON
    //Delete book - Produces XML
    //Delete book - Produces HTML
}
