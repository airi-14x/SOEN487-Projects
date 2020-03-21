/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package a2.loanservice;

import a2.loancore.Member;
import a2.loansystem.LoanException;
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
    //@WebMethod public String listLoan(String bookTitle) throws LoanServiceSOAPFault;
    //@WebMethod public String listLoanID(int memberID) throws LoanServiceSOAPFault;
    //@WebMethod public void borrowBook(String callNumber, int memberID, String borrowDate, String returnDate) throws LoanException, LoanServiceSOAPFault;
    //@WebMethod public void editBookLoan(int loanID, Member member, String borrowDate, String returnDate) throws LoanException, LoanServiceSOAPFault;
    //@WebMethod public void returnBookLoan(int loanID) throws LoanException, LoanServiceSOAPFault;
    //@WebMethod public void deleteBookLoan(int loanID) throws LoanException, LoanServiceSOAPFault;
    @WebMethod public String listLoan(String bookTitle);
    @WebMethod public String listLoanID(int memberID);
    @WebMethod public void borrowBook(String callNumber, int memberID, String borrowDate, String returnDate) throws LoanException;
    @WebMethod public void editBookLoan(int loanID, Member member, String borrowDate, String returnDate) throws LoanException;
    @WebMethod public void returnBookLoan(int loanID) throws LoanException;
    @WebMethod public void deleteBookLoan(int loanID) throws LoanException;
}