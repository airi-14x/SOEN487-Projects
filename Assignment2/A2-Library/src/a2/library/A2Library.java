/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.library;

/**
 *
 * @author Airi
 */
public class A2Library {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Library library = Library.getInstance();
       library.dropLibraryTable();
       library.createLibraryTable();
       System.out.println("Create Table");
       

    }
    
}
