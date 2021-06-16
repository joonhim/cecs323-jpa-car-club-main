package csulb.cecs323.model;

// Imported the necessary Java packages and extensions needed for this class.
import javax.persistence.*;

/**
 * A printed work consisting of sheets of paper bound together that is written by a writer.
 */
public class Books {
    /**
     * The number of the ISBN.
     */
    @Id //PK
    @Column(length = 17, nullable = false)
    private String isbn;

    /**
     * The title of the Book.
     */
    @Column(length = 80, nullable = false)
    private String title;

    /**
     * The year the Book is published.
     */
    @Column(length = 4, nullable = false)
    private int year_published;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Publisher_Name", referencedColumnName = "name", nullable = false)
    private Publishers publishers;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Authoring_Entity_Email", referencedColumnName = "email", nullable = false)
    private authoring_entities authoringEntities;

    /**
     * Constructor
     */
    public Books() {}

    /**
     * The constructor for the Book class.
     * @param isbn
     * @param bookTitle
     * @param yearPublished
     * @param publisher
     * @param author
     */
    public Books(String isbn, String bookTitle, int yearPublished, Publishers publisher, authoring_entities author) {
        this.isbn = isbn;
        this.title = bookTitle;
        this.year_published = yearPublished;
        this.publishers = publisher;
        this.authoringEntities = author;
    }

    /**
     * Gets the number of the ISBN.
     * @return ISBN of the Book
     */
    public String getISBN() {
        return this.isbn;
    }

    /**
     * Gets the title of the Book.
     * @return Title of the Book
     */
    public String getBookTitle() {
        return this.title;
    }

    /**
     * Gets the published year of the Book.
     * @return Year the Book is published
     */
    public int getYearPublished() {
        return this.year_published;
    }


    /**
     * Changes the ISBN number of the Book.
     * @param isbn
     */
    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Changes the title of the Book.
     * @param book_title
     */
    public void setBookTitle(String book_title) {
        this.title = book_title;
    }
    /**
     * Changes the year published of the Book.
     * @param year_published
     */
    public void setYearPublished(int year_published) {
        this.year_published = year_published;
    }

    public String getPublisherName(){
        return publishers.getName();
    }

    public String getAuthorEmail(){
        return authoringEntities.getEmail();
    }

    /**
     * Returns string values of Books
     */

    @Override
    public String toString () {
        return "Publisher Name: " + getPublisherName() + " ISBN: " + this.isbn +
                " Book Title: " + this.title + " Year Published: " + this.year_published +
                " Email: " + getAuthorEmail();
    }
}
