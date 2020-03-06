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
    private String member;
    private String borrowDate;
    private String returnDate;

    public Loan(){
        
    }
    
    public Loan(String bookTitle, String member, String borrowDate, String returnDate) {
        this.bookTitle = bookTitle;
        this.member = member;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
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
        return "Loan{" + "bookTitle=" + bookTitle + ", member=" + member + ", borrowDate=" + borrowDate + ", returnDate=" + returnDate + '}';
    }

}
