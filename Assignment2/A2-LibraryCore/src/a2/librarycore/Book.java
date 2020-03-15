/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2.librarycore;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Airi
 */
@XmlRootElement(name="Book")
public class Book implements Serializable {

    private int id; 
    @XmlElement
    private String title;
    @XmlElement
    private String description;
    @XmlElement
    private String isbn;
    @XmlElement
    private String author;
    @XmlElement
    private String publisher;
    @XmlElement
    private String callNumber;

    public Book() {

    }

    public Book(String title, String description, String isbn, String author, String publisher, String callNumber) {
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.callNumber = callNumber;
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

    public void setIsbn(String isbn) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", description=" + description + ", isbn=" + isbn + ", author=" + author + ", publisher=" + publisher + ", callNumber=" + callNumber + '}';
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        } else if (!(object instanceof Book)) {
            return false;
        } else {
            Book book = (Book) object;
            if (id == book.getId()
                    && title.equals(book.getTitle())
                    && description.equals(book.getDescription())
                    && isbn.equals(book.getIsbn())
                    && author.equals(book.getAuthor())
                    && publisher.equals(book.getPublisher())
                    && callNumber.equals(book.getCallNumber())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.title);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.isbn);
        hash = 97 * hash + Objects.hashCode(this.author);
        hash = 97 * hash + Objects.hashCode(this.publisher);
        return hash;
    }
}
