/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryclient;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 * Jersey REST client generated for REST resource:LibraryRESTService
 * [/LibraryRESTService]<br>
 * USAGE:
 * <pre>
 *        LibraryClient client = new LibraryClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Airi
 */
public class LibraryClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/A1-LibraryService/webresources";

    public LibraryClient() {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("LibraryRESTService");
    }

    public String sayXMLHello() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(MediaType.TEXT_XML).get(String.class);
    }

    public Response deleteBook(int id) throws ClientErrorException {
        //webTarget.queryParam("id", id);
        return webTarget.queryParam("id", id).path("book/delete").request().delete(Response.class);
    }

    public <T> T getBook(Class<T> responseType, int id) throws ClientErrorException {
        return webTarget.path("book/" + id).request(MediaType.TEXT_PLAIN).get(responseType);
    }

    public <T> T listBooks(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("books");
        return resource.request(MediaType.TEXT_PLAIN).get(responseType);
    }

    public Response updateBook(int id, String title, String description, String isbn, String author, String publisher) throws ClientErrorException {
        //webTarget.queryParam("id", id);
        //webTarget.queryParam("title", title);
        //webTarget.queryParam("description", description);
        //webTarget.queryParam("isbn", isbn);
        //webTarget.queryParam("author", author);
        //webTarget.queryParam("publisher", publisher);
        Entity<?> empty = Entity.text("");
        webTarget = webTarget.queryParam("id", id).queryParam("title", title).queryParam("description", description).queryParam("isbn", isbn).queryParam("author", author).queryParam("publisher", publisher);
        return webTarget.path("book/update").request().put(empty, Response.class);
        //return webTarget.queryParam("title", title).queryParam("description", description).queryParam("isbn", isbn).queryParam("author", author).queryParam("publisher", publisher).path("book/update").request().put(null, Response.class);
    }

    //updateBook with MultivaluedMap
    /*public Response updateBook(int id, String title, String description, String isbn, String author, String publisher) {
        MultivaluedMap<String, String> queryParams = new MultivaluedHashMap<>();
        queryParams.add("id", Integer.toString(id));
        queryParams.add("title", title);
        queryParams.add("description", description);
        queryParams.add("isbn", isbn);
        queryParams.add("athor", author);
        queryParams.add("publisher", publisher);
        return webTarget.path("book/update").request().put(Entity.form(queryParams));
    }*/

    public String sayHtmlHello() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(MediaType.TEXT_HTML).get(String.class);
    }

    public Response addBook(String title, String description, String isbn, String author, String publisher) throws ClientErrorException {

        return webTarget.queryParam("title", title).queryParam("description", description).queryParam("isbn", isbn).queryParam("author", author).queryParam("publisher", publisher).path("book/add").request().post(null, Response.class);
    }

    // addBook with MultivaluedMap
    /*public Response addBook(String title, String description, String isbn, String author, String publisher) throws ClientErrorException {
        MultivaluedMap<String, String> queryParams = new MultivaluedHashMap<>();
        queryParams.add("title", title);
        queryParams.add("description", description);
        queryParams.add("isbn", isbn);
        queryParams.add("athor", author);
        queryParams.add("publisher", publisher);
        return webTarget.path("book/add").request(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.TEXT_PLAIN).post(Entity.form(queryParams));
    }*/

 /*
    public Response addBookForm() throws ClientErrorException {
        return webTarget.path("book").request().post(null, Response.class);
    }*/
    public String sayPlainTextHello() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(MediaType.TEXT_PLAIN).get(String.class);
    }

    public void close() {
        client.close();
    }

}
