package csulb.cecs323.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Books {
    /**
     * Variables
     */

    private String name;
    @Id
    private String ISBN;
    private String bookTitle;
    private int yearPublished;
    private String authorEntityEmail;

    /**
     * Constructor
     */

    public Books() {}

    public Books(String n, String i, String b, int y, String ae) {
        this.name = n;
        this.ISBN = i;
        this.bookTitle = b;
        this.yearPublished = y;
        this.authorEntityEmail = ae;
    }

    /**
     * Getters & Setters
     */

    public String getName() {
        return this.name;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public String getBookTitle() {
        return this.bookTitle;
    }

    public int getYearPublished() {
        return this.yearPublished;
    }

    public String getAuthorEntityEmail() {
        return this.authorEntityEmail;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setISBN(String i) {
        this.ISBN = i;
    }

    public void setBookTitle(String b) {
        this.bookTitle = b;
    }

    public void setYearPublished(int y) {
        this.yearPublished = y;
    }

    public void setAuthorEntityEmail(String ae) {
        this.authorEntityEmail = ae;
    }

    /**
     * Returns string values of Books
     */

    @Override
    public String toString () {
        return "Publisher Name: " + this.name + " ISBN: " + this.ISBN +
                " Book Title: " + this.bookTitle + " Year Published: " + this.yearPublished +
                " Email: " + this.authorEntityEmail;
    }
}
