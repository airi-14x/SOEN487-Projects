/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.librarysystem;

/**
 *
 * @author Airi
 */
public class LibraryException extends Exception{

    String libraryErrorMessage;
    
    public LibraryException(String errorMessage) {
        System.out.println(errorMessage);
        libraryErrorMessage = errorMessage;
    }

    public String getLibraryErrorMessage() {
        return libraryErrorMessage;
    }

    public void setLibraryErrorMessage(String libraryErrorMessage) {
        this.libraryErrorMessage = libraryErrorMessage;
    }
    
    
       
}
