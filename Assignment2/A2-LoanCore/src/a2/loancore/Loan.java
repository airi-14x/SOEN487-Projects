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
    private Member member;
    //private int memberID;
    //private String memberName;
    //private String memberContact;
    //private String borrowDate;
    private String returnDate;

    public Loan(){
        
    }

    public Loan(String bookTitle, Member member, String returnDate) {
        this.bookTitle = bookTitle;
        this.member = member;
        this.returnDate = returnDate;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Loan{" + "bookTitle=" + bookTitle + ", member=" + member + ", returnDate=" + returnDate + '}';
    }

    

}
