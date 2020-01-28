/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a1.librarycore;

/**
 *
 * @author jasminelatendresse
 */
public class Book {
    //Need to find a way to generate unique ID when creating a new book (maybe similar to previous assignment) 
    private int id; 
    private String title; 
    private String description; 
    private String isbn; 
    private String author; 
    private String publisher; 
    
    public Book(String title, String description, String isbn, String author, String publisher) {
        this.title = title; 
        this.description = description; 
        this.isbn = isbn; 
        this.author = author;
        this.publisher = publisher;
    }
    
    public String getTitle() {
        return title; 
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIdbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getAuthor() {
        return author; 
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getPublisher() {
        return publisher;
    }
    
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", description=" + description + ", isbn=" + isbn + ", author=" + author + ", publisher=" + publisher + '}';
    }
}
