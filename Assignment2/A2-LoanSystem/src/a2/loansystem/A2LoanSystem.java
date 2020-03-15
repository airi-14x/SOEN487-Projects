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
        library.addBook("Meditations", "Written in Greek, without any intention of publication, by the only Roman emperor",
                "01404493371", "Marcus Aurelius", "Penguin Classic", "B 583 S74 2012");
        System.out.println(library.getBooksMap());
        System.out.println(library.getCallNumbersMap());

        library.addBook("2-Meditations", "Written in Greek, without any intention of publication, by the only Roman emperor",
                "01404493372", "Marcus Aurelius", "Penguin Classic", "B 583 S74 2021");

        library.updateBook(1, "Meditations", "Written in Greek, without any intention of publication, by the only Roman emperor",
                "01404493371", "Marcus Aurelius", "Penguin Classic", "B 583 S74 2014");
        System.out.println("After Update");
        System.out.println(library.getBooksMap());
        System.out.println(library.getCallNumbersMap());

        System.out.println("Get Book");
        System.out.println(library.getBook(1));

        System.out.println("Remove Book");
        library.removeBook(1);
        System.out.println(library.getBooksMap());
        System.out.println(library.getCallNumbersMap());

        // --- MemberManagerImpl --- //
        MemberManagerImpl members = new MemberManagerImpl();
        members.addMember("Airi", "fkwjf@gmail.com");
        members.addMember("Ali", "wssjf@gmail.com");
        System.out.println(members.getMembersMap());

        System.out.println(members.getMembers());
        System.out.println(members.getMemberInfo(4));

        members.updateMember(2, "Alice", "sfdd");
        System.out.println(members.getMembersMap());

        members.deleteMember(2);
        System.out.println(members.getMembersMap());

        
        // --- LoanManagerImpl --- //
        LoanManagerImpl loans = LoanManagerImpl.getInstance();
        loans.borrowBook("B 583 S74 2021", 1, "2020-02-04", "2020-03-04");
        System.out.println(loans.getLoansMap());
        Member member1 = new Member("Jo","hello@gmail.com");
        member1.setMemberID(members.memberMapKey()); // Need to set ID separately
        loans.editBookLoan(1, "djkfdj", member1, "2020-03-04", "2020-04-03");
        System.out.println(loans.getLoansMap());
        loans.returnBookLoan(1);
        System.out.println(loans.getLoansMap());
        
        loans.deleteBookLoan(1);
        System.out.println(loans.getLoansMap());
        int input = 0;
        //System.out.println("Stop A2 LoanSystem by entering '1': ");
        Scanner scan = new Scanner(System.in);
        while(input != 1)
        {
            System.out.println("Stop A2 LoanSystem by entering '1': ");
            input = scan.nextInt();
        }
        // Database Version //
        //LoanManager loans = LoanManagerImpl.getInstance();
        //loans.dropLoanTable();
        //loans.createLoanTable();
        //loans.cleanup();
        //MemberManager members = MemberManagerImpl.getInstance();
        //members.dropMemberTable();
        //members.createMemberTable();
        //members.cleanup();
    }

}
