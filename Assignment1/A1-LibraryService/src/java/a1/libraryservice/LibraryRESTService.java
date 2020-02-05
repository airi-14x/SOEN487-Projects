/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a1.libraryservice;

import a1.librarysystem.LibrarySystem;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Airi
 */

@Path("/LibraryRESTService") 
public class LibraryRESTService {
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/books")    
  public Response listBooks(){
      LibrarySystem librarySystem = new LibrarySystem();
      String output = librarySystem.displayBooks();
      return Response.status(200).entity(output).build();
  }     
    
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/book/{id}")  
  public Response getBook(@PathParam("id") int id){
      String output = "Library Service ID: " + id;
      LibrarySystem librarySystem = new LibrarySystem();
      output = librarySystem.getBook(id);
      
      return Response.status(200).entity(output).build();
  }
  
  @POST
  @Produces(MediaType.TEXT_PLAIN)
  //@Path("/book")
  @Path("/book/add")
  public Response addBook(@FormParam("title") String title,
          @FormParam("description") String description,
          @FormParam("isbn") String isbn, 
          @FormParam("author") String author,
          @FormParam("publisher") String publisher){
      LibrarySystem librarySystem = new LibrarySystem();
      String output = librarySystem.addBook(title, description, isbn, author, publisher);
      return Response.status(200).entity(output).build();
      //String output = "Current Book has title: " + title + " description: " + description +
      //        " isbn: " + isbn + " author: " + author + " publisher: " + publisher;
      //return Response.status(200).entity(output).build();
  }
  
  @PUT
  @Produces(MediaType.TEXT_PLAIN)
  //@Path("/book")
  @Path("/book/update/")
  public Response updateBook(@QueryParam("id") int id,
          @QueryParam("title") String title,
          @QueryParam("description") String description,
          @QueryParam("isbn") String isbn, 
          @QueryParam("author") String author,
          @QueryParam("publisher") String publisher){
      LibrarySystem librarySystem = new LibrarySystem();
      String output = librarySystem.updateBook(id,title, description, isbn, author, publisher);
      return Response.status(200).entity(output).build();
  }
  
  @DELETE
  @Produces(MediaType.TEXT_PLAIN)
  //@Path("/book")
  @Path("/book/delete/{id}")
  public Response deleteBook(@QueryParam("id") int id){
      LibrarySystem librarySystem = new LibrarySystem();
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
