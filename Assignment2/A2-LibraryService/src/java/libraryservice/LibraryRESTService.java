package libraryservice;

import a2.librarycore.Book;
import a2.librarysystem.Library;
import a2.librarysystem.LibraryException;
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
    public Response listBooksJSON() {
        String output = librarySystem.displayBooks();
        return Response.status(200).entity(output).build();
    }
    
    //TODO
    //List books - Produces XML
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/books_xml")
    public Response listBooksXML() {
        String output = librarySystem.displayBooks();
        return Response.status(200).entity(output).build();
    }
    
    //TODO
    //List books - Produces HTML
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/books_html")
    public Response listBooksHTML() {
        String output = librarySystem.displayBooks();
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
    
    //TODO
    //Get book - Produces HTML
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/book_html/{id}")
    public Response getBookHTML(@PathParam("id") int id) {
        String output = librarySystem.getBook(id);
        return Response.status(200).entity(output).build();
    }
    
    //Add book - Basic data types 
    @POST
    @Path("/book_basic/add")
    public Response addBookBasic(@QueryParam("title") String title,
            @QueryParam("description") String description,
            @QueryParam("isbn") String isbn,
            @QueryParam("author") String author,
            @QueryParam("publisher") String publisher,
            @QueryParam("callNumber") String callNumber) {
        try{
            librarySystem.addBook(title, description, isbn, author, publisher, callNumber);
            return Response.status(200).entity("Success").build();
        }
        catch(Exception e){
            return Response.status(500).entity("Error").build();
        }
        
    }

    //TODO
    //Add book - Complex data types 
    @POST
    @Path("/book_complex/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBookComplex(@QueryParam("title") String title,
            @QueryParam("description") String description,
            @QueryParam("isbn") String isbn,
            @QueryParam("author") String author,
            @QueryParam("publisher") String publisher,
            @QueryParam("callNumber") String callNumber) {
        try{
            librarySystem.addBook(title, description, isbn, author, publisher, callNumber);
            return Response.status(200).entity("Success").build();
        }
        catch(Exception e){
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
        try{
            librarySystem.updateBook(id, title, description, isbn, author, publisher, callNumber);
            return Response.status(200).entity("Success").build();
        }
        catch(Exception e){
            return Response.status(500).entity("Error").build();
        }
    }
    
    //Update book - Complex data types
    
    //Delete book - Produces TEXT_PLAIN
    
    //Delete book - Produces JSON
    
    //Delete book - Produces XML
    
    //Delete book - Produces HTML

}
