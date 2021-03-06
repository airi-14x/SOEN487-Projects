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
        //System.out.println("Created an instance of LoanSystem()");
        memberManagerConnectionInstance = memberManagerConnectionInstance.getInstance();
        libraryConnectionInstance = libraryConnectionInstance.getInstance();
    }

    // SINGLETON    
    public static LoanManagerImpl getInstance() throws LoanException, IOException, LibraryException {
        if (loanManagerConnectionInstance == null) {
            loanManagerConnectionInstance = new LoanManagerImpl();
            //System.out.println("LoanManager - Instance has been created!");
        }
        return loanManagerConnectionInstance;
    }

    public ConcurrentHashMap<Integer, Loan> getLoansMap() {
        return loans;
    }

    // Borrow a book --> Create loan
    @Override
    public synchronized void borrowBook(String callNumber, int memberID, String borrowDate, String returnDate) throws LoanException {
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
                            
                            // Loan associated with book belongs to no member //
                            if (loan.getValue().getMember() == null) {
                                currentLoanID = loan.getKey();
                                Loan newLoan = new Loan(book.getTitle(), member, borrowDate, returnDate);
                                newLoan.setLoanID(currentLoanID); //Get current key "id" to match loanID value
                                System.out.println(newLoan);
                                loans.put(loan.getKey(), newLoan);
                                currentLoanMember = member;
                            } // Book is currently loaned to someone --> memberID != null
                            else {
                                currentLoanID = loan.getKey();
                                currentLoanMember = member;
                                throw new LoanException("Book not available");
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

    // Edit a Book Loan --> Just for editing borrowDate, returnDate. It will mess up if you edit bookTitle
    @Override
    public synchronized void editBookLoan(int loanID, String borrowDate, String returnDate) throws LoanException {
        if (!loans.containsKey(loanID)) {
            throw new LoanException("Loan Manager - Loan does not exist!");
        } else {
            Loan newLoan = new Loan(loans.get(loanID).getBookTitle(), loans.get(loanID).getMember(), borrowDate, returnDate);
            newLoan.setLoanID(loanID);
            loans.put(loanID, newLoan); //Replace previous loan with updated book loan
        }
    }

    // Return Book
    // Set memberID and all attributes expect book to null
    @Override
    public synchronized void returnBookLoan(int loanID) throws LoanException {
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
    public synchronized void deleteBookLoan(int loanID) throws LoanException {
        if (!loans.containsKey(loanID)) {
            throw new LoanException("Loan Manager - Loan does not exist!");
        } else {
            loans.remove(loanID); // Entirely remove loan from hashmap
        }
    }
}
