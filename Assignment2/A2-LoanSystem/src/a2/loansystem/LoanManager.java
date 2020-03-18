/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loansystem;

import a2.loancore.Loan;
import a2.loancore.Member;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Airi
 */

public interface LoanManager {
    public ConcurrentHashMap<Integer, Loan> getLoansMap();
    public void borrowBook(String callNumber, int memberID, String borrowDate, String returnDate) throws LoanException;
    public void editBookLoan(int loanID, Member member, String borrowDate, String returnDate) throws LoanException;
    public void returnBookLoan(int loanID) throws LoanException;
    public void deleteBookLoan(int loanID) throws LoanException;
}
