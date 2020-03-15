/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loansystem;

import a2.loancore.Loan;
import a2.loancore.Member;
import java.util.concurrent.ConcurrentHashMap;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author Airi
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)

public interface LoanManager {
    @WebMethod public ConcurrentHashMap<Integer, Loan> getLoansMap();
    @WebMethod public void borrowBook(String callNumber, int memberID, String borrowDate, String returnDate) throws LoanException;
    @WebMethod public void editBookLoan(int loanID, String bookTitle, Member member, String borrowDate, String returnDate) throws LoanException;
    @WebMethod public void returnBookLoan(int loanID) throws LoanException;
    @WebMethod public void deleteBookLoan(int loanID) throws LoanException;
}
