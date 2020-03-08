/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loansystem;

import java.io.IOException;

/**
 *
 * @author Airi
 */
public class A2LoanSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws LoanException, IOException {
       LoanManager loans = LoanManager.getInstance();
       loans.dropLibraryTable();
       loans.createLoanTable();
       loans.cleanup();
    }
    
    
}
