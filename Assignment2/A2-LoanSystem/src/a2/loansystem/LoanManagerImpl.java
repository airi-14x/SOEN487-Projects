/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loansystem;

import a2.librarycore.Book;
import a2.librarysystem.Library;
import a2.librarysystem.LibraryException;
import a2.loancore.Loan;
import a2.loancore.Member;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Airi
 */
public class LoanManagerImpl implements LoanManager {

    private static ConcurrentHashMap<Integer, Loan> loans = new ConcurrentHashMap<Integer, Loan>();
    private static AtomicInteger loanMapKey = new AtomicInteger();
    private static LoanManagerImpl loanManagerConnectionInstance;
    private static MemberManagerImpl memberManagerConnectionInstance;
    private static Library libraryConnectionInstance;

    public LoanManagerImpl() throws LoanException, IOException, LibraryException {
        System.out.println("Created an instance of LoanSystem()");
        memberManagerConnectionInstance = memberManagerConnectionInstance.getInstance();
        libraryConnectionInstance = libraryConnectionInstance.getInstance();
    }

    // SINGLETON    
    public static LoanManagerImpl getInstance() throws LoanException, IOException, LibraryException {
        if (loanManagerConnectionInstance == null) {
            loanManagerConnectionInstance = new LoanManagerImpl();
            System.out.println("LoanManager - Instance has been created!");
        }
        return loanManagerConnectionInstance;
    }

    public ConcurrentHashMap<Integer, Loan> getLoansMap() {
        return loans;
    }

    public String listLoan(String bookTitle) {
        ConcurrentHashMap<Integer, Loan> loansMap = loanManagerConnectionInstance.getLoansMap();
        String loanResult = ""; //Multiple loans with same title
        for (Map.Entry<Integer, Loan> loan : loansMap.entrySet()) {
            if (loan.getValue().getBookTitle().equals(bookTitle)) {
                loanResult += loan.toString() + "\n";
            }
        }
        if (loanResult.isEmpty()) {
            return "No loans are associated with book title: " + bookTitle;
        } else {
            return loanResult;
        }

    }

    public String listLoan(int memberID) {
        ConcurrentHashMap<Integer, Loan> loansMap = loanManagerConnectionInstance.getLoansMap();
        String loanResult = ""; //Multiple loans with same memberID
        for (Map.Entry<Integer, Loan> loan : loansMap.entrySet()) {
            if (loan.getValue().getMember() != null) {
                if (loan.getValue().getMember().getMemberID() == memberID) {
                    loanResult += loan.toString() + "\n";
                }
            }

        }
        if (loanResult.isEmpty()) {
            return "No loans are associated with memberID: " + memberID;
        } else {
            return loanResult;
        }

    }

    // Borrow a book --> Create loan
    @Override
    public void borrowBook(String callNumber, int memberID, String borrowDate, String returnDate) throws LoanException {
        ConcurrentHashMap<Integer, Member> memberMap = memberManagerConnectionInstance.getMembersMap();
        ConcurrentHashMap<String, Book> callNumberMap = libraryConnectionInstance.getCallNumbersMap();
        //Get current member with memberID
        if (!memberMap.containsKey(memberID)) {
            throw new LoanException("Loan Manager - Member does not exist!");
        } else {
            if (!callNumberMap.containsKey(callNumber)) {
                throw new LoanException("Loan Manager - Book's Call Number does not exist!");
            } else {
                Book book = callNumberMap.get(callNumber); // Get Book via callNumber
                Member member = memberMap.get(memberID);

                int currentLoanID = 0;
                Member currentLoanMember = null;

                // Check if book is available => Associated with no memberID //
                // No Loans exists //
                if (loans.isEmpty()) {
                    Loan newLoan = new Loan(book.getTitle(), member, borrowDate, returnDate);
                    int loanID = loanMapKey.incrementAndGet();
                    newLoan.setLoanID(loanID);
                    loans.put(loanID, newLoan);

                } // Loans exist //
                else {
                    for (Map.Entry<Integer, Loan> loan : loans.entrySet()) {
                        // Matching the same book title //
                        if (loan.getValue().getBookTitle().equals(book.getTitle())) {
                            //currentLoanID = loan.getKey();
                            // Loan associated with book belongs to no member //
                            if (loan.getValue().getMember() == null) {
                                currentLoanID = loan.getKey();
                                System.out.println("Book is available");
                                Loan newLoan = new Loan(book.getTitle(), member, borrowDate, returnDate);
                                newLoan.setLoanID(currentLoanID); //Get current key "id" to match loanID value
                                System.out.println("New Loan");
                                System.out.println(newLoan);
                                loans.put(loan.getKey(), newLoan);
                                currentLoanMember = member;
                            } // Book is currently loaned to someone --> memberID != null
                            else {
                                currentLoanID = loan.getKey();
                                currentLoanMember = member;
                                System.out.println("Book not available");
                            }
                        }
                    }
                    // This book title does not exist in Loans yet
                    if (currentLoanID == 0 && currentLoanMember == null) {
                        Loan newLoan = new Loan(book.getTitle(), member, borrowDate, returnDate);
                        int loanID = loanMapKey.incrementAndGet();
                        newLoan.setLoanID(loanID);
                        loans.put(loanID, newLoan);
                    }
                }

            }

        }
    }

    // Edit a Book Loan --> Just for editing borrowDate, returnDate, member. It will mess up if you edit bookTitle
    @Override
    public void editBookLoan(int loanID, Member member, String borrowDate, String returnDate) throws LoanException {
        if (!loans.containsKey(loanID)) {
            throw new LoanException("Loan Manager - Loan does not exist!");
        } else {
            Loan newLoan = new Loan(loans.get(loanID).getBookTitle(), member, borrowDate, returnDate);
            newLoan.setLoanID(loanID);
            System.out.println("Old Loans: ");
            System.out.println(loans);
            loans.put(loanID, newLoan); //Replace previous loan with updated book loan
            System.out.println("New Loans: ");
            System.out.println(loans);
            System.out.println("=====!====");
        }
    }

    // Return Book
    // Set memberID and all attributes expect book to null
    @Override
    public void returnBookLoan(int loanID) throws LoanException {
        if (!loans.containsKey(loanID)) {
            throw new LoanException("Loan Manager - Loan does not exist!");
        } else {
            Loan loan = loans.get(loanID);
            // Keep book title there to get Call No.
            loan.setMember(null);
            loan.setBorrowDate(null);
            loan.setReturnDate(null);
            loans.put(loanID, loan); // Update book loan
        }

    }

    // Delete a Book Loan
    @Override
    public void deleteBookLoan(int loanID) throws LoanException {
        if (!loans.containsKey(loanID)) {
            throw new LoanException("Loan Manager - Loan does not exist!");
        } else {
            loans.remove(loanID); // Entirely remove loan from hashmap
        }
    }
}
