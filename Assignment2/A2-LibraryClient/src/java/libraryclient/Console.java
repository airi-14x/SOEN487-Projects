/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryclient;

import a2.librarysystem.Library;
import a2.librarysystem.LibraryException;
import javax.ws.rs.core.Response;

/**
 *
 * @author jasminelatendresse
 */
public class Console {
    public static LibraryClient client = new LibraryClient();
    public static Library library = new Library();
    public static void main(String[] args) throws LibraryException {
        
        String title = "hi";
        String description = "hi";
        String isbn = "hi";
        String author = "hi";
        String publisher = "hi";
        String callNumber = "hi22eqw2";
        
        String input = "{\"title\":\"hi\",\"description\":\"hi\",\"isbn\":\"hi\",\"author\":\"hi\",\"publisher\":\"hi\",\"callNumber\":\"hi2e33w3\"}";
        System.out.println(client.addBookBasic(title, description, isbn, author, publisher, callNumber));
        System.out.println(client.addBookJson(input));
        System.out.println("Before delete " + client.listBooksJson(String.class));
        System.out.println("Get book plain: " + client.getBookPlain(String.class, 1));
        System.out.println("Get book json: " + client.getBookJson(String.class, 1));
        System.out.println("Get book xml: " + client.getBookXml(String.class, 1));
        System.out.println("Get book html: " + client.getBookHtml(String.class, 1));
        //System.out.println(client.deleteBook(1));
        //System.out.println("After delete " + client.listBooksJson(String.class));
        
        client.close();
    }
    
}
