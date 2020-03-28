/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loanservice;

import a2.librarysystem.Library;
import a2.librarysystem.LibraryException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;

@WebService(endpointInterface="a2.loanservice.LoanServiceLibraryManager")
public class LoanServiceLibraryManagerImpl implements LoanServiceLibraryManager {
    private static Library LibraryManager;
    
    public LoanServiceLibraryManagerImpl() throws LibraryException, IOException{
        System.out.println("Created an instance of LoanService - LibrarySystem");
        LibraryManager = Library.getInstance();
    }
    @Override
    public String displayBooks() {
        return LibraryManager.displayBooks();
    }

    @Override
    public String getBook(int id) {
        return LibraryManager.getBook(id);
    }

    @Override
    public String addBook(String title, String description, String isbn, String author, String publisher, String callNumber) throws LoanServiceSOAPFault {
        try {
            LibraryManager.addBook(title, description, isbn, author, publisher, callNumber);
        } catch (LibraryException ex) {
            Logger.getLogger(LoanServiceLibraryManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new LoanServiceSOAPFault(ex.getLibraryErrorMessage());
        }
        return LibraryManager.displayBooks();
    }

    @Override
    public String updateBook(int id, String title, String description, String isbn, String author, String publisher, String callNumber) throws LoanServiceSOAPFault {
        try {
            LibraryManager.updateBook(id, title, description, isbn, author, publisher, callNumber);
        } catch (LibraryException ex) {
            Logger.getLogger(LoanServiceLibraryManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new LoanServiceSOAPFault(ex.getLibraryErrorMessage());
        }
        return LibraryManager.displayBooks();
    }

    @Override
    public String removeBook(int id) throws LoanServiceSOAPFault {
        try {
            LibraryManager.removeBook(id);
        } catch (LibraryException ex) {
            Logger.getLogger(LoanServiceLibraryManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new LoanServiceSOAPFault(ex.getLibraryErrorMessage());
        }
        return LibraryManager.displayBooks();
    }
    
}
