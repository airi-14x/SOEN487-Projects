/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryservice;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author Airi
 */
@Path("/LibraryRestService")
public class LibraryRESTServiceResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LibraryRESTServiceResource
     */
    public LibraryRESTServiceResource() {
    }

    /**
     * Retrieves representation of an instance of libraryservice.LibraryRESTServiceResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(javax.ws.rs.core.MediaType.TEXT_PLAIN)
    public String getText() {
        //TODO return proper representation object
         return "<html><body><h1>Hello World!</h1></body></html>";
        //throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of LibraryRESTServiceResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(javax.ws.rs.core.MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }
}
