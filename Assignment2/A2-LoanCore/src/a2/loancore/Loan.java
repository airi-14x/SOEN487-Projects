/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.loancore;

/**
 *
 * @author Airi
 */
public class Loan {
    
    private String bookTitle;
    private int memberID;
    private String memberName;
    private String memberContact;
    private String borrowDate;
    private String returnDate;

    public Loan(){
        
    }

    public Loan(String bookTitle, int memberID, String memberName, String memberContact, String borrowDate, String returnDate) {
        this.bookTitle = bookTitle;
        this.memberID = memberID;
        this.memberName = memberName;
        this.memberContact = memberContact;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberContact() {
        return memberContact;
    }

    public void setMemberContact(String memberContact) {
        this.memberContact = memberContact;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Loan{" + "bookTitle=" + bookTitle + ", memberID=" + memberID + ", memberName=" + memberName + ", memberContact=" + memberContact + ", borrowDate=" + borrowDate + ", returnDate=" + returnDate + '}';
    }
   
    

}
