/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loanservice;

import a2.loancore.Member;
import a2.loansystem.LoanException;
import javax.jws.WebService;

@WebService(endpointInterface="a2.loanservice.LoanServiceLoanManager")
public class LoanServiceLoanManagerImpl implements LoanServiceLoanManager {

    @Override
    public String listLoan(String bookTitle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String listLoan(int memberID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrowBook(String callNumber, int memberID, String borrowDate, String returnDate) throws LoanException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editBookLoan(int loanID, String bookTitle, Member member, String borrowDate, String returnDate) throws LoanException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void returnBookLoan(int loanID) throws LoanException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteBookLoan(int loanID) throws LoanException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
