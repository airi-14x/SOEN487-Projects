/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryclient;

import a2.librarycore.BookList;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 * Jersey REST client generated for REST resource:LibraryResource
 * [LibraryResource]<br>
 * USAGE:
 * <pre>
 *        LibraryClient client = new LibraryClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author jasminelatendresse
 */
public class LibraryClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/A2-LibraryService/webresources";

    public LibraryClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("LibraryRESTService");
        
    }
    
    //List books - plain text
    public <T> T listBooksPlain(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("books_plain");
        return resource.request(MediaType.TEXT_PLAIN).get(responseType);
    }
    
    //List books - json
    public <T> T listBooksJson(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("books_json");
        return resource.request(MediaType.APPLICATION_JSON).get(responseType);
    }
    
    //List books - xml
    public <T> T listBooksXml(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("books_xml");
        return resource.request(MediaType.APPLICATION_XML).get(responseType);
    }
    
    //List books - html
    public <T> T listBooksHtml(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("books_html");
        return resource.request(MediaType.TEXT_HTML).get(responseType);
    }
    
    //Get book - plain text
    public <T> T getBookPlain(Class<T> responseType, int id) throws ClientErrorException {
        webTarget = client.target(BASE_URI).path("LibraryRESTService");
        return webTarget.path("book_plain/" + id).request(MediaType.TEXT_PLAIN).get(responseType);
    }
    
    //Get book - json
    public <T> T getBookJson(Class<T> responseType, int id) throws ClientErrorException {
        webTarget = client.target(BASE_URI).path("LibraryRESTService");
        return webTarget.path("book_json/" + id).request(MediaType.APPLICATION_JSON).get(responseType);
    }
    
    //Get book - xml
    public <T> T getBookXml(Class<T> responseType, int id) throws ClientErrorException {
        webTarget = client.target(BASE_URI).path("LibraryRESTService");
        return webTarget.path("book_xml/" + id).request(MediaType.APPLICATION_XML).get(responseType);
    }
    
    //Bug - 406 in console
    //Get book - html
    public <T> T getBookHtml(Class<T> responseType, int id) throws ClientErrorException {
        webTarget = client.target(BASE_URI).path("LibraryRESTService");
        return webTarget.path("book_plain/" + id).request(MediaType.TEXT_HTML).get(responseType);
    }
    
    //Add book - basic data type
    public Response addBookBasic(String title, String description, String isbn, String author, String publisher, String callNumber) throws ClientErrorException {
        webTarget = client.target(BASE_URI).path("LibraryRESTService");
        MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
        form.add("title", title);
        form.add("description", description);
        form.add("isbn", isbn);
        form.add("author", author);
        form.add("publisher", publisher);
        form.add("callNumber", callNumber);
 
        return webTarget.path("book_basic/add").request().post(Entity.form(form));
    }
    
    //Add book - complex data type (with json)
    public Response addBookJson(String book) {
         webTarget = client.target(BASE_URI).path("LibraryRESTService");
         return webTarget.path("book_json/add").request("application/json").post(Entity.json(book));
    }
    
    
    //Update book - basic data type
    public Response updateBookBasic(int id, String title, String description, String isbn, String author, String publisher, String callNumber) throws ClientErrorException {
        webTarget = client.target(BASE_URI).path("LibraryRESTService");
        MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
        form.add("id", Integer.toString(id));
        form.add("title", title);
        form.add("description", description);
        form.add("isbn", isbn);
        form.add("author", author);
        form.add("publisher", publisher);
        form.add("callNumber", callNumber);
        return webTarget.path("book_basic/update").request().put(Entity.form(form));
    }
    
    //Bug - 406 in console
    //Update book - complex data type (json)
    public Response updateBookJson(int id, String book) {
         webTarget = client.target(BASE_URI).path("LibraryRESTService");
         return webTarget.path("book_json/update/" + id).request("application/json").put(Entity.json(book));
    }
    
    //Delete book - produces plain text 
    public Response deleteBook(int id) throws ClientErrorException {
        webTarget = client.target(BASE_URI).path("LibraryRESTService");
        return webTarget.path("delete/" + id).request().delete(Response.class);
    }

    public void close() {
        client.close();
    }
    
}
