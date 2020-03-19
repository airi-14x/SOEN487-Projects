/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loanservice;

import a2.librarysystem.LibraryException;
import a2.loancore.Loan;
import a2.loancore.Member;
import a2.loansystem.LoanException;
import a2.loansystem.LoanManagerImpl;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.jws.WebService;

@WebService(endpointInterface="a2.loanservice.LoanServiceLoanManager")
public class LoanServiceLoanManagerImpl implements LoanServiceLoanManager {
    private static LoanManagerImpl loanManager;
    
    public LoanServiceLoanManagerImpl() throws LoanException, IOException, LibraryException{
        System.out.println("Created an instance of LoanService - LoanManager");
        loanManager = loanManager.getInstance();
    }
    
    @Override
      public String listLoan(String bookTitle) {
        ConcurrentHashMap<Integer, Loan> loansMap = loanManager.getLoansMap();
        for(Map.Entry<Integer, Loan> loan : loansMap.entrySet()){
            if(loan.getValue().getBookTitle().equals(bookTitle))
            {
                return loan.toString();
            }
        }
        return "No loans are associated with book title: " + bookTitle;
    }

    @Override
    public String listLoan(int memberID) {
        ConcurrentHashMap<Integer, Loan> loansMap = loanManager.getLoansMap();
        
        for(Map.Entry<Integer, Loan> loan : loansMap.entrySet()){
            if(loan.getValue().getMember().getMemberID() == memberID)
            {
                //System.out.println("Member ID found: " + memberID);
                return loan.toString();  
            }
        }
        return "No loans are associated with memberID: " + memberID; 
    }

    @Override
    public void borrowBook(String callNumber, int memberID, String borrowDate, String returnDate) throws LoanException {
        loanManager.borrowBook(callNumber, memberID, borrowDate, returnDate);
    }

    @Override
    public void editBookLoan(int loanID, Member member, String borrowDate, String returnDate) throws LoanException {
       loanManager.editBookLoan(loanID, member, borrowDate, returnDate);
    }

    @Override
    public void returnBookLoan(int loanID) throws LoanException {
        loanManager.returnBookLoan(loanID);
    }

    @Override
    public void deleteBookLoan(int loanID) throws LoanException {
        loanManager.deleteBookLoan(loanID);
    }
    
}