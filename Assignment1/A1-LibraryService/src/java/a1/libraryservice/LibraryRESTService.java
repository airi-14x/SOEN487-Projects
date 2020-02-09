/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a1.libraryservice;

import a1.librarysystem.LibrarySystem;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 *
 * @author Airi
 */
@Path("/LibraryRESTService")
public class LibraryRESTService {

    LibrarySystem librarySystem = new LibrarySystem();
    
    // POSTMAN: http://localhost:8080/A1-LibraryService/webresources/LibraryRESTService/books
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/books")
    public Response listBooks() {
        String output = librarySystem.displayBooks();
        return Response.status(200).entity(output).build();
    }

    // POSTMAN: http://localhost:8080/A1-LibraryService/webresources/LibraryRESTService/book/1
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/book/{id}")
    public Response getBook(@PathParam("id") int id) {
        String output = librarySystem.getBook(id);
        return Response.status(200).entity(output).build();
    }

    // POSTMAN: http://localhost:8080/A1-LibraryService/webresources/LibraryRESTService/book/add?title=hello&description=2e2&isbn=23232&author=me&publisher=ff
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    //@Path("/book")
    @Path("/book/add")
    public Response addBook(@QueryParam("title") String title,
            @QueryParam("description") String description,
            @QueryParam("isbn") String isbn,
            @QueryParam("author") String author,
            @QueryParam("publisher") String publisher) {
        String output = librarySystem.addBook(title, description, isbn, author, publisher);
        return Response.status(200).entity(output).build();
    }
    
    // addBook with MultivaluedMap
   /* @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    //@Path("/book")
    @Path("/book/add")
    public Response addBook(MultivaluedMap<String, String> queryParams) {
        String title = queryParams.get("title").toString();
        String description = queryParams.get("description").toString();
        String isbn = queryParams.get("isbn").toString();
        String author = queryParams.get("author").toString();
        String publisher = queryParams.get("publisher").toString();
        String output = librarySystem.addBook(title, description, isbn, author, publisher);
        return Response.status(200).entity(output).build();
    }*/
    
    /*
    // Form Version --> To switch to Query Version when it's functional //
    // Add Book via: http://localhost:8080/A1-LibraryService/
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/book")
    //@Path("/book/add")
    public Response addBookForm(@FormParam("title") String title,
            @FormParam("description") String description,
            @FormParam("isbn") String isbn,
            @FormParam("author") String author,
            @FormParam("publisher") String publisher) {
        String output = librarySystem.addBook(title, description, isbn, author, publisher);
        return Response.status(200).entity(output).build();
    }*/
    

    // POSTMAN: http://localhost:8080/A1-LibraryService/webresources/LibraryRESTService/book/update?id=2&title=hello2&description=2e2&isbn=23232&author=me&publisher=ff
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    //@Path("/book")
    @Path("/book/update")
    public Response updateBook(@QueryParam("id") int id,
            @QueryParam("title") String title,
            @QueryParam("description") String description,
            @QueryParam("isbn") String isbn,
            @QueryParam("author") String author,
            @QueryParam("publisher") String publisher) {
        String output = librarySystem.updateBook(id, title, description, isbn, author, publisher);
        return Response.status(200).entity(output).build();
    }
    
    // updateBook with multivaluedMap
    public Response updateBook(MultivaluedMap<String, String> params) {
        int id = Integer.parseInt(params.get("id").toString());
        String title = params.get("title").toString();
        String description = params.get("description").toString();
        String isbn = params.get("isbn").toString();
        String author = params.get("author").toString();
        String publisher = params.get("publisher").toString();
        String output = librarySystem.updateBook(id, title, description, isbn, author, publisher);
        return Response.status(200).entity(output).build();
    }

    // POSTMAN: http://localhost:8080/A1-LibraryService/webresources/LibraryRESTService/book/delete?id=1
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    //@Path("/book")
    @Path("/book/delete")
    public Response deleteBook(@QueryParam("id") int id) {
        String output = librarySystem.removeBook(id);
        return Response.status(200).entity(output).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
        return "Hello LibraryService Plain";
    }

    // This method is called if XML is requested  
    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello> Hello LibraryService" + "</hello>";
    }

    // This method is called if HTML is requested  
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        return "<html> " + "<title>" + "Hello LibraryService" + "</title>"
                + "<body><h1>" + "Hello LibraryService HTML" + "</h1></body>" + "</html> ";
    }

}
