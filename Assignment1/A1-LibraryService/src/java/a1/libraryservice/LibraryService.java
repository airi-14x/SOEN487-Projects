/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a1.libraryservice;

/**
 *
 * @author Airi
 */

import javax.ws.rs.GET;  
import javax.ws.rs.Path;  
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;  
import javax.ws.rs.core.MediaType;  
import javax.ws.rs.core.Response;
@Path("/LibraryService")  
public class LibraryService {
    
  /*@GET  
  @Produces(MediaType.TEXT_PLAIN)  
  public String sayPlainTextHello() {  
    return "Hello LibraryService Plain";  
  }*/
  
  public Response listBooks(){
      return null;
  }     
    
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/book/{id}")  
  public Response getBook(@PathParam("id") String id){
      String output = "Library Service ID: " + id;
      
      return Response.status(200).entity(output).build();
  }
  
  public Response addBook(){
      return null;
  }
  
  public Response updateBook(){
      return null;
  }
  
  public Response deleteBook(){
      return null;
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
