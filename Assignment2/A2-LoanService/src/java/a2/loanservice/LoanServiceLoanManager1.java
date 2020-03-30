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
public interface LoanServiceLoanManager1 {
    @WebMethod public String listLoan(String bookTitle) throws LoanServiceSOAPFault;
    @WebMethod public String listLoanID(int memberID) throws LoanServiceSOAPFault;
    @WebMethod public String borrowBook(String callNumber, int memberID, String borrowDate, String returnDate) throws LoanServiceSOAPFault;
    @WebMethod public String editBookLoan(int loanID, String borrowDate, String returnDate) throws LoanServiceSOAPFault;
    @WebMethod public String returnBookLoan(int loanID) throws LoanServiceSOAPFault;
    @WebMethod public String deleteBookLoan(int loanID) throws LoanServiceSOAPFault;
}