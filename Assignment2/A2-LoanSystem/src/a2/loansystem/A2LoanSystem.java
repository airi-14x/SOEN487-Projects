/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loansystem;

import a2.librarycore.Book;
import a2.librarysystem.Library;
import a2.librarysystem.LibraryException;
import a2.loancore.Member;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Airi
 */
public class A2LoanSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws LoanException, IOException, LibraryException {

        // --- Library --- //
        Library library = new Library();
        Book book = new Book();
        
        // ADD BOOKS //
        library.addBook("Meditations", "Written in Greek, without any intention of publication, by the only Roman emperor",
                "01404493371", "Marcus Aurelius", "Penguin Classic", "B 583 S74 2012");
        library.addBook("Happiness", "helloworld", "231232141", "Panda Bear", "HappinessCorp", "H 123 456 2016");
        library.addBook("123-Help", " publication, by the only Roman emperor",
                "01404493372", "fefe", "Penguin Classic", "B 583 S74 2021");
        System.out.println(library.getBooksMap());
        System.out.println(library.getCallNumbersMap());

        // UPDATE BOOK //
        System.out.println("After Update:");
        library.updateBook(1, "Meditations", "Written in Greek, without any intention of publication, by the only Roman emperor",
                "01404493371", "Marcus Aurelius", "Penguin Classic", "B 583 S74 2014");
        System.out.println(library.getBooksMap());
        System.out.println(library.getCallNumbersMap());

        // GET BOOK //
        System.out.println("Get Book:");
        System.out.println(library.getBook(1));

        
        // DELETE BOOK //
        /*
        System.out.println("Remove Book");
        library.removeBook(1);
        System.out.println(library.getBooksMap());
        System.out.println(library.getCallNumbersMap());
        */

        System.out.println("==============");
        // --- MemberManagerImpl --- //
        MemberManagerImpl members = new MemberManagerImpl();
        
        // ADD MEMBER //
        members.addMember("Airi", "fkwjf@gmail.com");
        members.addMember("Ali", "wssjf@gmail.com");
        System.out.println(members.getMembersMap());

        // GET MEMBER //
        System.out.println(members.getMembers());
        System.out.println(members.getMemberInfo(4));

        // EDIT MEMBER //
        members.updateMember(2, "Alice", "sfdd");
        System.out.println(members.getMembersMap());

        // DELETE MEMBER //
        //members.deleteMember(2);
        //System.out.println(members.getMembersMap());

        System.out.println("==============");
        // --- LoanManagerImpl --- //
        LoanManagerImpl loans = LoanManagerImpl.getInstance();
        
        // BORROW BOOK //
        System.out.println("Borrowing - B 583 S74 2014:");
        loans.borrowBook("B 583 S74 2014", 1, "2020-02-04", "2020-03-04");
        System.out.println(loans.getLoansMap());
        Member member1 = new Member("Jo","hello@gmail.com");
        member1.setMemberID(members.memberMapKey()); // Need to set ID separately if called outside of LoanSystem class
        
        // EDIT BOOK //
        System.out.println("Editing Loan 1: ");
        loans.editBookLoan(1, member1, "2020-03-04", "2020-04-03");
        System.out.println(loans.getLoansMap());
        System.out.println(library.getBooksMap());
        System.out.println(library.getCallNumbersMap());
        
        // Borrowing a loaned book //
        System.out.println("Borrowing - B 583 S74 2014: (Should be unavailable)");
        loans.borrowBook("B 583 S74 2014", 1, "2020-02-04", "2020-03-04");
        
        
        // Borrowing another book //
        //System.out.println("Borrowing - H 123 456 2016: (Should be available)");
        //loans.borrowBook("H 123 456 2016", 1, "2020-02-04", "2020-03-04");
        
        System.out.println("Returning Book:");
        loans.returnBookLoan(1);
        System.out.println(loans.getLoansMap());
        
        System.out.println("Borrow Book:"); //Why is it unavailable????
        loans.borrowBook("B 583 S74 2021", 1, "2020-02-04", "2020-03-04");
        System.out.println(members.getMembersMap());
        System.out.println(loans.getLoansMap());
        
        
        // Enable to test out methods if LoanService ones fail //
        /*
        System.out.println("Listing Loans:");
        System.out.println(loans.listLoan(1));
        System.out.println(loans.listLoan(2));
        
        System.out.println("Listing Loans(BookTitle):");
        System.out.println(loans.listLoan("123-Help"));
        System.out.println(loans.listLoan("Meditations2"));
        */
        
        loans.deleteBookLoan(1);
        System.out.println(loans.getLoansMap());
        
    }

}