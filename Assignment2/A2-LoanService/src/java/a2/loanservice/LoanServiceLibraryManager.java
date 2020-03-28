/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loanservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author Airi
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface LoanServiceLibraryManager {
    @WebMethod public String displayCallNumberMap();
    @WebMethod public String displayBooks();
    @WebMethod public String getBook(int id);
    @WebMethod public String addBook(String title, String description, String isbn, String author, String publisher, String callNumber) throws LoanServiceSOAPFault;
    @WebMethod public String updateBook(int id, String title, String description, String isbn, String author, String publisher, String callNumber) throws LoanServiceSOAPFault;
    @WebMethod public String removeBook(int id) throws LoanServiceSOAPFault;
}
