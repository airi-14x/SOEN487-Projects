/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loansystem;

/**
 *
 * @author Airi
 */
public class LoanException extends Exception {
    
    String loanErrorMessage;
    
    public LoanException(String errorMessage) {
        System.out.println(errorMessage);
        setLoanErrorMessage(errorMessage);
    }

    public String getLoanErrorMessage() {
        return loanErrorMessage;
    }

    public void setLoanErrorMessage(String loanErrorMessage) {
        this.loanErrorMessage = loanErrorMessage;
    }
    
    
    
}
